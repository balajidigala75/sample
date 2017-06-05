package onlineGrid.grid;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class cartpage extends elementUtilities 
{
	//WebDriver driver=null;
	
	private String cartSize;
	private ExtentTest test;
	
	
	public By deals_link = By.linkText("Today's Deals");
	public By cartItemsGroup = By.xpath("//div[@data-asin]");
	public By itemTitle = By.xpath(".//*[@class='a-size-medium sc-product-title a-text-bold']");
	public By itemPrice = By.xpath(".//*[@class='a-size-medium a-color-price sc-price sc-white-space-nowrap sc-product-price sc-price-sign a-text-bold']");
	public By itemQty =By.xpath(".//*[@class='a-button a-button-dropdown a-button-small a-button-span8 quantity'or @class='sc-non-editable-quantity-right']");
	public By deleteLnk = By.xpath(".//input[contains(@name,'submit.delete')]");
	
	public cartpage(WebDriver driver) 
	{
		this.driver = driver;
		
	}
	
	public DealsPage clickTodayDeals()
	{
		//driver.findElement(AmazonMainMenu.deals_link).click();
		click(AmazonMainMenu.deals_link);
		return new DealsPage(driver);
	}
	
	public void getProductsFromCart()
	{
		
		cartSize = findWebElement(AmazonMainMenu.cartValue).getText();
		
		if(cartSize.equalsIgnoreCase("0"))
			return;
		List<WebElement>Products = findWebElements(cartItemsGroup);
				
		System.out.println("list size" + Products.size());
		
		ExtentTestManager.getExtentTest().log(Status.INFO, "Displaying products from the cart...");
		
		
		for(WebElement Product:Products)
		{
			System.out.println("Next Product");
			
			String productTitle = findWebElement(itemTitle,Product).getText(); 
			String productPrice = findWebElement(itemPrice, Product).getText();
			String productQuantity = findWebElement(itemQty,Product).getText();
			
			System.out.println(productTitle);
			System.out.println(productPrice);
			System.out.println(productQuantity);
			System.out.println("********************");
			
			ExtentTestManager.getExtentTest().log(Status.INFO, productTitle + "  "+productPrice+"   "+productQuantity );
			
		}
				
	}

	public void deleteProductsFromCart() throws InterruptedException
	{
		//check cart size is zero or not
		cartSize = findWebElement(AmazonMainMenu.cartValue).getText();
		System.out.println("cart size " + cartSize);
		
		while (!cartSize.equalsIgnoreCase("0"))
		{
			
			//delete top cart product
			
			//get cart table
			List<WebElement>Products = findWebElements(cartItemsGroup);
			
			System.out.println("list size" + Products.size());
			
			for(WebElement Product:Products)
			{
				//click delete 
				
				click(deleteLnk, Product);
				Thread.sleep(1000);
				ExtentTestManager.getExtentTest().log(Status.INFO, Product.getText() + "  removed from cart");
			}
			//check cart size now if its zero or not
			cartSize = findWebElement(AmazonMainMenu.cartValue).getText();
			System.out.println("cart size " + cartSize);
			//Assert.assertEquals(cartSize, "0", "Cart not empty");
			
		}
		
		
		
	}
}
