package supporting.classes;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericMethods {

	WebDriver driver;

	public GenericMethods(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(String type, String locator) {
		type = type.toLowerCase();
		if (type == "id") {
			return this.driver.findElement(By.id(locator));
		} else if (type == "xpath") {
			return this.driver.findElement(By.xpath(locator));
		} else if (type == "cssselector") {
			return this.driver.findElement(By.cssSelector(locator));
		} else if (type == "linktext") {
			return this.driver.findElement(By.linkText(locator));
		} else if (type == "name") {
			return this.driver.findElement(By.name(locator));
		} else if (type == "classname") {
			return this.driver.findElement(By.className(locator));
		} else {
			return null;
		}

	}

	public List<WebElement> getElements(String type, String locator) {
		type = type.toLowerCase();
		if (type == "id") {
			return this.driver.findElements(By.id(locator));
		} else if (type == "xpath") {
			return this.driver.findElements(By.xpath(locator));
		} else if (type == "cssselector") {
			return this.driver.findElements(By.cssSelector(locator));
		} else if (type == "linktext") {
			return this.driver.findElements(By.linkText(locator));
		} else if (type == "name") {
			return this.driver.findElements(By.name(locator));
		} else if (type == "classname") {
			return this.driver.findElements(By.className(locator));
		} else {
			return null;
		}
	}

	public boolean isElementPresent(String type, String locator) {
		List<WebElement> W = getElements(type, locator);
		if (W.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean retryingFindClick(By locator) {
        boolean result = false;
        int attempts = 0;
        while(attempts < 2) {
            try {
                List<WebElement> list = driver.findElements(locator);
                for(WebElement w: list )
                {
                	w.click();
                }
                result = true;
                break;
            } catch(StaleElementReferenceException e) {
            	e.printStackTrace();
            }
            attempts++;
        }
        return result;
}

	public String retryingGetText(WebElement W) {
		int attempts = 0;
		String result = "";
		while (attempts < 2) {
			try {
				result = W.getText();

				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

}
