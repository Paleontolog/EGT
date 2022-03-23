package com.qa.shop.tests.filters;

import com.qa.shop.pages.catalog.CatalogPanel;
import com.qa.shop.pages.catalog.FiltersPanel;
import com.qa.shop.pages.main.MainPage;
import com.qa.shop.tests.AbstractTest;
import io.qameta.allure.Step;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FiltersTest extends AbstractTest {
    private static MainPage mainPage;
    private static CatalogPanel catalogPanel;
    private static FiltersPanel filtersPanel;

    @BeforeTest
    public void beforeTest() {
        mainPage = new MainPage();
        catalogPanel = new CatalogPanel();
        filtersPanel = new FiltersPanel();
    }

    @DataProvider(name = "price")
    public Object[][] provideData() {

        return new Object[][] {
                { 15000 },
                { 20000 },
                { 25000 }
        };
    }

    @Step("Открыть главную страницу")
    private void openMainPage() {
        mainPage.open();
    }

    @Step("Перейти в раздел 'Планшеты'")
    private void goToTablets() {
        catalogPanel.goToTablets();
    }

    @Step("Устанавливаем максимальную цену")
    private void setPriceTo(Integer priceTo) {
        filtersPanel.setPriceTo(priceTo);
    }


    @Step("Нажимаем кнопку 'Применить'")
    private void clickApplyFilters() {
        filtersPanel.clickApplyFilters();
    }

    @Step("Проверяем что цены меньше")
    private void checkProductPrice(Integer maxPrice) {
        catalogPanel.checkProductPrice(price -> price < maxPrice);
    }

    @Test(description = "Проверка работы фильтров", dataProvider = "price")
    public void favouriteTest(Integer price) {
        openMainPage();
        goToTablets();
        setPriceTo(price);
        clickApplyFilters();
        checkProductPrice(price);
    }

    @AfterTest
    public void afterTest() {
    }
}
