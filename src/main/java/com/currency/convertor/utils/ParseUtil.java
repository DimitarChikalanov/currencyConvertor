package com.currency.convertor.utils;

import java.time.LocalDateTime;

public class ParseUtil {


    private ParseUtil() {
    }

    public static LocalDateTime parseTime(String time) {
       // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time);
    }
}
