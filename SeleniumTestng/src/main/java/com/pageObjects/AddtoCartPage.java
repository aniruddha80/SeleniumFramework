package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.action.Action;
import com.base.BaseClass;

public class AddtoCartPage extends BaseClass{
Action action= new Action();
	
	@FindBy(id="quantity_wanted")
	private WebElement quantity;
	@FindBy(id="uniform-group_1")
	private WebElement uniformGroup;
	@FindBy(id="group_1")
	private WebElement size;
	
	@FindBy(xpath="//span[text()='Add to cart']")
	private WebElement addToCartBtn;
	
	@FindBy(xpath="//*[@id=\"layer_cart\"]//h2/i")
	private WebElement addToCartMessag;
	
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckOutBtn;
	
	public AddtoCartPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public void enterQuantity(String quantity1) throws Throwable {
		action.enterValue(quantity, quantity1);
	}
	
	public void selectSize(String size1) throws Throwable {
		action.scrollByVisibilityOfElement(getDriver(), uniformGroup);
	     //action.JSClick(getDriver(), uniformGroup);
	     action.clickanyElement(uniformGroup);
	     action.selectValuefromDropDown(getDriver(), size, size1);
		//action.selectByVisibleText(size1, size);
	}
	
	public void clickOnAddToCart() throws Throwable {
		action.clickanyElement(addToCartBtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		
		return action.checkIsDisplayed(addToCartMessag);
	}
	
	
}
