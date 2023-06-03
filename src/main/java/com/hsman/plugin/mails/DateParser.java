package com.hsman.plugin.mails;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DateParser {
    private static final Map<String, Long> TIME_UNIT_MAP = new HashMap<>();
    static {
        TIME_UNIT_MAP.put("y", TimeUnit.DAYS.toSeconds(365L));
        TIME_UNIT_MAP.put("year", TimeUnit.DAYS.toSeconds(365L));
        TIME_UNIT_MAP.put("years", TimeUnit.DAYS.toSeconds(365L));
        TIME_UNIT_MAP.put("m", TimeUnit.DAYS.toSeconds(30L));
        TIME_UNIT_MAP.put("mon", TimeUnit.DAYS.toSeconds(30L));
        TIME_UNIT_MAP.put("months", TimeUnit.DAYS.toSeconds(30L));
        TIME_UNIT_MAP.put("d", TimeUnit.DAYS.toSeconds(1L));
        TIME_UNIT_MAP.put("day", TimeUnit.DAYS.toSeconds(1L));
        TIME_UNIT_MAP.put("days", TimeUnit.DAYS.toSeconds(1L));
        TIME_UNIT_MAP.put("h", TimeUnit.HOURS.toSeconds(1L));
        TIME_UNIT_MAP.put("hour", TimeUnit.HOURS.toSeconds(1L));
        TIME_UNIT_MAP.put("hours", TimeUnit.HOURS.toSeconds(1L));
        TIME_UNIT_MAP.put("min", TimeUnit.MINUTES.toSeconds(1L));
        TIME_UNIT_MAP.put("minute", TimeUnit.MINUTES.toSeconds(1L));
        TIME_UNIT_MAP.put("minutes", TimeUnit.MINUTES.toSeconds(1L));
        TIME_UNIT_MAP.put("s", TimeUnit.SECONDS.toSeconds(1L));
        TIME_UNIT_MAP.put("sec", TimeUnit.SECONDS.toSeconds(1L));
        TIME_UNIT_MAP.put("seconds", TimeUnit.SECONDS.toSeconds(1L));
    }
    public static long parse(String input) {
        long totalSeconds = 0L;
        long currentNumber = 0L;
        String currentUnit = "";
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            } else {
                currentUnit += c;
                if (TIME_UNIT_MAP.containsKey(currentUnit)) {
                    totalSeconds += currentNumber * TIME_UNIT_MAP.get(currentUnit);
                    currentNumber = 0L;
                    currentUnit = "";
                }
            }
        }
        if (!currentUnit.isEmpty()) {
            throw new IllegalArgumentException("Invalid input: " + input);
        }
        return totalSeconds;
    }
}