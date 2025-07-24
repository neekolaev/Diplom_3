package ru.praktikum.tests;

import ru.praktikum.helpers.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class NavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в личный кабинет по клику на «Личный кабинет»")
    public void testNavigationToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().clickPersonalAccountButton();

        // Неавторизованный пользователь должен попасть на страницу входа
        assertTrue("Не произошел переход на страницу входа",
                driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Переход по кнопке «Конструктор»")
    @Description("Проверка перехода по кнопке «Конструктор»")
    public void testNavigationToConstructor() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().clickConstructorButton();

        assertTrue("Не произошел переход на главную страницу",
                mainPage.isMainPageOpened());
    }

    @Test
    @DisplayName("Переход по логотипу")
    @Description("Проверка перехода по логотипу Stellar Burgers")
    public void testNavigationByLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().clickLogo();

        assertTrue("Не произошел переход на главную страницу",
                mainPage.isMainPageOpened());
    }
}