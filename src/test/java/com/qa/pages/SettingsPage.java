package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SettingsPage {
   @AndroidFindBy(accessibility = "test-ALL ITEMS")
    private List<WebElement> menuList;

   @AndroidFindBy(accessibility = "test-LOGOUT")
   private WebElement logoutBtn;

   public SettingsPage(){
       PageFactory.initElements(new AppiumFieldDecorator(BaseTest.appiumDriver),this);
   }

   static class MenuItems{
       @AndroidFindBy(accessibility = "test-LOGOUT")
        private static WebElement logoutBtn;

       void method(){

       }
   }

   public LoginPage pressLogoutBtn(){
       BaseTest.click(logoutBtn);
       return LoginPage.getInstance();
   }
}
