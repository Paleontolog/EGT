package com.qa.shop.pages.product;

import com.qa.shop.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.qa.shop.webdriver.WebDriverHelper.*;

public class WishList extends AbstractPageObject {

    @FindBy(xpath = "//div[contains(@class, 'catalog-products')]//div[@data-id='product']")
    private List<WebElement> wishList;

    @FindBy(xpath = "//div[@class='profile-wishlist-management__select-all']")
    private WebElement selectAllCheck;

    @FindBy(xpath = "//button[contains(@class, 'controls_delete')]")
    private WebElement clearAllBtn;

    @FindBy(xpath = "//div[contains(@class, 'profile-wishlist__remove-modal-buttons')]/div/..")
    private WebElement confirmDelete;


    public void containsProduct(String productName) {
        findInList(wishList, prod -> prod.getText().contains(productName));
    }

    public void cleanWishList() throws InterruptedException {
        moveToElement(selectAllCheck);
        click(selectAllCheck);
        moveToElement(clearAllBtn);
        click(clearAllBtn);
        click(confirmDelete);
        waitPageLoad();
        Thread.sleep(2000);
    }

    @Override
    public void isOpen() {

    }

    @Override
    public String getPageUrl() {
        return "";
    }
}
