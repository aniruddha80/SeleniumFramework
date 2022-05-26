package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.action.Action;
import com.base.BaseClass;

public class HomePage extends BaseClass {
	Action action = new Action();
	@FindBy(xpath="//span[text()='My wishlists']")
	private WebElement myWishList;
	@FindBy(xpath = "//span[text()='Order history and details']")
	private WebElement orderHistory;
	
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean validateMyWishListPage() {
		
		boolean check = action.checkIsDisplayed(myWishList);
		
		return check;
	}

}
