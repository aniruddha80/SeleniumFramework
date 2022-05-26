package com.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.base.BaseClass;

public class Action extends BaseClass {

	public int cnt = 0;
	
	
	public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}

	public boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			// System.out.println("Location not found: "+locatorName);
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Successfully Found element at");

			} else {
				System.out.println("Unable to locate element at");
			}
		}
		return flag;
	}

	/**
	 * Select value from dropdown
	 * 
	 * @param driver
	 * @param ele
	 * @param Value
	 */
	public void selectValuefromDropDown(WebDriver driver, WebElement ele, String Value) {
		boolean flag = false;
		try {
			//if (waitSmartly(ele) == 1) {
				Select option = new Select(ele);
				List<WebElement> optionv = option.getOptions();
				for (int i = 0; i < optionv.size(); i++) {
					if (optionv.get(i).getText().equals(Value)) {
						optionv.get(i).click();
						cnt = 0;
						flag = true;
						break;
					}
				}

			//}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println("Drodown value is selected ");
			} else {
				System.out.println("Drodown value failed to select ");
			}
		}
	}

	/**
	 * The method is used for fluent wait
	 * 
	 * @param ele
	 * @return
	 */
	public int waitSmartly(WebElement ele)
	{
		Wait<WebElement> wait = new FluentWait<WebElement>(ele).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofMillis(2000)).ignoring(NoSuchElementException.class);

		wait.until(new Function<WebElement, Boolean>() {
			boolean isDisplay = false;

			public Boolean apply(WebElement ele) {
				// TODO Auto-generated method stub
				try {
					isDisplay = ele.isDisplayed();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (isDisplay) {
					cnt = 1;
				}
				return isDisplay;
			}
		});
		return cnt;

	}

	/**
	 * The method is used for clicking an element after moving the cursor
	 * 
	 * @param driver
	 * @param ele
	 */
	public void moveToElementandClick(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();
	}

	/**
	 * The method is used for entering value
	 * 
	 * @param ele
	 * @param text
	 * @return
	 */

	public boolean enterValue(WebElement ele, String text) {
		boolean flag = false;

		try {
			if (waitSmartly(ele) == 1) {
				ele.clear();
				ele.sendKeys(text);
				flag = true;
				cnt = 0;

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println("Value is entered successfully in the field: " + ele);
			} else {
				System.out.println("Value is not entered successfully in the field: " + ele);
			}
		}
		return flag;
	}

	/**
	 * The method is used for clicking an element
	 * 
	 * @param ele
	 * @return
	 */

	public boolean clickanyElement(WebElement ele) {
		boolean flag = false;
		try {
			if (waitSmartly(ele) == 1) {
				ele.click();
				flag = true;
				cnt = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println("The element is clicked " + ele);
			} else {
				System.out.println("The element is not clicked " + ele);
			}
		}
		return flag;

	}

	/***
	 * The method is used for taking screen shot
	 * 
	 * @param driver
	 * @param filename
	 * @return
	 */
	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}
	
	public List<String> getHeaders(List<WebElement>ele){
		 List<String> getValues = new ArrayList<String>();
		 
		 for(int i=0;i<ele.size();i++) {
			 getValues.add(ele.get(i).getText());
		 }
		return getValues;
		
	}
	
	public List<ArrayList<String>> getRowValues(List<WebElement>ele) {
		List<ArrayList<String>> getAllValues = new ArrayList<ArrayList<String>>();
		
		for(int i=0;i<ele.size();i++) {
			ArrayList<String> getRowValues = new ArrayList<String>();
			List<WebElement>cols = ele.get(i).findElements(By.tagName("td"));
			for(int j=0; j<cols.size();j++) {
				getRowValues.add(cols.get(j).getText());
			}
			getAllValues.add(getRowValues);
		 }
		return getAllValues;

	}
	
	public void verifyHtmlTableValues(String testData) {
		
		String[] rows = testData.split("\\|");
		
		for(int i=0;i<rows.length;i++) {
			String[] cols = rows[i].split("\\^");
			for(int j=0;j<cols.length;j++) {
				String Header = cols[j].substring(0,cols[j].indexOf("~"));
				String values = cols[j].substring(cols[j].indexOf("~")+1,cols[j].length());
				
				System.out.println("Header: " + Header + "values: " + values);
			}
		}
		
	}
	
	

		
	
	public boolean checkIsDisplayed(WebElement ele) {
		boolean flag = false;
		if(ele.isDisplayed()) {
			flag = true;
			System.out.println("Element is displayed");
		}
		else {
			System.out.println("element not displayed");
		}
		return flag;
	}
	public boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}
	public boolean JSClick(WebDriver driver, WebElement ele) throws Exception {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				System.out.println("Click Action is performed");
			} else if (!flag) {
				System.out.println("Click Action is not performed");
			}
		}
		return flag;
	}


}
