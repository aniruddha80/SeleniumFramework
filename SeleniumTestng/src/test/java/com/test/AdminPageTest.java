package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.dataprovider.DataProviders;
import com.pageObjects.AdminPage;

public class AdminPageTest{
	
	@Test
	public void a_clickAdmin() throws InterruptedException {
		AdminPage adminPage = new AdminPage();
		adminPage.clickonAdmin();
		//adminPage.verifyWebTable);
	}

	
	 @Test
	public void b_TestDataValues() throws IOException {
		 AdminPage adminPage = new AdminPage();
		 adminPage.verifyWebTable();
		
	}
}
