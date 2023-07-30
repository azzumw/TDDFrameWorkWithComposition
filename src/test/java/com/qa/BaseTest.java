package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static AppiumDriver appiumDriver;
    protected Properties properties;
    private InputStream inputStream;
    private static final String propfilename = "/config.properties";

    public void initialiseDriver() throws Exception {

        try {
            properties = new Properties();

            inputStream = getClass().getResourceAsStream(propfilename);
            properties.load(inputStream);

            appiumDriver = CreateDriverSession.createDriverWithConfigProperties(
                    properties,DEVICE.PIXEL_6_API_33
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        String sessionID = appiumDriver.getSessionId().toString();

    }

//    public void setAppiumDriver(AppiumDriver driver){
//        this.appiumDriver = driver;
//    }

    public void waitForVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void click(WebElement element){
        waitForVisibility(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement element,String attribute){
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public void quitDriver(){
        if(appiumDriver!=null){
            appiumDriver.quit();
        }
    }
}
