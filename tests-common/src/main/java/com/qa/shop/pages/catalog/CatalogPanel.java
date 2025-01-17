package com.qa.shop.pages.catalog;

import com.qa.shop.pages.AbstractPageObject;
import com.qa.shop.webdriver.BaseSelenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.qa.shop.webdriver.WebDriverHelper.*;

public class CatalogPanel extends AbstractPageObject {

    private static final String productNamePattern = ".//a[text() = '%s']";

    private static final String containerPath = "//div[@class='menu-desktop']";

    private static final String catalogProducts = "//div[contains(@class, 'catalog-products')]";

    private static final String productNamePath = ".//a[contains(@class, 'product__name')]";

    private static final String productPrice = ".//div[@class='product-buy__price']";

    private static final Pattern onlyNumber = Pattern.compile("[^\\d]");

    private static final By btnNextPage = By.xpath("//a[contains(@class, '__page-link_next')]");

    @FindBy(xpath = containerPath)
    private WebElement container;

    @FindBy(xpath = catalogProducts)
    private WebElement catalog;

    @FindBy(xpath = containerPath)
    private WebElement productContainer;

    @FindBy(xpath = catalogProducts + "//div[@data-id='product']")
    private List<WebElement> products;

    private static Integer parsePrice(String price) {
        String cleanString = onlyNumber.matcher(price).replaceAll("");
        return Integer.parseInt(cleanString);
    }

    private boolean nextPage() {
        if (isElementExist(btnNextPage)) {
            moveToElement(findElement(btnNextPage));
            boolean hasNextPage = !findElement(btnNextPage).getAttribute("class").contains("disabled");
            if (hasNextPage) {
                click(findElement(btnNextPage));
            }
            return hasNextPage;
        } else {
            return false;
        }
    }


    public void moveChainElement(WebElement startElement, String ...chain) {
        WebElement element = startElement;
        for (String name : chain) {
            final String productPath = String.format(productNamePattern, name);
            element = findElementBy(startElement, By.xpath(productPath));
            moveToElement(element);
        }
        click(element);
    }

    public void goToNotebooksCatalog() {
        moveChainElement(productContainer, "Компьютеры", "Ноутбуки", "Игровые");
    }

    public void goToTablets() {
        moveChainElement(productContainer,"Смартфоны и гаджеты", "Планшеты", "LTE");
    }

    public void chooseProduct(String productName) {
        WebElement product = findInList(products, prod ->
                findElementBy(prod, By.xpath(productNamePath))
                        .getText()
                        .contains(productName));

        scrollToElement(product);
        moveToElement(product);
        click(product);
    }

    private Integer getPrice(WebElement product) {
        String priceText = findElementBy(product, By.xpath(productPrice)).getText();
        return parsePrice(priceText);
    }

    public void checkProductPrice(Function<Integer, Boolean> checkPrice) {
        do {
            Assert.assertFalse(products.isEmpty(), "Can not find products");
            boolean allPriceMatched = waitAndGet(driver ->
                    products.stream()
                            .map(this::getPrice)
                            .allMatch(checkPrice::apply)
            );
            Assert.assertTrue(allPriceMatched, "Has unmatched price");

        } while (nextPage());
    }

    @Override
    public void isOpen() {

    }

    @Override
    public String getPageUrl() {
        return "";
    }
}
