package com.qa.pages;

import com.qa.BaseTest;
import com.qa.MenuBar;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {

    @AndroidFindBy(accessibility = "test-BACK TO PRODUCTS")
    private WebElement backButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
    private WebElement productName;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]")
    private WebElement productDescription;

    public ProductDetailPage() {
        PageFactory.initElements(new AppiumFieldDecorator(BaseTest.appiumDriver),this);
    }

    public String getProductName(){
        return productName.getText();
    }

    public String getProductDescription(){
        return productDescription.getText();
    }

    public ProductsPage navigateToProductsPage(){
        BaseTest.click(backButton);
        return new ProductsPage(new MenuBar());
    }

}
