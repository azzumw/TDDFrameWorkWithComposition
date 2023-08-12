package com.qa.tests;

import com.qa.BaseTest;
import com.qa.CustomSoftAssert;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.TestUtils;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.lang.reflect.Method;


public class LoginTests {

    //subject under test
    private LoginPage loginPage;

    private InputStream inputStream;
    private JSONObject jsonObjLoginUsers;

    private SoftAssert softAssert;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String datafilename = "/data/loginUsers.json";
            inputStream = getClass().getResourceAsStream(datafilename);
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            jsonObjLoginUsers = new JSONObject(jsonTokener);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @AfterClass
    public void afterClass() {
    }

    @Parameters({"platformName","platformVersion","deviceName"})
    @BeforeMethod
    public void setUp(@Optional String platformName, @Optional String platformVersion, @Optional String deviceName, Method method) throws Exception {
        BaseTest.initialiseDriver(platformName);

        loginPage = new LoginPage();

        softAssert = new CustomSoftAssert(getClass().getSimpleName(), method.getName());

        ((CanRecordScreen)BaseTest.appiumDriver).startRecordingScreen();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        TestUtils.captureVideo(result,((CanRecordScreen)BaseTest.appiumDriver).stopRecordingScreen());
        System.out.println("Tearing down method after recoding...");
        BaseTest.quitDriver();
        System.out.println("Tear Down completed");
    }

    @Test
    public void invalid_username_valid_password_unsuccessful_login() {
        //GIVEN - invalid username but correct password
        JSONObject invalidUserObj = jsonObjLoginUsers.getJSONObject("invalidUser");

        loginPage
                .enterUsername(invalidUserObj.getString("username"))
                .enterPassword(invalidUserObj.getString("password"));

        //WHEN - login btn is pressed
        loginPage.pressLoginBtn();

        //THEN - verify error messaged displayed is correct, and user is not logged in
        softAssert.assertEquals(loginPage.getErrorText(), BaseTest.stringHashMap.get("error_invalid_username_password"));
        softAssert.assertAll();
    }

    @Test
    public void valid_username_invalid_password_unsuccessful_login() {
        //GIVEN - invalid username but correct password
        JSONObject invalidUserObj = jsonObjLoginUsers.getJSONObject("invalidPassword");
        loginPage
                .enterUsername(invalidUserObj.getString("username"))
                .enterPassword(invalidUserObj.getString("password"));

        //WHEN - login btn is pressed
        loginPage.pressLoginBtn();

        //THEN - verify error messaged displayed is correct, and user is not logged in
        softAssert.assertEquals(loginPage.getErrorText(), BaseTest.stringHashMap.get("error_invalid_username_password"));
        softAssert.assertAll();
    }

    @Test
    public void valid_username_valid_password_successful_login() {
        //GIVEN - invalid username but correct password
        JSONObject validUserObj = jsonObjLoginUsers.getJSONObject("validUser");

        loginPage
                .enterUsername(validUserObj.getString("username"))
                .enterPassword(validUserObj.getString("password"));

        //WHEN - login button is pressed
        ProductsPage productsPage = loginPage.pressLoginBtn();

        //THEN - verify user logs in and is displayed the Products page.
       softAssert.assertEquals(productsPage.getTitle(), BaseTest.stringHashMap.get("products_page_title"));
        softAssert.assertAll();
    }
}
