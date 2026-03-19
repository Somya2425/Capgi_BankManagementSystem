package com.example.Capgi_BankManagementSystem.constants;

/**
 * Centralized API endpoints
 */
public final class ApiPaths {

    private ApiPaths() {} // Prevent instantiation

    public static final String BASE = "/api/v1";

    // ================= CUSTOMER =================
    public static final String CUSTOMER = BASE + "/customers";

    // ================= ACCOUNT =================
    public static final String ACCOUNT = BASE + "/accounts";

    // ================= TRANSACTION =================
    public static final String TRANSACTION = BASE + "/transactions";

    // ================= LOAN =================
    public static final String LOAN = BASE + "/loans";
}