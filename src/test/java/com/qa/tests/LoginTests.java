package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.InputStream;

public class LoginTests {

    private LoginPage loginPage;
    private InputStream inputStream;
    private JSONObject jsonObjLoginUsers;

    @BeforeClass
    public void beforeClass() throws Exception {
        try {
            String datafilename = "/data/loginUsers.json";
            inputStream = getClass().getResourceAsStream(datafilename);
            JSONTokener jsonTokener = new JSONTokener(inputStream);
            jsonObjLoginUsers = new JSONObject(jsonTokener);


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream!= null){
                inputStream.close();
            }
        }

    }

    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void setUp() throws Exception {
        BaseTest.initialiseDriver();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void tearDown() {
        BaseTest.quitDriver();
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
        Assert.assertEquals(loginPage.getErrorText(), BaseTest.stringHashMap.get("error_invalid_username_password"));
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
        Assert.assertEquals(loginPage.getErrorText(), BaseTest.stringHashMap.get("error_invalid_username_password"));
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
        Assert.assertNotNull(productsPage);
        Assert.assertEquals(productsPage.getTitle(),BaseTest.stringHashMap.get("products_page_title"));
    }
}
