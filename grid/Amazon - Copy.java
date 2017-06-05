package onlineGrid.grid;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;




public class Amazon 
{
  
	//Logger log = Logger.getLogger("balajiLogger");//to log system level logging
	public WebDriver driver = null;	
	public  ExtentReports extent;
	public  ExtentTest test;
	
	//Logger log = Logger.getLogger("devpinoyLogger");
	
	/*@BeforeSuite
	@Test
	public void init()
	{
		extent = new ExtentReports("c:\\balaji\\personal\\selenium\\lenora\\online\\report.html");
		extent.loadConfig(new File("C:\\Balaji\\Personal\\Selenium\\extentreports-java-2.41.2\\extent-config.xml"));
	}
	@AfterSuite
	@Test
	public void down()
	{
		extent.endTest(test);
		extent.flush();
		extent.close();
	
	}
*/
	@BeforeMethod
	public void beforeMethod(Method method)
	{
		test.log(LogStatus.INFO, "entering into method  " + method.getName()+" browser "+((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString());
	}
	
	@AfterMethod
	public void afterMethod(Method method)
	{
		test.log(LogStatus.INFO, "exiting from method  " + method.getName()+" browser "+((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString());
	}
	
	
	@BeforeTest
	@Parameters({"browser"})
	public void setup(String br) throws MalformedURLException
	{
		extent = new ExtentReports("c:\\balaji\\personal\\selenium\\lenora\\grid\\"+br+".html");
		extent.loadConfig(new File("C:\\Balaji\\Personal\\Selenium\\extentreports-java-2.41.2\\extent-config.xml"));
		//Logger log = Logger.getLogger(Amazon.class); 
		logger.log.debug("Selecting brower....");
		
		test = extent.startTest( getMethodName());
		
		test.log(LogStatus.INFO, "Deciding the browser...");
		
		if (br.equalsIgnoreCase("chrome"))
		{
			
			System.setProperty("webdriver.chrome.driver","C:\\Balaji\\Personal\\Selenium\\chromedriver_win32\\chromedriver_win32\\chromedriver.exe");
			
			/*System.setProperty("webdriver.chrome.driver","C:\\Balaji\\chromedriver.exe");
			DesiredCapabilities b = DesiredCapabilities.chrome();
			b.setPlatform(Platform.WIN10);
		    b.setBrowserName("chrome");
		    driver = new RemoteWebDriver(new URL("http://192.168.1.135:5566/wd/hub"),b);
		    */
			driver = new ChromeDriver();
			logger.log.info("Chrome browser selected...");
			test.log(LogStatus.INFO, "chrome selected");
		
		}
		else if (br.equalsIgnoreCase("InternetExplorer"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Balaji\\Personal\\Selenium\\IEDriverServer_x64_3.4.0\\IEDriverServer.exe");
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability("nativeEvents", false); 
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			ieCapabilities.setCapability("enablePersistentHover", true);
			driver = new InternetExplorerDriver(ieCapabilities);
			
			logger.log.info("IE browser selected...");
			
			
		}
		else if(br.equalsIgnoreCase("Firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", "C:\\Balaji\\Personal\\Selenium\\geckodriver-v0.16.1-win64\\geckodriver.exe");
			 
			System.setProperty("webdriver.gecko.driver", "C:\\Balaji\\geckodriver.exe");
			 DesiredCapabilities b = DesiredCapabilities.chrome();
			 b.setPlatform(Platform.WIN10);
		     b.setBrowserName("firefox");
		     driver = new RemoteWebDriver(new URL("http://192.168.1.135:5566/wd/hub"),b);
		     
			//driver = new FirefoxDriver();
			
			logger.log.info("Firefox browser selected...");
			
		}
		else
		{
			System.out.println("no browser mentioned, exiting....");
			logger.log.debug("no browser mentioned, exiting....");
			
			System.exit(0);
		}
		
		
		
		test.log(LogStatus.INFO, "Browser is..." + getBrowserName());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
		
		//setup logging
		extent.endTest(test);
		
		
	}
	
	@AfterTest
	public void tearDown()
	{
		
		driver.quit();
		//extent.endTest(test);
		extent.flush();
		extent.close();
	
	}
	
	@Test(priority=0)
	
	public void gotosite()
	{
		logger.log.info(" entering gotosite test  "+ ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString());
		
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		test.log(LogStatus.INFO, "Launching the website ...");
		driver.get("http://www.amazon.in");	
		extent.endTest(test);
		
		
	}
	
	@Test(dependsOnMethods = {"gotosite"})
  public void clickSignIn() throws InterruptedException
  {
		logger.log.info("entering clickSignIn test");
		
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		test.log(LogStatus.INFO, "Clicking Sign in from HomePage...");
				
		HomePage homepg= new HomePage(driver);
		homepg.clicksignin();
		//Assert.assertEquals(driver.getTitle(),"Amazon Sign in", "Failed........................");
		extent.endTest(test);
	
  }
	@Test(dependsOnMethods = {"clickSignIn"})
	public void logIn() throws InterruptedException
	{
		Thread.sleep(3000);
			
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		test.log(LogStatus.INFO, "Logging in with User id and Password from Login Page");
		LoginPage loginpg = new LoginPage(driver);
		loginpg.login("9704878146", "balu189");
		extent.endTest(test);
	}
	
	/*@Test(priority =3)
	public void loginHomePage()
	{
		System.out.println("entering loginHomePage test");
		LoginHomePage loginhomepage = new LoginHomePage(driver);
	}*/
	@Test(dependsOnMethods = {"logIn"})
	public void goToCartPage()
	{
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		test.log(LogStatus.INFO, "Clicking on Cart from Login Home page...");
		LoginHomePage loginhomepage = new LoginHomePage(driver);
		loginhomepage.addtocart();
		extent.endTest(test);
	}
	@Test(priority =5, dependsOnMethods = {"logIn"})
	public void displayItemsfromCart()
	{
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		test.log(LogStatus.INFO, "Displaying products from the cart...");
		
		cartpage cartpg = new cartpage(driver);
		cartpg.getProductsFromCart();
		extent.endTest(test);
	}
	@Test(priority =6,dependsOnMethods = {"logIn"})
	public void deleteItemsfromCart() throws InterruptedException
	{
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		
		test.log(LogStatus.INFO, "Deleting products from the cart...");
		cartpage cartpg = new cartpage(driver);
		cartpg.deleteProductsFromCart();
		extent.endTest(test);
	}
	@Test(priority =7,dependsOnMethods = {"deleteItemsfromCart"})
	public void gotoTodayDeals()
	{
		//LoginHomePage loginhomepage = new LoginHomePage(driver);
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		cartpage cartpg = new cartpage(driver);
		cartpg.clickTodayDeals();
		test.log(LogStatus.INFO, "Now in Deals Page...");
		extent.endTest(test);
	}
	@Test(priority =8,dependsOnMethods = {"gotoTodayDeals"})
	public void addDealsToCart() throws InterruptedException
	{
		test = extent.startTest( getMethodName() + " " + getBrowserName());
		test.log(LogStatus.INFO, "Now adding deal products to cart...");
		DealsPage deals = new DealsPage(driver);
		deals.addTodayDealItemsToCart();
		extent.endTest(test);
//		//item.clickaddtocart();
//		
//		Thread.sleep(5000);

	}
	
  public String getBrowserName()
  {
	  return ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString();
	  
  }
  public String getMethodName()
  {
	  return Thread.currentThread().getStackTrace()[2].getMethodName();
  }
  
}
