package onlineGrid.grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemDetailsPage 
{
	
	
	public ItemDetailsPage(WebDriver driver) 
	{
		this.driver = driver;
				
	}

	public WebDriver prevdriver = null;
	public WebDriver driver = null;
	public By addtocart = By.id("nav-cart");
	public By size = By.id("native_dropdown_selected_size_name");
	public By addtocartbtn = By.xpath("//button[contains(text(),'Cart')]");
	
	public void clickaddtocart() throws InterruptedException
	{
		//Select sel = new Select(driver.findElement(size));
		//sel.selectByVisibleText("38");
		Thread.sleep(3000);
		driver.findElement(addtocartbtn).click();
		
		driver.findElement(addtocart).click();
	}
	

}
