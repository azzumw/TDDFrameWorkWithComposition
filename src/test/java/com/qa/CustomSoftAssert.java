package com.qa;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

public class CustomSoftAssert extends SoftAssert {
    private static int n = 0;
    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        System.out.println("Assert Fail " + ++n);

        if(BaseTest.appiumDriver!= null){
            System.out.println("CustomSoftAssert: taking screenshot");
            File file = BaseTest.appiumDriver.getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(file,new File("img"+ n +".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("CustomSoftAssert: NULL appium dirver");
        }
    }
}
