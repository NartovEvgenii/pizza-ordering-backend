package com.pizza.service.cooking;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class CookingData {
    private Long idOrder;

    private LocalDateTime startOrderTime;

    private Duration cookDuration;

}
