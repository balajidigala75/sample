package onlineGrid.grid;

import org.openqa.selenium.WebDriver;



public class HomePage extends elementUtilities
{
	//public WebDriver driver = null;
	String expctedPageTitle = "Amazon Sign in";
	
		public HomePage(WebDriver dr)
		{
			driver = dr;
			
		}


		public LoginPage clicksignin() throws InterruptedException
		{
			//driver.findElement(AmazonMainMenu.link_signin).click();
			//WebElement signInLink=findWebElement(AmazonMainMenu.link_signin);
			//signInLink.click();
			//Thread.sleep(2000);
			click(AmazonMainMenu.link_signin);
			//Assert.assertEquals(driver.getTitle(),expctedPageTitle,"page title not matching");
			return new LoginPage(driver);
			
		}

}
