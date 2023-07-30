package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @AndroidFindBy (accessibility = "test-Username") private WebElement usernameTextField;
    @AndroidFindBy(accessibility = "test-Password") private WebElement passwordTextField;
    @AndroidFindBy(accessibility = "test-LOGIN") private WebElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private WebElement errorText;

    public final static String username = "standard_user";
    public final static String incorrectUsername = "standarduser";
    public final static String password = "secret_sauce";
    public final static String incorrectPassword = "secretsauce";

    public static final String expectedError = "Username and password do not match any user in this service.";

    private BaseTest baseTest;
    public LoginPage() {
        baseTest = new BaseTest();
        PageFactory.initElements(new AppiumFieldDecorator(BaseTest.appiumDriver),this);
    }

    public LoginPage enterUsername(String username){
        baseTest.sendKeys(usernameTextField,username);
        return this;
    }

    public LoginPage enterPassword(String password){
        baseTest.sendKeys(passwordTextField,password);
        return this;
    }

    public ProductsPage pressLoginBtn(){
        baseTest.click(loginBtn);
        return new ProductsPage();
    }

    public String getErrorText(){
        return errorText.getText();
    }


}
