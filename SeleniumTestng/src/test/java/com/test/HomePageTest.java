package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.dataprovider.DataProviders;
import com.pageObjects.HomePage;
import com.pageObjects.IndexPage;
import com.pageObjects.LoginPage;

public class HomePageTest extends BaseClass {
	private IndexPage indexPage;
	private LoginPage loginPage;
	private HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Smoke",dataProvider = "credentials", dataProviderClass = DataProviders.class)
	public void myWishList(String uname, String pswd) {
		indexPage = new IndexPage();
		loginPage = indexPage.clickOnSignIn();
		homePage = loginPage.login(uname, pswd, homePage);
		boolean result = homePage.validateMyWishListPage();
		Assert.assertTrue(result);
	}

}
