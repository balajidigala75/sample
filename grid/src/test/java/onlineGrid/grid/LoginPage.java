package onlineGrid.grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends elementUtilities
{
	//WebDriver driver = null;
	public static By Mobile = By.id("ap_email");
	public static By pwd = By.id("ap_password");
	public By captcha = By.id("auth-captcha-guess");
	public static By signinbtn = By.id("signInSubmit");
	
	public LoginPage(WebDriver dr)
	{
		driver = dr;
	}
	
	
	
	public LoginHomePage login(String mobile, String password) throws InterruptedException
	{
		//driver.findElement(Mobile).sendKeys(mobile);
		//findWebElement(Mobile).sendKeys(mobile);
		sendKeys(Mobile,mobile);
		//driver.findElement(pwd).sendKeys(password);
		//findWebElement(pwd).sendKeys(password);
		sendKeys(pwd,password);
		//driver.findElement(signinbtn).click();
		//findWebElement(signinbtn).click();
		click(signinbtn);
		
		try
		{
			WebElement w = driver.findElement(By.linkText("See a new challenge"));
			if (w !=null)
			{
				driver.findElement(pwd).sendKeys(password);
				driver.findElement(captcha).sendKeys("x");
				Thread.sleep(10000);
				driver.findElement(signinbtn).click();
				
			}
		}catch(Exception e)
		{
			
		
		}
		return new LoginHomePage(driver);
		
		
	}

}
