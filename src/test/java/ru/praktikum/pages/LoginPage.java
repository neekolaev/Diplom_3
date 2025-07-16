package ru.praktikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Упрощенные локаторы
    private By emailInput = By.xpath("//input[@name='name' and @type='text']");
    private By passwordInput = By.xpath("//input[@name='Пароль' and @type='password']");
    private By loginButton = By.xpath("//button[contains(text(), 'Войти')]");
    private By registerLink = By.xpath("//a[contains(text(), 'Зарегистрироваться')]");
    private By forgotPasswordLink = By.xpath("//a[contains(text(), 'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввод email: {email}")
    public LoginPage enterEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailElement.clear();
        emailElement.sendKeys(email);
        return this;
    }

    @Step("Ввод пароля")
    public LoginPage enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordElement.clear();
        passwordElement.sendKeys(password);
        return this;
    }

    @Step("Клик по кнопке 'Войти'")
    public MainPage clickLoginButton() {
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        WebElement loginBtn = null;

        for (WebElement button : buttons) {
            if (button.getText().contains("Войти")) {
                loginBtn = button;
                break;
            }
        }

        if (loginBtn != null) {
            loginBtn.click();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return new MainPage(driver);
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public RegisterPage clickRegisterLink() {
        WebElement registerLinkElement = wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        registerLinkElement.click();
        return new RegisterPage(driver);
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public ForgotPasswordPage clickForgotPasswordLink() {
        WebElement forgotPasswordLinkElement = wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));
        forgotPasswordLinkElement.click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Полный процесс входа")
    public MainPage login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        return clickLoginButton();
    }
}