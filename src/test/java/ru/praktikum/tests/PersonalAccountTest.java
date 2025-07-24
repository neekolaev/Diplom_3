package ru.praktikum.tests;

import ru.praktikum.helpers.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class PersonalAccountTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет для неавторизованного пользователя")
    @Description("Проверка того, что неавторизованный пользователь переходит на страницу входа")
    public void testPersonalAccountRedirectToLogin() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().clickPersonalAccountButton();

        // Неавторизованный пользователь должен попасть на страницу входа
        assertTrue("Не произошел переход на страницу входа",
                driver.getCurrentUrl().contains("/login"));
    }
}