package com.example.Capgi_BankManagementSystem.util;

public final class CommonUtil {

    private CommonUtil() {}

    /**
     * Check if value is null or <= 0
     */
    public static boolean isInvalidAmount(Double amount) {
        return amount == null || amount <= 0;
    }

    /**
     * Safe string check
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}