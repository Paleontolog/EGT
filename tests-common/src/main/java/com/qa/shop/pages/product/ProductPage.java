package com.qa.shop.pages.product;

import com.qa.shop.pages.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.qa.shop.webdriver.WebDriverHelper.*;

public class ProductPage extends AbstractPageObject {

    private static final String modalWindow = "//div[@class='base-modal__container']//a[text()='Понятно, закрыть']";

    private static final String productCard = "//div[@class = 'product-card-top product-card-top_full']";

    private static final String activeWishListButton = "//button[contains(@class, 'wishlist-btn button-ui_done')]";

    @FindBy(xpath = "//h1[@class='product-card-top__title']")
    private WebElement pageTop;

    @FindBy(xpath = productCard + "//button[contains(@class, 'wishlist-btn')]")
    private WebElement wishListBtn;


    @FindBy(xpath = "//*")
    private WebElement mainWindow;

    public void addToWishList() {
        scrollToElement(pageTop);
        moveToElement(pageTop);
        moveToElement(wishListBtn);
        click(wishListBtn);
        waitForElementLocated(By.xpath(activeWishListButton));
        sendKeys(mainWindow, Keys.ESCAPE);
    }

    @Override
    public void isOpen() {

    }

    @Override
    public String getPageUrl() {
        return "";
    }
}
