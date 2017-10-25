package supporting.classes;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class TakeScreenShotsOnFailureAndSuccess {
	
	WebDriver driver;

	public TakeScreenShotsOnFailureAndSuccess(WebDriver driver)
	{
		this.driver = driver;
	}
	
	
	public String getScreenshotsOnSuccess(String file) throws IOException
	{
		//String filename = getFileName(27) + ".jpg";
		String filename = file + ".jpg";
		//System.out.println("filename : " +filename);
		//String dir = "C://Users//N0290327//Desktop//SeleniumScreenshots//";
		String dir = "C:\\Users\\N0290327\\Desktop\\SeleniumScreenshots\\";
		//File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(src, new File(dir + filename));
		return (dir + filename);
		
		
	}
	
	
	/*	public void getScreenshotsOnSuccess(String file) throws IOException
	{
		//String filename = getFileName(27) + ".jpg";
		String filename = file + ".jpg";
		//System.out.println("filename : " +filename);
		String dir = "C://Users//N0290327//Desktop//SeleniumScreenshots//";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(dir + filename));
		
	}*/
	
	
	public String  getScreenshotsOnFailure(String file) throws IOException
	{
		//String filename = getFileName(27) + ".jpg";
		String filename = file + ".jpg";
		//System.out.println("filename : " +filename);
		//String dir = "C://Users//N0290327//Desktop//SeleniumScreenshots//";
		String dir = "C:\\Users\\N0290327\\Desktop\\SeleniumScreenshots\\";
		//File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//FileUtils.copyFile(src, new File(dir + filename));
		return (dir + filename);
		
	}
	
	
}
