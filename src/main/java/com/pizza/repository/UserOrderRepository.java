package com.pizza.repository;

import com.pizza.domains.OrderState;
import com.pizza.domains.UserOrder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

    @Query("SELECT uo FROM UserOrder uo WHERE uo.mobUser.idUser = :idUser")
    List<UserOrder> findByMobUserId(Long idUser);

    @Query("SELECT uo FROM UserOrder uo JOIN uo.userOrderHistories oh  " +
            "WHERE uo.orderState.idOrderState = :idOrderState AND oh.orderState.idOrderState = :idOrderState")
    @EntityGraph(attributePaths = {"orderItems", "userOrderHistories", "orderItems.pizza.cookingPlan"})
    Set<UserOrder> findByOrderState(Long idOrderState);
}
