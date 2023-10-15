package com.pizza.utils;

import java.time.LocalTime;

public class LocalTimeUtils {

    public static Long getSecondDuration(LocalTime time) {
        long h = time.getHour();
        long m = time.getMinute();
        long s = time.getSecond();
        return  (h * 3600) + (m * 60) + s;
    }
}
