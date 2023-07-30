package com.qa.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
         if (result.getThrowable() != null){
             StringWriter sw = new StringWriter();
             PrintWriter printWriter = new PrintWriter(sw);
             result.getThrowable().printStackTrace(printWriter);
             System.out.println(sw);
         }
        ITestListener.super.onTestFailure(result);
    }
}
