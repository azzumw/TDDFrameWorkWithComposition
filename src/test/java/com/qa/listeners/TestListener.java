package com.qa.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.PrintWriter;
import java.io.StringWriter;


public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            System.out.println("onTestFailure...");

            StringWriter sw = new StringWriter();
            PrintWriter printWriter = new PrintWriter(sw);
            result.getThrowable().printStackTrace(printWriter);

            System.out.println(sw);
        }

        System.out.println("test failed");
        ITestListener.super.onTestFailure(result);

//        Map<String ,String > params = new HashMap<>();
//        params = result.getTestContext().getCurrentXmlTest().getAllParameters();

//        String imagePath = "Screenshots" + File.separator + params.get("platformName") + "_" +
//                params.get("platformVersion") + "_" + params.get("deviceName") + File.separator +
//                TestUtils.getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName() +
//                File.separator + result.getName() + ".png";

//            File file = BaseTest.appiumDriver.getScreenshotAs(OutputType.FILE);

//            try {
//                FileUtils.copyFile(file, new File(imagePath));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
    }
}
