package com.qa.shop.pages.main;

import com.qa.shop.pages.AbstractPageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.qa.shop.webdriver.WebDriverHelper.click;
import static com.qa.shop.webdriver.WebDriverHelper.sendText;

public class LoginForm extends AbstractPageObject {

    private static final String containerPath = "//div[@class='auth-modal-render-container']";

    @FindBy(xpath = containerPath + "//div[contains(text(), 'Войти с паролем')]")
    private WebElement btnUsePassword;

    @FindBy(xpath = containerPath + "//input[contains(@autocomplete,'username')]")
    private WebElement inputUserName;

    @FindBy(xpath = containerPath + "//input[contains(@autocomplete,'current-password')]")
    private WebElement inputPassword;

    @FindBy(xpath = containerPath + "//div[@class='base-main-button']//div[contains(text(), 'Войти')]")
    private WebElement loginButton;

    @Override
    public void isOpen() {

    }

    @Override
    public String getPageUrl() {
        return "";
    }


    public void pressBtnUsePassword() {
        click(btnUsePassword);
    }

    public void enterUserName(String email) {
        sendText(inputUserName, email);
    }

    public void enterUserPass(String password) {
        sendText(inputPassword, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        pressBtnUsePassword();
        enterUserName(username);
        enterUserPass(password);
        clickLoginButton();
    }
}
