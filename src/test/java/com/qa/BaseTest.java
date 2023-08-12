package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

public class BaseTest {

    public static AppiumDriver appiumDriver;
    protected static Properties properties;

    private static InputStream inputStream;
    private static InputStream inputStreamStrings;

    public static HashMap<String, String> stringHashMap = new HashMap();

    private static final String propfilename = "/config.properties";
    private static final String stringsFile = "/strings/strings.xml";

    public static void initialiseDriver(String platformName) throws Exception {
        try {
            properties = new Properties();

            inputStream = BaseTest.class.getResourceAsStream(propfilename);
            properties.load(inputStream);

            inputStreamStrings = BaseTest.class.getResourceAsStream(stringsFile);

            stringHashMap = TestUtils.parseStringXML(inputStreamStrings);

            appiumDriver = CreateDriverSession.createDriverWithConfigProperties(
                    properties, DEVICE.PIXEL_6_API_33,platformName
            );

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (inputStream != null) inputStream.close();
            if (inputStreamStrings != null) inputStreamStrings.close();

        }

//        String sessionID = appiumDriver.getSessionId().toString();

    }

    public static void waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(TestUtils.WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void click(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    public static void sendKeys(WebElement element, String text) {
        waitForVisibility(element);
        element.sendKeys(text);
    }

    public static String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    public static void quitDriver() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
    }
}
