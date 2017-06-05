package onlineGrid.grid;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class DealsPage extends elementUtilities 
{
	//public WebDriver driver = null;
	
	private static ExtentTest test;
	
	private By itemsListTable = By.xpath("//div[contains(@id,'_dealView_')]");
	private By itemTitle = By.xpath(".//*[@id='dealTitle' and @class='a-size-base a-color-link dealTitleTwoLine restVisible singleCellTitle autoHeight']");
	private By itemPrice = By.xpath(".//*[contains(@class,'priceBlock')]");
	private By itemButtonTitle = By.xpath(".//*[@class='a-button-text a-text-center']");
	public By items = By.linkText("Deal of the Day");
	public By images = By.xpath("//*[@id='dealTitle']");
	
	public DealsPage(WebDriver driver) 
	{
		this.driver =driver;
		System.out.println("entered int Deals constructor");
		
	}
	
	public ItemDetailsPage addTodayDealItemsToCart() throws InterruptedException
	{
		System.out.println("entered int click item");
		Thread.sleep(5000);
		List<WebElement>itemsList = findWebElements(itemsListTable);
		
		System.out.println("items list size in deals page  "+ itemsList.size());
		
		int i=0;
		for(WebElement item : itemsList)
		{
			if (i>=6) break; //just to limit number of items adding to cart ONLY to 5
			
			String productTitile = findWebElement(itemTitle,item).getText();
			String productPrice =  findWebElement(itemPrice,item).getText();
			
			try{
			WebElement productButton = findWebElement(itemButtonTitle,item);
			String productButtonTitle = productButton.getText();
			
			if(productButtonTitle.equalsIgnoreCase("Add To Cart"))
			{
				System.out.println("....."+productTitile+"  " + productPrice +"  "+ productButtonTitle);
				productButton.click();		
				ExtentTestManager.getExtentTest().log(Status.INFO, productTitile + "  added to cart");
				i++;
			}
			Thread.sleep(500);
			}
			catch(Exception e)
			{
				System.out.println("the button not found");
			}
			
			
				
		}
		
//		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,250)", "");
	
		/*WebElement submit = driver.findElement(item);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",submit);*/
		
		/*Actions act = new Actions(driver);
		act.moveToElement(ele1).perform();
		act.click(ele1).perform();*/

		
		/*ArrayList <String> lst;
		lst = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(lst.get(1));*/
		return new ItemDetailsPage(driver);
	}

}
;