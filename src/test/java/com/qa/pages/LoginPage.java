package com.qa.pages;

import com.qa.BaseTest;
import com.qa.MenuBar;
import com.qa.tests.ProductsTests;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private static LoginPage loginPage;

    @AndroidFindBy (accessibility = "test-Username") private WebElement usernameTextField;
    @AndroidFindBy(accessibility = "test-Password") private WebElement passwordTextField;
    @AndroidFindBy(accessibility = "test-LOGIN") private WebElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private WebElement errorText;

//    public LoginPage() {
//
//    }

    public static LoginPage getInstance(){
        PageFactory.initElements(new AppiumFieldDecorator(BaseTest.appiumDriver),LoginPage.class);
        if( loginPage != null){
            return loginPage;
        }

        loginPage = new LoginPage();
        return loginPage;
    }

    public LoginPage enterUsername(String username){
        BaseTest.sendKeys(usernameTextField,username);
        return this;
    }

    public LoginPage enterPassword(String password){
        BaseTest.sendKeys(passwordTextField,password);
        return this;
    }

    public ProductsPage pressLoginBtn(){
        BaseTest.click(loginBtn);
        return new ProductsPage(new MenuBar());
    }

    public ProductsPage login(String username,String password){
        enterUsername(username);
        enterPassword(password);
        return pressLoginBtn();
    }

    public String getErrorText(){
        return errorText.getText();
    }

    public WebElement getLoginBtn(){
        return loginBtn;
    }
}
