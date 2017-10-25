package supporting.classes;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class TakeScreenShots {
	
	WebDriver driver;

	public TakeScreenShots(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public String getFileName(int len)
	{
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<len;i++)
		{
		int ind = (int) (Math.random()*str.length());
		sb.append(str.charAt(ind));
		}
		//System.out.println("file name is : " + sb.toString());
		return sb.toString();
	}

	
	public void getScreenshots() throws IOException
	{
		String filename = getFileName(27) + ".jpg";
		//String filename = file + ".jpg";
		//System.out.println("filename : " +filename);
		String dir = "C:\\Users\\N0290327\\Desktop\\SeleniumScreenshots\\";
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(dir + filename));
		
	}
	
	
}
