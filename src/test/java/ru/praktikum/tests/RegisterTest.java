package ru.praktikum.tests;

import ru.praktikum.helpers.BaseTest;
import ru.praktikum.helpers.TestDataHelper;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.pages.MainPage;
import ru.praktikum.pages.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации пользователя")
    public void testSuccessfulRegistration() {
        String userEmail = TestDataHelper.generateTestEmail();
        String password = "password123";
        String name = TestDataHelper.TEST_NAME;

        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage
                .openMainPage()
                .clickLoginButton()
                .clickRegisterLink();

        registerPage
                .enterName(name)
                .enterEmail(userEmail)
                .enterPassword(password)
                .clickRegisterButton();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue("Должен произойти переход на страницу входа",
                driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Проверка ошибки при регистрации с паролем менее 6 символов")
    public void testRegistrationWithShortPassword() {
        String userEmail = TestDataHelper.generateTestEmail();
        String shortPassword = "12345"; // 5 символов
        String name = TestDataHelper.TEST_NAME;

        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = mainPage
                .openMainPage()
                .clickLoginButton()
                .clickRegisterLink();

        registerPage
                .enterName(name)
                .enterEmail(userEmail)
                .enterPassword(shortPassword)
                .clickRegisterButton();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        assertTrue("Должны остаться на странице регистрации",
                driver.getCurrentUrl().contains("/register"));
    }
}