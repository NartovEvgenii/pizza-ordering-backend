package com.pizza.service.cooking;

import com.pizza.domains.CookingPlan;
import com.pizza.domains.UserOrder;
import com.pizza.repository.CookingPlanRepository;
import com.pizza.service.UserOrderService;
import com.pizza.utils.LocalTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import static com.pizza.utils.ServiceConstant.PROCESSED_IDENT;

@Component
@EnableScheduling
public class CookingScheduling implements SchedulingConfigurer {

    @Autowired
    public Executor taskExecutor;
    @Autowired
    private CookingPlanRepository cookingPlanRepository;
    @Autowired
    private UserOrderService userOrderService;
    private Duration minOrderDuration;
    private Map<Long, CookingData> mapOrderToCookingData = new HashMap<>();

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor);
        taskRegistrar.addTriggerTask(
                () -> {
                    System.out.println("ПРОВЕРКА...");
                    Set<Long> readyOrders = mapOrderToCookingData.entrySet()
                            .stream()
                            .filter(entry -> entry.getValue().getStartOrderTime().plus(entry.getValue().getCookDuration())
                                    .isBefore(LocalDateTime.now())
                            )
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toSet());
                    System.out.println("Готовые заказы.." + LocalDateTime.now() + "------> " + readyOrders);
                    mapOrderToCookingData.entrySet().removeIf(entry -> readyOrders.contains(entry.getKey()));

                },
                context -> {
                    System.out.println("Вычисляю проверку...");
                    Duration nextDuration = mapOrderToCookingData.values()
                            .stream()
                            .map(cookingData -> Duration.between(LocalDateTime.now(),
                                                    cookingData.getStartOrderTime().plus(cookingData.getCookDuration()))
                            )
                            .min(Duration::compareTo)
                            .orElse(minOrderDuration);
                    int plus = (int) (Math.min(nextDuration.getSeconds(), minOrderDuration.getSeconds()) * 1000) + 500;
                    Date lastCompletionTime = Optional.ofNullable(context.lastCompletionTime())
                            .orElseGet(Date::new);
                    Instant nextExecutionTime = lastCompletionTime.toInstant()
                            .plusMillis(plus);
                    System.out.println("Следующая проверка через " + plus);
                    return Date.from(nextExecutionTime);
                }
        );
    }

    private CookingData getCookingDataFromUserOrder(UserOrder userOrder) {
        CookingData cookingData = new CookingData();
        cookingData.setIdOrder(userOrder.getIdOrder());
        LocalDateTime startOrderTime = userOrder.getUserOrderHistories()
                .stream()
                .filter(userOrderHistory -> userOrderHistory.getOrderState().getIdentifier()
                        .equals(PROCESSED_IDENT))
                .findFirst().orElseThrow()
                .getChangeTime();
        cookingData.setStartOrderTime(startOrderTime);
        Duration fullDuration = userOrder.getOrderItems()
                .stream()
                .map(orderItem -> {
                            CookingPlan cookingPlan = orderItem.getPizza().getCookingPlan();
                            return getDurationCookByCookingPlan(cookingPlan, orderItem.getCountItems());
                        }
                )
                .reduce(Duration.ZERO, Duration::plus);
        cookingData.setCookDuration(fullDuration);
        return cookingData;
    }

    private Duration getDurationCookByCookingPlan(CookingPlan cookingPlan, int countItems) {
        long seconds = countItems * LocalTimeUtils.getSecondDuration(cookingPlan.getBasePreparationTime())
                + LocalTimeUtils.getSecondDuration(cookingPlan.getOvenTime())
                + countItems * LocalTimeUtils.getSecondDuration(cookingPlan.getPackingTime());
        return Duration.ofSeconds(seconds);
    }

    @PostConstruct
    private void loadLocalData() {
        loadMinOrderDuration();
        loadProcessedUserOrders();
    }

    private void loadMinOrderDuration() {
        minOrderDuration = cookingPlanRepository.findAll().stream()
                .map(cookingPlan -> getDurationCookByCookingPlan(cookingPlan, 1)
                )
                .min(Duration::compareTo)
                .orElseThrow();
    }

    private void loadProcessedUserOrders() {
        mapOrderToCookingData = userOrderService.getUserOrdersByOrderStateIdent(PROCESSED_IDENT)
                .stream()
                .collect(Collectors.toMap(UserOrder::getIdOrder, this::getCookingDataFromUserOrder));
    }


}
