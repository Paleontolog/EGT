package com.qa.shop.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.function.Function;


public class WebDriverHelper {
    private static final int ACCEPTABLE_PAUSE = 15;

    private static final long STANDARD_WAIT = 10;
    private static final long POLLING_INTERVAL = 500;

    private static final String siteUrl = "https://www.dns-shop.ru/";

    private static final FluentWait<WebDriver> waiter = new FluentWait<>(BaseSelenium.getWebDriver())
            .withTimeout(Duration.ofSeconds(STANDARD_WAIT))
            .pollingEvery(Duration.ofMillis(POLLING_INTERVAL))
            .ignoring(Exception.class);

    private static final Logger log = LoggerFactory.getLogger(WebDriverHelper.class);

    public static void open(String pageUrl) {
        BaseSelenium.getWebDriver().get(siteUrl + pageUrl);
    }

    private static void highLighterMethod(WebElement element) {
        WebDriver driver = BaseSelenium.getWebDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                element
        );
    }

    public static void click(WebElement webElement) {
        try {
            waitForElementVisible(webElement);
            waitForElementToBeClickable(webElement);
            highLighterMethod(webElement);
            webElement.click();
        } catch (Exception e) {
            log.error("There is some problem with clicking", e);
        } finally {
            log.debug("Clicked");
        }
    }

    public static void sendText(WebElement webElement, String inputText) {
        try {
            waitForElementVisible(webElement);
            waitForElementToBeClickable(webElement);
            highLighterMethod(webElement);
            webElement.clear();
            webElement.sendKeys(inputText);
        } catch (Exception e) {
            System.out.println("Exception in 'Send Keys'");
        }
    }

    public static void sendKeys(WebElement webElement, Keys key) {
        try {
            waitForElementVisible(webElement);
            waitForElementToBeClickable(webElement);
            highLighterMethod(webElement);
            webElement.sendKeys(key);
        } catch (Exception e) {
            System.out.println("Exception in 'Send Keys'");
        }
    }

    public static void waitPageLoad() {
        WebDriver driver = BaseSelenium.getWebDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(ACCEPTABLE_PAUSE))
                    .until(webDriver -> {
                        JavascriptExecutor js = (JavascriptExecutor) webDriver;
                        assert js != null;
                        return js.executeScript("return document.readyState").equals("complete");
                    });
        } catch (Exception e) {
            log.error("Can not wait page loading", e);
        } finally {
            log.debug("Page loaded");
        }
    }

    public static void waitForElementToBeClickable(WebElement element) {
        WebDriver driver = BaseSelenium.getWebDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(ACCEPTABLE_PAUSE))
                    .until(ExpectedConditions.elementToBeClickable(element));
        } finally {
            log.debug("Waiting For Element To Be Clickable");
        }
    }

    public static void waitForElementLocated(By by) {
        WebDriver driver = BaseSelenium.getWebDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(ACCEPTABLE_PAUSE))
                    .until((ExpectedConditions.presenceOfAllElementsLocatedBy(by)));
        } finally {
            log.debug("Waiting For Element To Be Located");
        }
    }

    public static void waitForElementVisible(WebElement webElement) {
        WebDriver driver = BaseSelenium.getWebDriver();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(ACCEPTABLE_PAUSE))
                    .until((ExpectedConditions.visibilityOf(webElement)));
        } finally {
            log.debug("Waiting For Element To Be Visible");
        }
    }

    public static void moveToElement(WebElement element) {
        WebDriver driver = BaseSelenium.getWebDriver();
        try {
            waitForElementVisible(element);
            highLighterMethod(element);
            new Actions(driver).moveToElement(element).perform();
        } catch (Exception e) {
            log.error("There is some problem with moving", e);
        } finally {
            log.info("Cursor moved to element");
        }
    }

    public static void scrollToElement(WebElement webElement) {
        try {
            ((JavascriptExecutor) BaseSelenium.getWebDriver())
                    .executeScript("arguments[0].scrollIntoView(true);", webElement);
        } catch (Exception e) {
            log.error("Problem when scrolling to element", e);
        } finally {
            log.info("Scroll to element");
        }
    }

    public static WebElement findElementBy(WebElement webElement, By by) {
        try {
            return webElement.findElement(by);
        } catch (Exception e) {
            log.error("Error while search element", e);
            throw new NotFoundException("Element not found", e);
        } finally {
            log.info("Find element");
        }
    }

    public static <T> T waitAndGet(Function<WebDriver, T> lambda) {
        return waiter.until(lambda);
    }
}
