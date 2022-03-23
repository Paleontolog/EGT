package com.qa.shop.tests.main;

import com.qa.shop.pages.main.LoginForm;
import com.qa.shop.pages.main.MainPage;
import com.qa.shop.tests.AbstractTest;
import io.qameta.allure.Step;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoggingInTest extends AbstractTest {
    private static MainPage mainPage;
    private static LoginForm loggingForm;

    @BeforeTest
    public void beforeTest() {
        mainPage = new MainPage();
        loggingForm = new LoginForm();
    }

    @Step("Открыть главную страницу")
    private void openMainPage() {
        mainPage.open();
    }

    @Step("Нажимаем кнопку 'Войти'")
    private void startLogin() {
        mainPage.startLogin();
    }

    @Step("Выбираем способ входа через логнин и пароль")
    private void pressBtnUsePassword() {
        loggingForm.pressBtnUsePassword();
    }

    @Step("Вводим имя пользователя {username}")
    private void enterUserName(String username) {
        loggingForm.enterUserName(username);
    }

    @Step("Вводим пароль пользователя")
    private void enterUserPass(String password) {
        loggingForm.enterUserPass(password);
    }

    @Step("Кликаем на кнопку 'Вход'")
    private void clickLoginButton() {
        loggingForm.clickLoginButton();
    }

    @Step(value = "Проверяем вход в аккаунт")
    private void checkAccountName(String userName) {
        mainPage.checkLoggedIn(userName);
    }

    @Step(value = "Выход из профиля")
    private void clickLogout() {
        mainPage.logout();
    }

    @Test(description = "Авторизация через логин и пароль")
    @Parameters({"login", "password", "userName"})
    public void checkLogin(String login, String password, String userName) {
        openMainPage();
        startLogin();
        pressBtnUsePassword();
        enterUserName(login);
        enterUserPass(password);
        clickLoginButton();
        checkAccountName(userName);
    }


    @AfterTest
    public void afterTest() {
        clickLogout();
    }
}
