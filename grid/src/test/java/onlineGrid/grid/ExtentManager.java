package onlineGrid.grid;

import com.aventstack.extentreports.ExtentReports;

public class ExtentManager 
{	
	private static ExtentReports extent;
	
	public static ExtentReports createInstance()
	{
		extent = new ExtentReports();
		return extent;
	}
	

}
