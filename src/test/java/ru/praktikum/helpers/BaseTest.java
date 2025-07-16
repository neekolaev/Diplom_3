package ru.praktikum.helpers;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        initDriver();
    }

    @Step("Инициализация драйвера")
    private void initDriver() {
        String browserName = System.getProperty("browser", "chrome");

        switch (browserName.toLowerCase()) {
            case "chrome":
                setChromeDriverPath("src/test/resources/drivers/chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(chromeOptions);
                System.out.println("✅ Запущен Chrome");
                break;

            case "yandex":
                setChromeDriverPath("src/test/resources/drivers/chromedriver_136.exe");
                String yandexPath = "C:\\Users\\Nik\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

                if (new File(yandexPath).exists()) {
                    ChromeOptions yandexOptions = new ChromeOptions();
                    yandexOptions.setBinary(yandexPath);
                    yandexOptions.addArguments("--disable-dev-shm-usage");
                    yandexOptions.addArguments("--no-sandbox");
                    yandexOptions.addArguments("--disable-extensions");
                    yandexOptions.addArguments("--remote-allow-origins=*");

                    driver = new ChromeDriver(yandexOptions);
                    System.out.println("✅ Запущен Яндекс.Браузер");
                } else {
                    throw new IllegalArgumentException("Яндекс.Браузер не найден по пути: " + yandexPath);
                }
                break;

            default:
                throw new IllegalArgumentException("Браузер " + browserName + " не поддерживается. Используйте 'chrome' или 'yandex'.");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @Step("Задаем путь к ChromeDriver")
    private void setChromeDriverPath(String driverPath) {
        File driverFile = new File(driverPath);
        if (!driverFile.exists()) {
            throw new IllegalStateException("ChromeDriver не найден по пути: " + driverPath);
        }

        System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
        System.out.println("🔧 Путь к ChromeDriver: " + driverFile.getAbsolutePath());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                try {
                    Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
                    Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
                    Runtime.getRuntime().exec("taskkill /F /IM browser.exe");
                } catch (Exception ex) {
                    System.out.println("⚠️ Не удалось завершить процессы драйвера");
                }
            }
        }
    }
}