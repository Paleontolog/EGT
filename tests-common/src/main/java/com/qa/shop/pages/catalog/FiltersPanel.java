package com.qa.shop.pages.catalog;

import com.qa.shop.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.qa.shop.webdriver.WebDriverHelper.*;

public class FiltersPanel extends AbstractPageObject {

    @FindBy(xpath ="//div[@data-id='brand']")
    private WebElement brand;

    @FindBy(xpath ="//div[contains(@class, ui-checkbox-group)]//input[@value='acer']/..")
    private WebElement acerComputers;

    @FindBy(xpath = "//button[text()='Применить']")
    private WebElement applyFiltersButton;

    @FindBy(xpath = "//div[@data-id='price']//div[contains(@class, 'ui-input-small')][1]/input")
    private WebElement priceFrom;

    @FindBy(xpath = "//div[@data-id='price']//div[contains(@class, 'ui-input-small')][2]/input")
    private WebElement priceTo;

    public void chooseComputers() {
        scrollToElement(brand);
        moveToElement(brand);
        click(acerComputers);
    }

    public void clickApplyFilters() {
        scrollToElement(applyFiltersButton);
        moveToElement(applyFiltersButton);
        click(applyFiltersButton);
    }

    public void setPriceTo(Integer price) {
        scrollToElement(priceTo);
        moveToElement(priceTo);
        sendText(priceTo, price.toString());
    }

    @Override
    public void isOpen() {

    }

    @Override
    public String getPageUrl() {
        return "";
    }
}
