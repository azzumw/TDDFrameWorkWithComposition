package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement productsPageTitle;

    public static final String pageTitle = "PRODUCTS";

    public ProductsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(BaseTest.appiumDriver),this);
    }

    public String getTitle(){
        return productsPageTitle.getText();
    }
}
