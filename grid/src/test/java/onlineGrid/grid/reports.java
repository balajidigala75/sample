package onlineGrid.grid;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class reports 
{
//	public static ExtentReports extent =new ExtentReports("c:\\balaji\\personal\\selenium\\lenora\\online\\report.html");
//	public static ExtentTest test;
//	
////	extent = new ExtentReports("c:\\balaji\\personal\\selenium\\lenora\\online\\report.html");
//	//extent.loadConfig(new File("C:\\Balaji\\Personal\\Selenium\\extentreports-java-2.41.2\\extent-config.xml"));	
	
	private static ExtentReports extent = null;
	private static ThreadLocal<ExtentReports>extentReport = null;
	
	private  static ExtentTest test;
	private static ThreadLocal<ExtentTest>extentTest ;
	
	public synchronized static void setExtentReport()
	{
		//if (extent == null)
			extent =new ExtentReports();
		
		//if(extentReport == null )
			extentReport = new ThreadLocal<ExtentReports>();
			extentReport.set(extent);
			System.out.println("................................setExtentReport id  " + extentReport.hashCode() +"  thread id " + Thread.currentThread().getId());
		
	}
	public synchronized static ExtentReports getExtentReport()
	{
		//if(extent == null)
			//setExtentReport();
		System.out.println("................................getExtentReport id  " + extentReport.hashCode() +"  thread id " + Thread.currentThread().getId());
		return extentReport.get();
	}
	
	public static void setExtentTest(String tstname)
	{
		test = extent.createTest(tstname);
		if (extentTest == null)
			extentTest = new ThreadLocal<ExtentTest>();
		extentTest.set(test);
		
	}
	
	public static ExtentTest getExtentTest()
	{
		if (test == null)
			setExtentTest("");
		return extentTest.get();
		
	}
	
	public static void  resetExtentReporter()
	{
		extent=null;
		extentReport = null;
	
	}
	

}
