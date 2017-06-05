package onlineGrid.grid;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentTestManager 
{
//	public static ExtentTest getTest();
//	public static ExtentTest createTest(String name, String desc);
//	public static void removeTest(ExtentTest test);
	
	
	private static ExtentReports extent ;
	private static Map<Integer,ExtentReports>ExtentReportsMap = new HashMap<Integer, ExtentReports>();
	private static ExtentTest test;
	private static Map<Integer,ExtentTest>ExtentTestMap = new HashMap<Integer,ExtentTest>();
	
	public synchronized static void setExtentReports()
	{
		extent = ExtentManager.createInstance();
		ExtentReportsMap.put((int) Thread.currentThread().getId(),extent);
	}
	
	public synchronized static ExtentReports getExtentReports()
	{
		return ExtentReportsMap.get((int) Thread.currentThread().getId());
	}
	
	public synchronized static ExtentTest getExtentTest()
	{
		
				test= ExtentTestMap.get((int) Thread.currentThread().getId());
		return test;
		
	}
	public synchronized static ExtentTest createTest(String testname)
	{
		test = ExtentReportsMap.get((int) Thread.currentThread().getId()).createTest(testname);
		ExtentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}
	
	
	

}
