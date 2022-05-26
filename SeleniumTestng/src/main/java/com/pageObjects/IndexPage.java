package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.action.Action;
import com.base.BaseClass;

public class IndexPage extends BaseClass{
	Action action  = new Action();
	@FindBy(xpath = "//a[@class='login']") 
	private WebElement signInBtn;
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	private WebElement myStoreLogo;
	
	@FindBy(id="search_query_top")
	private WebElement searchProductBox;
	
	@FindBy(name="submit_search")
	private WebElement searchButton;
	
	
	public IndexPage() {
		
		PageFactory.initElements(getDriver(), this);
		
	}
	
	public LoginPage clickOnSignIn() {
		action.clickanyElement(signInBtn);
		return new LoginPage();
	}
	public boolean validateLogo() {
		boolean checkDisplayed = action.checkIsDisplayed(myStoreLogo);
		return checkDisplayed;
	}
	
	public String getStoreTitle() {
		String myStoreTitle = getDriver().getTitle();
		
		return myStoreTitle;
	}
	public SearchResultPage searchProduct(String ProductName) {
		
		action.enterValue(searchProductBox, ProductName);
		action.clickanyElement(searchButton);
		return new SearchResultPage();
	}

}
