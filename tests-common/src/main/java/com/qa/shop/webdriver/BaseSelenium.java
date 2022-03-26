package com.qa.shop.webdriver;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;

public class BaseSelenium {

    private static WebDriver driver;

    private static final String path = new File("screens").getAbsolutePath();

    public static String getPath() {
        return path;
    }

    public static WebDriver getWebDriver() {
        return driver;
    }


    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\JAVA\\chrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("start-fullscreen");
//        options.addArguments("disable-infobars");
//        options.addArguments("disable-extensions");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
//        options.addArguments("disable-gpu");
//        options.addArguments("disable-dev-shm-usage");
//        options.addArguments("no-sandbox");
//        options.addArguments("user-agent=Mozilla/5.0 (Windows Phone 10.0; Android 4.2.1; Microsoft; Lumia 640 XL LTE) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Mobile Safari/537.36 Edge/12.10166");
//        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public static void close() {
        try {
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            driver = null;
        }
    }
}