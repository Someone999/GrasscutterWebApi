package com.hsman.utils;

import java.security.SecureRandom;
public class RandomStringUtils {
    private static final SecureRandom secureRandom = new SecureRandom();
    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomInt = secureRandom.nextInt(126 - 32 + 1) + 32;
            char randomChar = (char) randomInt;
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
    public static String[] generateRandomStrings(int length, int count) {
        String[] randomStrings = new String[count];
        for (int i = 0; i < count; i++) {
            randomStrings[i] = generateRandomString(length);
        }
        return randomStrings;
    }
}