package com.qa.shop.tests.favourite;

import com.qa.shop.pages.catalog.CatalogPanel;
import com.qa.shop.pages.catalog.FiltersPanel;
import com.qa.shop.pages.main.LoginForm;
import com.qa.shop.pages.main.MainPage;
import com.qa.shop.pages.product.ProductPage;
import com.qa.shop.pages.product.WishList;
import com.qa.shop.tests.AbstractTest;
import io.qameta.allure.Step;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FavouriteTest extends AbstractTest {
    private static MainPage mainPage;
    private static LoginForm loginForm;
    private static CatalogPanel catalogPanel;
    private static FiltersPanel filtersPanel;
    private static ProductPage productPage;
    private static WishList wishList;

    @BeforeTest
    public void beforeTest() {
        mainPage = new MainPage();
        loginForm = new LoginForm();
        catalogPanel = new CatalogPanel();
        filtersPanel = new FiltersPanel();
        productPage = new ProductPage();
        wishList = new WishList();
    }

    @Step("Открыть главную страницу")
    private void openMainPage() {
        mainPage.open();
    }

    @Step("Выбрать в каталоге ноутбуки")
    private void choseNotebooks() {
        catalogPanel.goToNotebooksCatalog();
    }

    @Step("Выбрать ноутбуки 'Acer'")
    private void chooseAcerNotebooks() {
        filtersPanel.chooseComputers();
    }

    @Step("Нажимаем кнопку 'Применить'")
    private void clickApplyFilters() {
        filtersPanel.clickApplyFilters();
    }

    @Step("Выбираем товар")
    private void chooseProduct(String product) {
        catalogPanel.chooseProduct(product);
    }

    @Step("Добавляем продукт в 'Избранное'")
    private void addToFavourites() {
        productPage.addToWishList();
    }

    @Step("Переходим в 'Избранное'")
    private void goToWishList() {
        productPage.goToWishList();
    }

    @Step("Проверяем добавление в 'Избранное'")
    private void checkProductInWishList(String productName) {
        wishList.containsProduct(productName);
    }

    @Step("Очистить 'Избранное'")
    private void cleanWishList() throws InterruptedException {
        wishList.cleanWishList();
    }

    @Test(description = "Добавление в избранное")
    @Parameters(value = {"productName"})
    public void favouriteTest(String productName) {
        openMainPage();
        choseNotebooks();
        chooseAcerNotebooks();
        clickApplyFilters();
        chooseProduct(productName);
        addToFavourites();
        goToWishList();
        checkProductInWishList(productName);
    }

    @AfterTest
    public void afterTest() throws InterruptedException {
        cleanWishList();
    }
}
