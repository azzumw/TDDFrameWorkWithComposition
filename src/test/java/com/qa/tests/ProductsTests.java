package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;
import org.apache.commons.logging.Log;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;

public class ProductsTests {

    //subject under test
    private ProductsPage productsPage;
    private ProductDetailPage productDetailPage;
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
            jsonObjLoginUsers = new JSONObject(jsonTokener).getJSONObject("validUser");;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(inputStream!= null){
                inputStream.close();
            }
        }

    }

    @BeforeMethod
    public void setUp() {
        try {
            BaseTest.initialiseDriver();
        } catch (Exception e) {
           e.printStackTrace();
        }
        loginPage = LoginPage.getInstance();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        BaseTest.quitDriver();
    }

    @Test
    public void clicking_product_navigates_to_correct_product_detail() {
        SettingsPage settingsPage;
        //GIVEN: user logs in and lands on the products page
        productsPage = login();

        softAssert.assertEquals(productsPage.getProductName(),BaseTest.stringHashMap.get("prod_detail_page_product_name"));

        //WHEN: first product is clicked
        productDetailPage = productsPage.pressProduct1();

        //THEN: verify the product detail page shows the product clicked
        softAssert.assertEquals(productDetailPage.getProductName(),BaseTest.stringHashMap.get("prod_detail_page_product_name"));
        softAssert.assertEquals(productDetailPage.getProductDescription(),BaseTest.stringHashMap.get("prod_detail_page_product_desc"));

        productsPage = productDetailPage.navigateToProductsPage();

        settingsPage = productsPage.pressDrawerMenu();

        loginPage = settingsPage.pressLogoutBtn();

        softAssert.assertEquals(loginPage.getLoginBtn().isDisplayed(),true);

        softAssert.assertAll();
    }

    private ProductsPage login(){
         return loginPage.login(
                jsonObjLoginUsers.getString("username"),
                jsonObjLoginUsers.getString("password"));
    }
}
