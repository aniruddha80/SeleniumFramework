package com.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.dataprovider.DataProviders;
import com.pageObjects.IndexPage;
import com.pageObjects.SearchResultPage;

public class SearchResultTest extends BaseClass{
	IndexPage index;
	SearchResultPage searchResultPage;
	@Parameters("browser")
	@BeforeMethod
	
	public void setUp(String browser) {
		launchApp(browser);
	}
	@AfterMethod
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(dataProvider = "searchProduct", dataProviderClass = DataProviders.class)
	public void productAvailabilityTest(String productName) throws Throwable {
		//Log.startTestCase("productAvailabilityTest");
		index= new IndexPage();
		searchResultPage=index.searchProduct(productName);
		boolean result=searchResultPage.isProductAvaialble();
		Assert.assertTrue(result);
		//Log.endTestCase("productAvailabilityTest");
	}

}
