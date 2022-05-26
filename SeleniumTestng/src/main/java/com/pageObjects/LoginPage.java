package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.action.Action;
import com.base.BaseClass;

public class LoginPage extends BaseClass {
Action action= new Action();
	
	@FindBy(id="email")
	private WebElement userName;
	
	@FindBy(id="passwd")
	private WebElement password;

	@FindBy(id="SubmitLogin")
	private WebElement signInBtn;
	
	@FindBy(name="email_create")
	private WebElement emailForNewAccount;
	
	@FindBy(name="SubmitCreate")
	private WebElement createNewAccountBtn;
	

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public HomePage login(String uName, String pwd, HomePage homePage) {
		
		action.scrollByVisibilityOfElement(getDriver(), userName);
		action.scrollByVisibilityOfElement(getDriver(), password);
		action.enterValue(userName, uName);
		action.enterValue(password, pwd);
		action.clickanyElement(signInBtn);
		homePage = new HomePage();
		
		return homePage;
		
	}

	/*public void Login(String UserName, String Password) {
		action.enterValue(userName, UserName);
		action.enterValue(passWord, Password);
		action.clickanyElement(clickLogin);
	}*/

}
