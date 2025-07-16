package ru.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private By nameInput = By.xpath("(//input[@name='name' and @type='text'])[1]");
    private By emailInput = By.xpath("(//input[@name='name' and @type='text'])[2]");
    private By passwordInput = By.xpath("//input[@name='Пароль' and @type='password']");
    private By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    private By loginLink = By.xpath("//a[text()='Войти']");
    private By errorMessage = By.xpath("//p[contains(@class, 'input__error')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Ввод имени: {name}")
    public RegisterPage enterName(String name) {
        WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        nameElement.clear();
        nameElement.sendKeys(name);
        return this;
    }

    @Step("Ввод email: {email}")
    public RegisterPage enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailElement.clear();
        emailElement.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public RegisterPage enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordElement.clear();
        passwordElement.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public LoginPage clickRegisterButton() {
        // Ищем кнопку среди всех button элементов
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        WebElement registerBtn = null;

        for (WebElement button : buttons) {
            if (button.getText().contains("Зарегистрироваться")) {
                registerBtn = button;
                break;
            }
        }

        if (registerBtn != null) {
            // Простой клик
            registerBtn.click();
        } else {
            throw new RuntimeException("Кнопка 'Зарегистрироваться' не найдена");
        }

        // Ждем немного для обработки
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return new LoginPage(driver);
    }

    @Step("Клик по ссылке 'Войти'")
    public LoginPage clickLoginLink() {
        WebElement loginLinkElement = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        loginLinkElement.click();
        return new LoginPage(driver);
    }

    @Step("Получение текста ошибки")
    public String getErrorMessage() {
        try {
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return errorElement.getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Проверка наличия ошибки")
    public boolean isErrorDisplayed() {
        try {
            List<WebElement> errors = driver.findElements(By.xpath("//*[contains(@class, 'input__error')]"));
            if (!errors.isEmpty()) {
                return errors.get(0).isDisplayed();
            }

            // Альтернативный поиск ошибок
            errors = driver.findElements(By.xpath("//*[contains(text(), 'Некорректный')]"));
            if (!errors.isEmpty()) {
                return errors.get(0).isDisplayed();
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}