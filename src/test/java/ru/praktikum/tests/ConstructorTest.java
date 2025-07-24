package ru.praktikum.tests;

import ru.praktikum.helpers.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.praktikum.pages.MainPage;
import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    @Description("Проверка работы перехода к разделу «Соусы» в конструкторе")
    public void testGoToSaucesSection() {
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
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    @Description("Проверка работы перехода к разделу «Начинки» в конструкторе")
    public void testGoToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        // Переход в раздел Начинки
        mainPage.clickFillingsTab();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String activeTab = mainPage.getActiveTabText();
        assertTrue("Вкладка 'Начинки' не активирована! Активна: " + activeTab,
                activeTab.contains("Начинки"));
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    @Description("Проверка работы перехода к разделу «Булки» в конструкторе")
    public void testGoToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();

        // Для чистоты теста сначала перейдем в другой раздел
        mainPage.clickSaucesTab();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Переход в раздел Булки
        mainPage.clickBunsTab();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String activeTab = mainPage.getActiveTabText();
        assertTrue("Вкладка 'Булки' не активирована! Активна: " + activeTab,
                activeTab.contains("Булки"));
    }
}