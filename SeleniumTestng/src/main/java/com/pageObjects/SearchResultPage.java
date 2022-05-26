package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.action.Action;
import com.base.BaseClass;

public class SearchResultPage extends BaseClass{
	
	Action action = new Action();
	@FindBy(xpath="//*[@id=\"center_column\"]//img")
	private WebElement productResult;
	
	public SearchResultPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean isProductAvaialble() {
		return action.checkIsDisplayed(productResult);
		
	}
	public AddtoCartPage clickOnProduct() throws Throwable {
		action.clickanyElement(productResult);
		return new AddtoCartPage();
	}

}
