package com.qa.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class LoginPage {

    @AndroidFindBy (accessibility = "test-Username") private WebElement usernameTextField;
    @AndroidFindBy(accessibility = "test-Password") private WebElement passwordTextField;
    @AndroidFindBy(accessibility = "test-LOGIN") private WebElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private WebElement errorText;

    public static final String expectedError = "Username and password do not match any user in this service.";

    public LoginPage enterUsername(String username){

        return this;
    }

    public LoginPage enterPassword(String password){

        return this;
    }

    public ProductsPage pressLoginBtn(){

        return new ProductsPage();
    }

    public String getErrorText(){
        return errorText.getText();
    }


}
