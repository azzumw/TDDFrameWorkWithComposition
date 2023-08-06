package com.qa;

import com.qa.utils.TestUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;

public class CustomSoftAssert extends SoftAssert {

    private final String testClassName;
    private final String testMethodName;

    public CustomSoftAssert(String className, String testMethodName) {
        this.testClassName = className;
        this.testMethodName = testMethodName;
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {

        if (BaseTest.appiumDriver != null) {
            System.out.println("CustomSoftAssert: taking screenshot");
            File file = BaseTest.appiumDriver.getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(file, new File(TestUtils.getImagePath(testClassName, testMethodName, DEVICE.PIXEL_6_API_33.getName(), "Android")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("CustomSoftAssert: NULL appium dirver");
        }
    }
}
