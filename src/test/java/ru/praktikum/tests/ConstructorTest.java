package ru.praktikum.tests;

import ru.praktikum.helpers.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переходы между разделами конструктора")
    @Description("Проверка работы переходов к разделам: «Булки», «Соусы», «Начинки»")
    public void testConstructorSectionNavigation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        // Переход в раздел Соусы
        mainPage.clickSaucesTab();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String activeTab = mainPage.getActiveTabText();
        assertTrue("Вкладка 'Соусы' не активирована! Активна: " + activeTab,
                activeTab.contains("Соусы"));

        // Переход в раздел Начинки
        mainPage.clickFillingsTab();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        activeTab = mainPage.getActiveTabText();
        assertTrue("Вкладка 'Начинки' не активирована! Активна: " + activeTab,
                activeTab.contains("Начинки"));

        // Переход в раздел Булки
        mainPage.clickBunsTab();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        activeTab = mainPage.getActiveTabText();
        assertTrue("Вкладка 'Булки' не активирована! Активна: " + activeTab,
                activeTab.contains("Булки"));
    }
}