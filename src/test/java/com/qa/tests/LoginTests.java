package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests {

    private LoginPage loginPage;

    @BeforeClass
    public void beforeClass() throws Exception {
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
        loginPage
                .enterUsername(LoginPage.incorrectUsername)
                .enterPassword(LoginPage.password);

        //WHEN - login btn is pressed
        loginPage.pressLoginBtn();

        //THEN - verify error messaged displayed is correct, and user is not logged in
        Assert.assertEquals(loginPage.getErrorText(), LoginPage.expectedError);
    }

    @Test
    public void valid_username_invalid_password_unsuccessful_login() {
        //GIVEN - invalid username but correct password
        loginPage
                .enterUsername(LoginPage.username)
                .enterPassword(LoginPage.incorrectPassword);

        //WHEN - login btn is pressed
        loginPage.pressLoginBtn();

        //THEN - verify error messaged displayed is correct, and user is not logged in
        Assert.assertEquals(loginPage.getErrorText(), LoginPage.expectedError);
    }

    @Test
    public void valid_username_valid_password_successful_login() {
        //GIVEN - invalid username but correct password
        loginPage
                .enterUsername(LoginPage.username)
                .enterPassword(LoginPage.password);

        //WHEN - login button is pressed
        ProductsPage productsPage = loginPage.pressLoginBtn();

        //THEN - verify user logs in and is displayed the Products page.
        Assert.assertNotNull(productsPage);
        Assert.assertEquals(productsPage.getTitle(), ProductsPage.pageTitle);
    }
}
