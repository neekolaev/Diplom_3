package ru.praktikum.helpers;

public class TestDataHelper {
    // Используем существующий тестовый аккаунт
    public static final String TEST_EMAIL = "test@example.com";
    public static final String TEST_PASSWORD = "password123";
    public static final String TEST_NAME = "Test User";

    // Для регистрации - генерируем уникальные email
    public static String generateTestEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }
}