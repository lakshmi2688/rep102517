package supporting.classes;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GetAllLinks {

	WebDriver driver;

	public GetAllLinks(WebDriver driver) {
		this.driver = driver;
	}

	public void getLinks() throws MalformedURLException, IOException {
		List<WebElement> list = driver.findElements(By.tagName("a"));
		List<WebElement> list1 = new ArrayList<WebElement>();
		list.addAll(driver.findElements(By.tagName("img")));
		for (WebElement w : list) {
			if (w.getAttribute("href") != null) {
				list1.add(w);
			}
		}
		for(WebElement w1:list1)
		{
			String message = getUrls(new URL(w1.getAttribute("href")));
			System.out.println("the links are: "+ w1.getAttribute("href") + " : " + message);
		}
	}

	public String getUrls(URL url) throws IOException {
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.connect();
		String message = http.getResponseMessage();
		http.disconnect();
		return message;
	}

}
