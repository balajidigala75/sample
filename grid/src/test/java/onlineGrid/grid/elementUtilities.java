package onlineGrid.grid;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public  abstract class  elementUtilities 
{
	protected WebDriver driver;
	protected final int timeout = 20;
	private static ExtentTest test;
	
//	public elementUtilities()
//	{
//		this.driver = driver;
//	}
	public WebElement findWebElement(By locator)
	{
		waitForElementToBeVisible(locator);
		try
		{
			WebElement element = driver.findElement(locator);
			
			logger.log.debug("element  " + locator +"  found " );
			return element;
			
//			WebElement ele =driver.findElement(locator);
//			//StackTraceElement[] ste = Thread.currentThread().getStackTrace();
//			String str = this.getClass().getEnclosingMethod().getName();
//			
//			//logger.log.debug("element  " + locator +"  found " + str );
//			return ele;
				
			
			
		}
		catch(Exception e)
		{
			throw new TestException("Element not found : "+ locator+" " + this.toString());
		}
	}
	
	public WebElement findWebElement(By locator, WebElement elementDriver)
	{
		waitForElementToBeVisible(locator);
		try
		{
			return elementDriver.findElement(locator);
			
		}
		catch(Exception e)
		{
			throw new TestException("Sub element not found : "+ locator+" from main element " + elementDriver + "  "+ this.toString());
		}
	}
	
	
	public List<WebElement> findWebElements(By locator)
	{
		waitForElementToBeVisible(locator);
		try
		{
			return driver.findElements(locator);
			
		}
		catch(Exception e)
		{
			throw new TestException("List of Elements not found : "+ locator+" " + this.toString());
		}
	}
	
	
	
	public void click(By locator,WebElement elementDriver)
	{
		WebElement element = findWebElement(locator,elementDriver);
		try
		{
			element.click();
		}
		catch(Exception e)
		{
			throw new TestException("Sub element not clickable : "+ locator+" from main element " + elementDriver + "  "+ this.toString());
		}
	}
	
	public void click(By locator)
	{
		WebElement element = findWebElement(locator);
		try
		{
			System.out.println("clicking "+element);
			//IE 11 has some click problems
			//element.sendKeys(Keys.ENTER);
			//((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver);
			element.click();
		}
		catch(Exception e)
		{
			throw new TestException("Element not clickable : " + locator + "  " + this.toString());
		}
	}
	
	public void sendKeys(By locator, String value)
	{
		WebElement element = findWebElement(locator);
		clearElement(element);
		try
		{
			element.sendKeys(value);;
		}
		catch(Exception e)
		{
			throw new TestException("Cannot type keys : " + locator + "  " + this.toString());
		}
	}
	
	public void sendKeys(By locator, String value, WebElement elementDriver)
	{
		WebElement element = findWebElement(locator,elementDriver);
		clearElement(element);
		try
		{
			element.sendKeys(value);;
		}
		catch(Exception e)
		{
			throw new TestException("Cannot type in Sub element : "+ locator+" from main element " + elementDriver + "  "+ this.toString());
		}
	}
	
	public void clearElement(WebElement element)
	{
		try
		{
			element.clear();
		}
		catch(Exception e)
		{
			throw new TestException("Cannot clear the element : " + element + "  " + this.toString());
		}
	}

	public void waitForElementToBeVisible(By locator)
	{
		
		try
		{
			new WebDriverWait(driver,timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch(Exception e)
		{
			throw new TestException("Element not visible yet...: " + locator + "  " + this.toString());
		}
	
	}
}
