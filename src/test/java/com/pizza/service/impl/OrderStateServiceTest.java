package com.pizza.service.impl;

import com.pizza.domains.OrderState;
import com.pizza.repository.OrderStateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderStateServiceTest {
    @Autowired
    private OrderStateServiceImpl orderStateService;
    @MockBean
    private OrderStateRepository orderStateRepository;

    @Test
    public void getOrderStateByIdentifier() {

        OrderState orderState = OrderState.builder()
                .idOrderState(1L)
                .identifier("TITLE")
                .build();
        when(orderStateRepository.findByIdentifier("TITLE"))
                .thenReturn(Optional.of(orderState));

        OrderState orderStateRes = orderStateService.getOrderStateByIdentifier("TITLE");

        assertEquals(orderStateRes, orderState);

        orderStateRes = orderStateService.getOrderStateByIdentifier("TITLE");

        assertEquals(orderStateRes, orderState);

        verify(orderStateRepository, times(1)).findByIdentifier("TITLE");
    }
}