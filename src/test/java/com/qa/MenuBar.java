package com.qa;

import com.qa.pages.SettingsPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuBar {
    static class MenuItem{

    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    private  WebElement menuDrawerIcon;

    public MenuBar() {
        PageFactory.initElements(new AppiumFieldDecorator(BaseTest.appiumDriver),this);
    }

    public SettingsPage click(){
        menuDrawerIcon.click();
        return new SettingsPage();
    }
}
