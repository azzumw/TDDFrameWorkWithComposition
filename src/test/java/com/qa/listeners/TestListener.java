package com.qa.listeners;

import com.qa.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter printWriter = new PrintWriter(sw);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println(sw);
        }

        File file = BaseTest.appiumDriver.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(file,new File("SampleSrc.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ITestListener.super.onTestFailure(result);
    }
}
