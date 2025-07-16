package ru.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By logoutButton = By.xpath("//button[contains(text(), 'Выход')]");
    private By constructorButton = By.xpath("//p[contains(text(), 'Конструктор')]");
    private By logo = By.xpath("//div[contains(@class, 'logo')]");
    private By profileButton = By.xpath("//a[contains(text(), 'Профиль')]");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Клик по кнопке 'Выход'")
    public LoginPage clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Конструктор'")
    public MainPage clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
        return new MainPage(driver);
    }

    @Step("Клик по логотипу")
    public MainPage clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
        return new MainPage(driver);
    }

    @Step("Проверка открытия личного кабинета")
    public boolean isPersonalAccountOpened() {
        return driver.getCurrentUrl().contains("/account/profile");
    }
}