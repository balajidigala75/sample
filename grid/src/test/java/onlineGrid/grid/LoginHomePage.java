package onlineGrid.grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginHomePage extends elementUtilities
{
	//public WebDriver driver = null;
	String expectedPageTitle = "Amazon.in Shopping Cart";
	public LoginHomePage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	public By addcart = By.id("nav-cart");
	
	public DealsPage clickTodayDeals()
	{
		//driver.findElement(AmazonMainMenu.deals_link).click();
		click(AmazonMainMenu.deals_link);
		return new DealsPage(driver);
	}
	
	public cartpage addtocart()
	{
		click(addcart);
		//Assert.assertEquals(driver.getTitle(),expectedPageTitle,"Page title  " + driver.getTitle() + "not matching with  " + expectedPageTitle);
		driver.findElement(addcart).click();
		return new cartpage(driver);
	}

}
