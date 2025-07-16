package ru.praktikum.tests;

import ru.praktikum.helpers.BaseTest;
import ru.praktikum.helpers.TestDataHelper;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка входа через кнопку «Войти в аккаунт» на главной странице")
    public void testLoginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().clickLoginButton();

        assertTrue("Не произошел переход на страницу входа",
                driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка входа через кнопку «Личный кабинет»")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().clickPersonalAccountButton();

        assertTrue("Не произошел переход на страницу входа",
                driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через кнопку «Войти» в форме регистрации")
    public void testLoginFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickLoginButton()
                .clickRegisterLink()
                .clickLoginLink();

        assertTrue("Не произошел переход на страницу входа",
                driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через кнопку «Войти» в форме восстановления пароля")
    public void testLoginFromForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage()
                .clickLoginButton()
                .clickForgotPasswordLink()
                .clickLoginLink();

        assertTrue("Не произошел переход на страницу входа",
                driver.getCurrentUrl().contains("/login"));
    }
}