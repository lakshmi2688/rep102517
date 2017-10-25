package supporting.classes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTypes {
	
	WebDriver driver;
	
	public WaitTypes(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public WebElement locateElement(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	
	public void clickWhenReady(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public List<WebElement> getListElements(By locator, int timeout)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return list;
		
	}
	
	
	public void getDropdown(By locator, int timeout, String value)
	{
		
		Select select = new Select(locateElement(locator,timeout));
		/*List<WebElement> w = select.getOptions();
		for(WebElement w1:w)
		{
			System.out.println(w1.getText());
		}*/
		select.selectByVisibleText(value);
		
	}
	
	
	public void selByIndex(By locator, int timeout, int indx)
	{
		
		Select select = new Select(locateElement(locator,timeout));
		/*List<WebElement> w = select.getOptions();
		for(WebElement w1:w)
		{
			System.out.println(w1.getText());
		}*/
		select.selectByIndex(indx);
		
	}

}
