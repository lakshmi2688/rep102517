package supporting.classes;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import freemarker.core.ReturnInstruction.Return;

public class ExtentFactory {
	
	private static ExtentReports rpt;
	
	
	public static ExtentReports getInstance()
	{
		
		rpt = new ExtentReports("C:\\Users\\N0290327\\Desktop\\SeleniumScreenshots\\Policy.html");
		//rpt = new ExtentReports("C://Users//N0290327//Desktop//SeleniumScreenshots//Policy.html",false);
		rpt.addSystemInfo("Selenium ver", "2.52");
		rpt.addSystemInfo("platform","windows");
		return rpt;
	}

}
