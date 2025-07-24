package ru.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By loginButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    private By personalAccountButton = By.xpath("//p[contains(text(), 'Личный Кабинет')]");
    private By constructorButton = By.xpath("//p[contains(text(), 'Конструктор')]");
    private By logo = By.xpath("//div[contains(@class, 'logo')]");
    private By bunsTab = By.xpath("//span[contains(text(), 'Булки')]");
    private By saucesTab = By.xpath("//span[contains(text(), 'Соусы')]");
    private By fillingsTab = By.xpath("//span[contains(text(), 'Начинки')]");
    private By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открытие главной страницы")
    public MainPage openMainPage() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        return this;
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new LoginPage(driver);
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public Object clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (driver.getCurrentUrl().contains("/login")) {
            return new LoginPage(driver);
        } else {
            return new PersonalAccountPage(driver);
        }
    }

    @Step("Клик по кнопке 'Конструктор'")
    public MainPage clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
        return this;
    }

    @Step("Клик по логотипу")
    public MainPage clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
        return this;
    }

    @Step("Переход к разделу 'Булки'")
    public MainPage clickBunsTab() {
        WebElement bunsElement = wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        bunsElement.click();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    @Step("Переход к разделу 'Соусы'")
    public MainPage clickSaucesTab() {
        WebElement saucesElement = wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        saucesElement.click();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    @Step("Переход к разделу 'Начинки'")
    public MainPage clickFillingsTab() {
        WebElement fillingsElement = wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        fillingsElement.click();
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    @Step("Получение текста активной вкладки")
    public String getActiveTabText() {
        try {
            WebElement activeElement = wait.until(ExpectedConditions.presenceOfElementLocated(activeTab));
            return activeElement.getText();
        } catch (Exception e) {
            try {
                WebElement currentActive = driver.findElement(By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span"));
                return currentActive.getText();
            } catch (Exception ex) {
                try {
                    WebElement bunsElement = driver.findElement(bunsTab);
                    WebElement saucesElement = driver.findElement(saucesTab);
                    WebElement fillingsElement = driver.findElement(fillingsTab);

                    String bunsParentClass = bunsElement.findElement(By.xpath("./..")).getAttribute("class");
                    String saucesParentClass = saucesElement.findElement(By.xpath("./..")).getAttribute("class");
                    String fillingsParentClass = fillingsElement.findElement(By.xpath("./..")).getAttribute("class");

                    if (bunsParentClass.contains("tab_tab_type_current")) {
                        return "Булки";
                    } else if (saucesParentClass.contains("tab_tab_type_current")) {
                        return "Соусы";
                    } else if (fillingsParentClass.contains("tab_tab_type_current")) {
                        return "Начинки";
                    }
                } catch (Exception finalEx) {
                    return "Не удалось определить активную вкладку";
                }
            }
        }
        return "Не удалось определить активную вкладку";
    }

    @Step("Проверка, что главная страница открыта")
    public boolean isMainPageOpened() {
        return driver.getCurrentUrl().equals("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Ожидание загрузки главной страницы")
    public MainPage waitForPageLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        return this;
    }

    @Step("Скролл к элементу")
    private void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}