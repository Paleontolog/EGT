package com.qa.shop.pages;

import com.qa.shop.webdriver.BaseSelenium;
import com.qa.shop.webdriver.WebDriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.qa.shop.webdriver.WebDriverHelper.*;

public abstract class AbstractPageObject {

    private static final Logger log = LoggerFactory.getLogger(AbstractPageObject.class);

    @FindBy(xpath = "//*[@id='header-search']//a[contains(@class, 'cart-link')]")
    private WebElement basketButton;

    @FindBy(xpath = "//*[@id='header-search']//div[@class='header__login']/button")
    private WebElement logInButton;

    @FindBy(xpath = "//div[@class = 'header-profile__level']")
    private WebElement accountInfo;

    @FindBy(xpath = "//div[@class='header-profile__menu']//span[contains(@class, 'profile__username')]")
    private WebElement accountName;

    @FindBy(xpath = "//*[@class='header__login']//a[@href='/logout/']")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[@class='ui-link wishlist-link']")
    private WebElement wishList;


    protected AbstractPageObject() {
        PageFactory.initElements(BaseSelenium.getWebDriver(), this);
    }

    public abstract void isOpen();

    public abstract String getPageUrl();

    public void open() {
        WebDriverHelper.open(getPageUrl());
    }

    public void startLogin() {
        click(logInButton);
    }


    public void checkLoggedIn(String username) {
        moveToElement(accountInfo);
        waitForElementVisible(accountName);
        String name = accountName.getText();
        Assert.assertEquals(name, username);
    }


    public void logout() {
        moveToElement(accountInfo);
        click(logOutButton);
    }


    public void goToWishList() {
        waitForElementToBeClickable(wishList);
        click(wishList);
    }


    public WebElement findInList(List<WebElement> elements, Function<WebElement, Boolean> exp) {
        Optional<WebElement> product = elements.stream()
                .filter(exp::apply)
                .findFirst();

        if (!product.isPresent()) {
            Assert.fail("Element not found");
        }

        return product.get();
    }


    protected  <T> T waitAndGet(Function<WebDriver, T> lambda) {
        return WebDriverHelper.waitAndGet(lambda);
    }
}