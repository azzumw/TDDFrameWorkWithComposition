package com.qa;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

enum OS{
 ANDROID,IOS
}

enum DEVICE{

    PIXEL_6_API_33("emulator-5554"),
    IPHONE_SE("F965DD92-5FA1-4B04-BF5F-27008CC82F9B");

    private final String UDID;

    private DEVICE(String udid){
        this.UDID = udid;
    }

    public  String getUDID(){
        return this.UDID;
    }

    public String getName(){
        if (this.name() == IPHONE_SE.name()){
            return "iPhone SE";
        } else {
            return "pixel_6";
        }
    }

    public String getAvdName(){
        if(this == PIXEL_6_API_33){
            return "Pixel_6_API_33";
        }

        return null;
    }
}

enum AUTOMATION{

    UIAUTOMATOR2("uiautomator2"),
    XCUITEST("XCUITest"),
    ESPRESSO("espresso");

    private final String automationName;

    private AUTOMATION(String name){
        this.automationName = name;
    }

    public String getAutomationName() {
        return this.automationName;
    }
}

public class CreateDriverSession {

    public static String getAPKPath(String apkName) {
        String rootDir =  System.getProperty("user.dir");
        String apk_Dir = ("/src/test/resources/app/"+apkName).replace('/',File.separatorChar);
        return rootDir + apk_Dir;
    }

    public static AndroidDriver createDriverWithConfigProperties(Properties properties, DEVICE device) throws MalformedURLException {

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName(device.getName())
                .setAutomationName(properties.getProperty("androidAutomationName"))
                .setUdid(device.getUDID())
                .setApp(getAPKPath(properties.getProperty("androidApk")))
                .setAppPackage(properties.getProperty("androidAppPackage"))
                .setAppActivity(properties.getProperty("androidAppActivity"))
                .setAvd(device.getAvdName())
                .setAvdLaunchTimeout(Duration.ofSeconds(60));
//                .setUnlockType("pin").setUnlockKey("1234")

        return new AndroidDriver(new URL(properties.getProperty("appiumUrl")),uiAutomator2Options);
    }
}
