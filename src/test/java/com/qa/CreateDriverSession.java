package com.qa;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

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

    private final static String port = "http://0.0.0.0:4723";
    private final static String apiDemoAppPackage = "io.appium.android.apis";
    private final static String sauceLabAppPackage = "com.swaglabsmobileapp";
    private final static String sauceLabAppActivity = sauceLabAppPackage+".SplashActivity";


    public static AndroidDriver createDriverSession(OS os,DEVICE device,AUTOMATION automation,String apkName) throws Exception {

        if (os == OS.ANDROID) {
            return createCapabilitiesWithUiAutomatorOptions(
                    device.getName(),
                    automation.getAutomationName(),
                    device.getUDID(),
                    getAPKPath(apkName),
                    sauceLabAppPackage,
                    sauceLabAppActivity,
                    device.getAvdName()
            );
        }
        throw new Exception("illegal OS name");
    }

    public static String getAPKPath(String apkName) {
        String rootDir =  System.getProperty("user.dir");
        String apk_Dir = ("/src/test/resources/app/"+apkName).replace('/',File.separatorChar);
        return rootDir + apk_Dir;
    }

    private static AndroidDriver createCapabilitiesWithUiAutomatorOptions(String deviceName,String automationName,String udid,String apkPath,String appPackage,String activity, String avd) throws MalformedURLException {

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options()
                .setDeviceName(deviceName)
                .setAutomationName(automationName)
                .setUdid(udid)
                .setApp(apkPath)
                .setAppPackage(appPackage)
                .setAppActivity(activity)
//                .setUnlockType("pin").setUnlockKey("1234")
                .setAvd(avd)
                .setAvdLaunchTimeout(Duration.ofSeconds(60));


        URL url = new URL(port);

        return new AndroidDriver(url,uiAutomator2Options);

    }

    //    private static DesiredCapabilities createCapabilities(String platformName,String deviceName, String automationName,String deviceUdid,String apkPath,String avd){
//
//        Map<String,String> capabilitiesMap = new HashMap<>();
//
//        capabilitiesMap.put(MobileCapabilityType.PLATFORM_NAME,platformName);
//        capabilitiesMap.put(MobileCapabilityType.DEVICE_NAME, deviceName);
//        capabilitiesMap.put(MobileCapabilityType.AUTOMATION_NAME,automationName);
//        capabilitiesMap.put(MobileCapabilityType.UDID,deviceUdid);
//
//        if(avd!=null) {
//            capabilitiesMap.put("avd",avd);
//            capabilitiesMap.put("avdLaunchTimeout","120000");
//        }
//
//        capabilitiesMap.put("appPackage","io.appium.android.apis");
//        capabilitiesMap.put("appActivity","io.appium.android.apis.ApiDemos");
////        capabilitiesMap.put(MobileCapabilityType.APP,apkPath);
//
//        return new DesiredCapabilities(capabilitiesMap);
//    }
}
