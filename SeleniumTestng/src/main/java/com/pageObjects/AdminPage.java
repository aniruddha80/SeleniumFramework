package com.pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.action.Action;
import com.base.BaseClass;
import com.utilities.ReadExcelData;

public class AdminPage extends BaseClass {
	Action ac = new Action();
	@FindBy(id = "menu_pim_viewPimModule")
	private WebElement clickonPIM;
	@FindBy(id = "empsearch_employee_status")
	private WebElement EmploymentStatus;
	@FindBy(tagName = "option")
	private WebElement selectDropDownEmploymentStatus;
	@FindBy(id = "searchBtn")
	private WebElement clickonSearchbtn;
	@FindBy(id = "empsearch_job_title")
	private WebElement selectTitle;
	@FindBy(xpath="//table[@id='resultTable']//th")
	private List<WebElement> getHeader;
	@FindBy(xpath="//table[@id='resultTable']/tbody/tr")
	private List<WebElement> getRows;
	String testData = "Id~0020^Sub Unit~Administration|Id~0217";
	String filePath= "D:\\testJenkins\\SeleniumTestng\\ExpectedData\\DatatobeMatched.xlsx";
	int sheetId=0;

	public AdminPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void clickonAdmin() throws InterruptedException {
		ac.moveToElementandClick(getDriver(), clickonPIM);
		ac.clickanyElement(EmploymentStatus);
		ac.selectValuefromDropDown(getDriver(), EmploymentStatus, "Full-Time Permanent");
		ac.clickanyElement(selectTitle);
		ac.selectValuefromDropDown(getDriver(), selectTitle, "All");
		ac.clickanyElement(clickonSearchbtn);
		//ac.getHeaders(getHeader);
	//	ac.getRowValues(getRows);
		//ac.verifyHtmlTableValues(testData);
		//ac.getHeaders(getRows);
		
	}
	
	public void verifyWebTable() throws IOException {
		List<String> getHeaderValues = new ArrayList<String>();
		getHeaderValues = ac.getHeaders(getHeader);
		List<ArrayList<String>> getRowExcel = new ArrayList<ArrayList<String>>();
		List<ArrayList<String>> getRowWebTable = new ArrayList<ArrayList<String>>();
		getRowExcel = ReadExcelData.readExcel(filePath, sheetId);
		getRowWebTable = ac.getRowValues(getRows);
		int cnt =0;
		int columnCount = ReadExcelData.getColumnCount(filePath, sheetId);
		for(int i=1;i<getRowExcel.size();i++) {
			outer:for(int j =0;j<columnCount;j++) {
			if(!getRowExcel.get(i).get(j).equals("null")) {
				 for(int k=1;k<getRowWebTable.size();k++) {
					 for(int l=0;l<getRowWebTable.size();l++) {
					if(getRowWebTable.get(k).get(l).equals(getRowExcel.get(i).get(j))) {
						System.out.println("match");
						continue outer;
						
					}
				 }
					
				}
			}
			}
			cnt = cnt+1;
		}
		}
		
	
}
