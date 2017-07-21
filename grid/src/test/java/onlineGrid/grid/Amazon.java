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
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;




public class Amazon extends elementUtilities
{
  
	//Logger log = Logger.getLogger("balajiLogger");//to log system level logging
	public WebDriver driver = null;	
	public  ExtentReports extent;
	public  ExtentTest test;
	public  ExtentHtmlReporter htmlReporter;
	static int i=0;
	
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
	public synchronized void beforeMethod(Method method)
	{
		//test = reports.getExtentReport().createTest(method.getName());
		ExtentTestManager.createTest(method.getName() + "   " + Thread.currentThread().getId());
		
		ExtentTestManager.getExtentTest().log(Status.INFO, "entering into method  " + method.getName()+" browser "+((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString());
	}
	
	@AfterMethod
	public synchronized void afterMethod(Method method, ITestResult result)
	{
		ExtentTestManager.getExtentTest().log(Status.INFO, "exiting from method  " + method.getName()+" browser "+((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString());
		//extent.removeTest(test);
		if (result.isSuccess()) {
			ExtentTestManager.getExtentTest().log(Status.PASS, "Test passed");
		}
		else if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getExtentTest().log(Status.FAIL, "Test failed");
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getExtentTest().log(Status.SKIP, "Test skipped");
		}
	}
	
	
	@BeforeTest
	@Parameters({"browser"})
	public synchronized void setup(String br) throws MalformedURLException
	{
		
//		extnt = new ExtentReports("c:\\balaji\\personal\\selenium\\lenora\\grid\\"+br+".html");
//		extnt.loadConfig(new File("C:\\Balaji\\Personal\\Selenium\\extentreports-java-2.41.2\\extent-config.xml"));
		
		
//		ThreadSafe.setExtentReport(extnt);
//		extent = ThreadSafe.getExtentReport();
		
		
		//Logger log = Logger.getLogger(Amazon.class); 
		logger.log.debug("Selecting brower....");
		
		
		System.out.println("thread number " + ++i + "  " + Thread.currentThread().getId());
		
		ExtentTestManager.setExtentReports();
		ExtentTestManager.getExtentReports();
		System.out.println("extent report  " + ExtentTestManager.getExtentReports() + "   thread id  "+ Thread.currentThread().getId());
		
		htmlReporter = new ExtentHtmlReporter("c:\\balaji\\personal\\selenium\\lenora\\grid\\"+br+".html");
		ExtentTestManager.getExtentReports().attachReporter(htmlReporter);
		
		//reports.setExtentTest(getMethodName());
		ExtentTestManager.createTest(getMethodName() + "   " + Thread.currentThread().getId());
		
		
		
		
		
		//test = extent.createTest(getMethodName());
		
	ExtentTestManager.getExtentTest().log(Status.INFO, "Deciding the browser...");
		
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
			//test.log(LogStatus.INFO, "chrome selected");
			ExtentTestManager.getExtentTest().log(Status.INFO, "Chrome Selected...");
		
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
			
			//logger.log.info("IE browser selected...");
			ExtentTestManager.getExtentTest().log(Status.INFO, "IE browser Selected...");
			
			
		}
		else if(br.equalsIgnoreCase("Firefox"))
		{
			//System.setProperty("webdriver.gecko.driver", "C:\\Balaji\\Personal\\Selenium\\geckodriver-v0.16.1-win64\\geckodriver.exe");
			 
			System.setProperty("webdriver.gecko.driver", "C:\\Balaji\\geckodriver.exe"); // this is on remote machine
			 DesiredCapabilities dc = DesiredCapabilities.chrome();
			 dc.setPlatform(Platform.WIN10);
		     dc.setBrowserName("firefox");
		     driver = new RemoteWebDriver(new URL("http://192.168.1.149:5566/wd/hub"),dc);
		     
			//driver = new FirefoxDriver();
			
			logger.log.info("Firefox browser selected...");
			ExtentTestManager.getExtentTest().log(Status.INFO, "Firefox browser selected...");
			
		}
		else
		{
			System.out.println("no browser mentioned, exiting....");
			logger.log.debug("no browser mentioned, exiting....");
			
			System.exit(0);
		}
		
		
		
		ExtentTestManager.getExtentTest().log(Status.INFO, "Browser is..." + getBrowserName());
		//ThreadSafe.getExtentTest().log(LogStatus.INFO, "Browser is..." + getBrowserName());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
		
		
	//	extent.removeTest(test);
		
	}
	
	@AfterTest
	public synchronized void tearDown()
	{
		
		driver.quit();
		//extent.endTest(test);
		ExtentTestManager.getExtentReports().flush();
		//reports.resetExtentReporter();
		//extent.close();
	
	}
	
	@Test(priority=0)
	
	public void gotosite()
	{
		logger.log.info(" entering gotosite test  "+ ((RemoteWebDriver) driver).getCapabilities().getBrowserName().toString());
		
		//test = extent.startTest( getMethodName() + " " + getBrowserName());
		ExtentTestManager.getExtentTest().log(Status.INFO, "Launching the website ...");
		driver.get("http://www.amazon.in");	
		//extent.endTest(test);
		
		
	}
	
	@Test(dependsOnMethods = {"gotosite"})
  public void clickSignIn() throws InterruptedException
  {
		logger.log.info("entering clickSignIn test");
		
		//test = extent.startTest( getMethodName() + " " + getBrowserName());
		ExtentTestManager.getExtentTest().log(Status.INFO, "Clicking Sign in from HomePage...");
				
		HomePage homepg= new HomePage(driver);
		homepg.clicksignin();
		//Assert.assertEquals(driver.getTitle(),"Amazon Sign in", "Failed........................");
	//	extent.endTest(test);
	
  }
	@Test(dependsOnMethods = {"clickSignIn"})
	public void logIn() throws InterruptedException
	{
		Thread.sleep(3000);
			
		//test = extent.startTest( getMethodName() + " " + getBrowserName());
		ExtentTestManager.getExtentTest().log(Status.INFO, "Logging in with User id and Password from Login Page");
		LoginPage loginpg = new LoginPage(driver);
		loginpg.login("", "");
	//	extent.endTest(test);
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
		//test = extent.startTest( getMethodName() + " " + getBrowserName());
		ExtentTestManager.getExtentTest().log(Status.INFO, "Clicking on Cart from Login Home page...");
		LoginHomePage loginhomepage = new LoginHomePage(driver);
		loginhomepage.addtocart();
		//extent.endTest(test);
	}
	@Test(priority =5, dependsOnMethods = {"logIn"})
	public void displayItemsfromCart()
	{
		//test = extent.startTest( getMethodName() + " " + getBrowserName());
		ExtentTestManager.getExtentTest().log(Status.INFO, "Displaying products from the cart...");
		
		cartpage cartpg = new cartpage(driver);
		cartpg.getProductsFromCart();
		//extent.endTest(test);
	}
	@Test(priority =6,dependsOnMethods = {"logIn"})
	public void deleteItemsfromCart() throws InterruptedException
	{
		//test = extent.startTest( getMethodName() + " " + getBrowserName());
		
		ExtentTestManager.getExtentTest().log(Status.INFO, "Deleting products from the cart...");
		cartpage cartpg = new cartpage(driver);
		cartpg.deleteProductsFromCart();
		//extent.endTest(test);
	}
	@Test(priority =7,dependsOnMethods = {"deleteItemsfromCart"})
	public void gotoTodayDeals()
	{
		//LoginHomePage loginhomepage = new LoginHomePage(driver);
		//test = extent.startTest( getMethodName() + " " + getBrowserName());
		cartpage cartpg = new cartpage(driver);
		cartpg.clickTodayDeals();
		ExtentTestManager.getExtentTest().log(Status.INFO, "Now in Deals Page...");
		//extent.endTest(test);
	}
	@Test(priority =8,dependsOnMethods = {"gotoTodayDeals"})
	public void addDealsToCart() throws InterruptedException
	{
	//	test = extent.startTest( getMethodName() + " " + getBrowserName());
		ExtentTestManager.getExtentTest().log(Status.INFO, "Now adding deal products to cart...");
		DealsPage deals = new DealsPage(driver);
		deals.addTodayDealItemsToCart();
		//extent.endTest(test);
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
