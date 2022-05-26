package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pageObjects.IndexPage;

public class IndexPageTest extends BaseClass {
	private IndexPage indexPage;
	@Parameters("browser")
	@BeforeMethod
	public void setup(String browser) {
		launchApp(browser); 
	}
	@AfterMethod
	public void closeBrowser() {
		getDriver().quit();
	}
	
	@Test(priority=1)
	public void VerifyLogo() {
		indexPage = new IndexPage();
		boolean result = indexPage.validateLogo();
		Assert.assertTrue(result);
		String actTitle = indexPage.getStoreTitle();
		Assert.assertEquals(actTitle, "My Store");
	}
	@Test(priority=2)
	public void VerifyTitle() {
		String actTitle = indexPage.getStoreTitle();
		Assert.assertEquals(actTitle, "My Store");
	}

}
