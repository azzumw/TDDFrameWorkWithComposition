package com.qa.pages;

import com.qa.BaseTest;
import com.qa.MenuBar;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
    private WebElement productsPageTitle;

    @AndroidFindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]")
    private WebElement product1;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
    private WebElement product1Name;

    private MenuBar menuBar;

    public ProductsPage(MenuBar menuBar) {
        PageFactory.initElements(new AppiumFieldDecorator(BaseTest.appiumDriver),this);
        this.menuBar =  menuBar;
    }

    public String getTitle(){
        return productsPageTitle.getText();
    }

    public ProductDetailPage pressProduct1(){
        BaseTest.click(product1);
        return new ProductDetailPage();
    }

    public String getProductName(){
        return product1Name.getText();
    }

    public ProductDetailPage pressProductWithTitle(String productTitle){
        WebElement element = (WebElement) AppiumBy.ByAndroidUIAutomator.name(productTitle);
        return new ProductDetailPage();
    }

    public SettingsPage pressDrawerMenu(){
       return menuBar.click();
    }

}
