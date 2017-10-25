package com.policy.multiplepolicycreation;
import supporting.classes.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class PolicyTestNGTest {

	private WebDriver driver;
	private String url;
	private GenericMethods gm;
	private WaitTypes wt;
	private TakeScreenShots ss;
	private TakeScreenShotsOnFailureAndSuccess sf;
	private JavascriptExecutor js;
	private ExcelRead er;
	private ExcelWrite ew;
	private String lastName;
	private String firstName;
	private String agencyId;
	private int row;
	private ExtentTest test;
	private ExtentReports rpt;
	
	private static Logger log = Logger.getLogger(PolicyTestNGTest.class);
	
	

	@Parameters({"browserType"})
	@BeforeClass
	public void setUp(String browser) throws Exception {
		
		if(browser.equalsIgnoreCase("internetExplorer"))
		{
		System.setProperty("webdriver.ie.driver",
				"C:\\Users\\N0290327\\Downloads\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setBrowserName("InternetExplorer");
		caps.setPlatform(Platform.WINDOWS);
		caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
		caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
		caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		driver  = new InternetExplorerDriver(caps);
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\N0290327\\Downloads\\chromedriver_win32\\chromedriver.exe");
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.WINDOWS);
			driver = new ChromeDriver(caps);
			/*extn root directory :C:\Users\N0290327\AppData\Local\Google\Chrome\User Data\Default\Extensions\aohghmighlieiainnegkcijnfilokake\0.9_0*/
			/*ChromeOptions co = new ChromeOptions();
			co.addExtensions(new File("C://Users//N0290327//AppData//Local//Google//Chrome//User Data//Default//Extensions//aohghmighlieiainnegkcijnfilokake//0.9_0.crx"));
			driver = new ChromeDriver(co);*/
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\N0290327\\Downloads\\geckodriver-v0.11.1-win32\\geckodriver.exe");
					
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setBrowserName("firefox");
			caps.setPlatform(Platform.WINDOWS);
			driver = new FirefoxDriver(caps);
		/*	ProfilesIni prof = new ProfilesIni();
			FirefoxProfile fox = prof.getProfile("SeleniumTest");
			driver = new FirefoxDriver(fox);*/
			
			
		}
		
		// url = "http://www.google.com";
		//PropertyConfigurator.configure("log4j.properties");
		url = "http://ecdev-sam-tin.apps.safeco.com/Client/TinML/Client/ClientList.aspx";
		gm = new GenericMethods(driver);
		wt = new WaitTypes(driver);
		ss = new TakeScreenShots(driver);
		sf = new TakeScreenShotsOnFailureAndSuccess(driver);
		er = new ExcelRead();
		ew = new ExcelWrite();
		rpt = ExtentFactory.getInstance();
		test = rpt.startTest("start the test for extents report");		
		
		test.log(LogStatus.INFO, "browser started");
		
		row = 1;
		js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "browser maximized");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get(url);

	}

/*	@DataProvider(name = "PolicyInformation")
	public String[][] dataPolicy() throws IOException {
		String[][] testDataPolicy = er.readExcel("Policy Information");
		System.out.println("length: " + testDataPolicy[0].length);
		return testDataPolicy;
	}*/

	/*
	 * @DataProvider(name = "Drivers") public String[] dataDriver() throws
	 * IOException { String[][] testDataDriver = er.readExcel("Drivers");
	 * String[] dataDriver = new String[testDataDriver[0].length]; for(int
	 * i=0;i<testDataDriver.length;i++) { dataDriver = testDataDriver[i]; return
	 * dataDriver; } System.out.println("length: " +testDataDriver[0].length);
	 * return dataDriver; }
	 */

	/*@DataProvider(name = "Drivers")
	public String[][] dataDriver() throws IOException {
		String[][] testDataDriver = er.readExcel("Drivers");
		System.out.println("length: " + testDataDriver[0].length);
		return testDataDriver;
	}

	@DataProvider(name = "Vehicles")
	public String[][] dataVehicle() throws IOException {
		String[][] testDataVehicle = er.readExcel("Vehicles");
		System.out.println("length: " + testDataVehicle[0].length);
		return testDataVehicle;
	}*/

	/*
	 * @DataProvider(name = "Telematics") public String[][] dataTelematics()
	 * throws IOException { String[][] testDataTelematics =
	 * er.readExcel("Telematics"); System.out.println("length: "
	 * +testDataTelematics[0].length); return testDataTelematics; }
	 */

	/*@DataProvider(name = "Underwriting")
	public String[][] dataUnderwriting() throws IOException {
		String[][] testDataUnderwriting = er.readExcel("Underwriting");
		System.out.println("length: " + testDataUnderwriting[0].length);
		return testDataUnderwriting;
	}

	@DataProvider(name = "Coverages")
	public String[][] dataCoverages() throws IOException {
		String[][] testDataCoverages = er.readExcel("Coverages");
		System.out.println("length: " + testDataCoverages[0].length);
		return testDataCoverages;
	}*/

	/*
	 * @DataProvider(name = "Summary") public String[][] dataSummary() throws
	 * IOException { String[][] testDataSummary = er.readExcel("Summary");
	 * System.out.println("length: " +testDataSummary[0].length); return
	 * testDataSummary; }
	 */

/*	@DataProvider(name = "OrderReports")
	public String[][] dataOrderReports() throws IOException {
		String[][] testDataOrderReports = er.readExcel("OrderReports");
		System.out.println("length: " + testDataOrderReports[0].length);
		return testDataOrderReports;
	}

	@DataProvider(name = "Billing")
	public String[][] dataBilling() throws IOException {
		String[][] testDataBilling = er.readExcel("Billing");
		System.out.println("length: " + testDataBilling[0].length);
		return testDataBilling;
	}

	@DataProvider(name = "Issue")
	public String[][] dataIssue() throws IOException {
		String[][] testDataIssue = er.readExcel("Issue");
		System.out.println("length: " + testDataIssue[0].length);
		return testDataIssue;
	}*/

	@DataProvider(name = "MultiplePolicies")
	public String[][] dataMultiplePolicies() throws IOException {
		String[][] testDataMultiplePolicies = er.readExcel("MultiplePolicies");
		//String[][] testDataMultiplePolicies = er.readExcel("Prod");
		System.out.println("length: " + testDataMultiplePolicies[0].length);
		return testDataMultiplePolicies;
	}

	@Test(dataProvider = "MultiplePolicies", alwaysRun = true)
	public void multipleInfo(String ratingState, String agency, String prodIden, String effDt, String fNamePolicy,
			String lNamePolicy, String Addr, String SSNstatus, String zip, String city, String state, String dobPolicy,
			String garageInd, String reason, String incidentInd, String deliveryInd, String fNameDriver,
			String lNameDriver, String dobDriver, String gender, String maritalSt, String ssnSt, String licenseSt,
			String age, String driverLicenseSt, String occupation, String education, String sr22St, String vinInd,
			String vin, String vehicleUse, String miles, String yearsOwned, String currSt, String priorCarr,
			String priorExpDt, String liability, String monthsCarr, String priorPolicyNum, String autoInsSince,
			String resType, String pptyTerm, String covLevel, String CSL, String uninsMotorCSL, String BI, String PD,
			String uninsBodilyInjury, String license, String mvrAction, String clueReport, String fNameBilling,
			String lNameBilling, String rountingNum, String accNum, String phone1, String phone2, String phone3,
			String email1, String email2) throws InterruptedException, IOException {
		
		

		test.log(LogStatus.INFO, "Read all the inputs from excel");
		// ********************** PAGE 1**********************************************************************************
		String str1 = driver.getWindowHandle();
		System.out.println("str1 : " + str1);
		WebElement E1 = gm.getElement("id", "cadd");
		WebElement E2 = gm.getElement("xpath", "//div[@id='mnuAddC']//a[contains(text(),'Personal Auto')]");
		Actions action = new Actions(driver);
		action.moveToElement(E1).click().perform();
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", E2);
		Thread.sleep(2000);

		Set<String> set = driver.getWindowHandles();
		for (String handle : set) {
			System.out.println("handle : " + handle);
			if (!handle.equals(str1)) {
				driver.switchTo().window(handle);
			}
		}

		test.log(LogStatus.INFO, "Entering details in PolicyInfo page");
		Thread.sleep(2000);
		wt.getDropdown(By.xpath("//select[@id='PolicyRatingState']"), 3, ratingState);
		agencyId = agency;
		wt.locateElement(By.xpath("//input[@id='PolicyAgentNumber']"), 3).sendKeys(agency);
		wt.locateElement(By.xpath("//input[@id='PolicyProducerName']"), 3).sendKeys(prodIden);
		wt.locateElement(By.xpath("//input[@id='PolicyEffectiveDate']"), 3).sendKeys(effDt);

		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonFirstName']"), 3).click();
		wt.locateElement(By.xpath("//span[@class='ui-icon ui-icon-closethick']"), 3).click();
		firstName = fNamePolicy;
		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonFirstName']"), 3).sendKeys(fNamePolicy);
		lastName = lNamePolicy;
		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonLastName']"), 3).sendKeys(lNamePolicy);
		wt.getDropdown(By.xpath("//select[@id='PolicyClientPersonSocialSecurityNumberStatus']"), 3, SSNstatus);
		wt.locateElement(By.xpath("//input[@id='PolicyClientMailingLocationAddressLine1']"), 3).sendKeys(Addr);
		wt.locateElement(By.xpath("//input[@id='PolicyClientMailingLocationZipCode']"), 3).sendKeys(zip);
		wt.locateElement(By.xpath("//input[@id='PolicyClientMailingLocationCity']"), 3).sendKeys(city);
		wt.getDropdown(By.xpath("//select[@id='PolicyClientMailingLocationState']"), 3, state);

		garageInd = garageInd.toUpperCase().trim();
		System.out.println("garageInd :" + garageInd);
		if (garageInd.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataVehicleGaragingAddressYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataVehicleGaragingAddressYNY']"), 3).click();
		}

		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonBirthdate']"), 3).sendKeys(dobPolicy);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataAutoBusinessType']"), 3, reason);

		incidentInd = incidentInd.toUpperCase().trim();
		WebElement e1 = null;
		System.out.println("incidentInd : " + incidentInd);
		if (incidentInd.equals("NO")) {
			e1 = wt.locateElement(By.xpath("//input[@id='PolicyAutoDataAnyIncidentsOnPolicyYNN']"), 3);
		} else {
			e1 = wt.locateElement(By.xpath("//input[@id='PolicyAutoDataAnyIncidentsOnPolicyYNY']"), 3);
		}
		js.executeScript("arguments[0].scrollIntoView(true);", e1);
		e1.click();
		Thread.sleep(2000);

		deliveryInd = deliveryInd.toUpperCase().trim();
		System.out.println("deliveryInd :" + deliveryInd);
		if (deliveryInd.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataDeliveryVehicleYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataDeliveryVehicleYNY']"), 3).click();
		}
		Thread.sleep(2000);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		Set<String> set1 = driver.getWindowHandles();
		for (String handle1 : set1) {
			System.out.println("handle : " + handle1);
			if (!handle1.equals(str1)) {
				driver.switchTo().window(handle1);
			}
		}

		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonFirstName']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonFirstName']"), 3).sendKeys(fNameDriver);
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonLastName']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonLastName']"), 3).sendKeys(lNameDriver);
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonBirthdate']"), 3).sendKeys(dobDriver);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonGender']"), 3, gender);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonMaritalStatus']"), 3, maritalSt);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonSocialSecurityNumberStatus']"), 3, ssnSt);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverLicenseState']"), 3, licenseSt);
		wt.locateElement(By.xpath("//input[@id='PolicyDriverFirstAgeLicensed']"), 3).sendKeys(age);

		driverLicenseSt = driverLicenseSt.toUpperCase().trim();
		if (driverLicenseSt.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyDriverLicenseSuspendedRevokedYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyDriverLicenseSuspendedRevokedYNY']"), 3).click();
		}

		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonCommonOccupationCategory']"), 3, occupation);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonEducation']"), 3, education);

		sr22St = sr22St.toUpperCase().trim();
		WebElement e2 = null;
		if (sr22St.equals("NO")) {
			e2 = wt.locateElement(By.xpath("//input[@id='PolicyDriverSR22FilingYNN']"), 3);
		} else {
			e2 = wt.locateElement(By.xpath("//input[@id='PolicyDriverSR22FilingYNY']"), 3);
		}
		js.executeScript("arguments[0].scrollIntoView(true);", e2);
		e2.click();
		Thread.sleep(2000);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// ********************** PAGE 3  **********************************************************************************
		test.log(LogStatus.INFO, "Entering details in vehicle page");
		// wt.getDropdown(By.xpath("//select[@id='PolicyVehiclemp_GaragedLocation_ID']"),
		// 3, garageLoc);
		// wt.getDropdown(By.xpath("//select[@id='PolicyVehiclemp_PrincipalOperator_ID']"),
		// 3, principalOp);

		vinInd = vinInd.toUpperCase().trim();
		if (vinInd.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyVehicleVINKnownYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyVehicleVINKnownYNY']"), 3).click();
			wt.locateElement(By.xpath("//input[@id='PolicyVehicleVINKnownYNY']"), 3).click();
		}
		Thread.sleep(2000);

		wt.locateElement(By.xpath("//input[@id='PolicyVehicleVIN']"), 3).sendKeys(vin);
		wt.locateElement(By.xpath("//input[@id='imgVINLookUp']"), 3).click();
		wt.getDropdown(By.xpath("//select[@id='PolicyVehicleUse']"), 3, vehicleUse);
		wt.locateElement(By.xpath("//input[@id='PolicyVehicleAnnualMiles']"), 3).sendKeys(miles);
		wt.locateElement(By.xpath("//input[@id='PolicyVehicleYearsVehicleOwned']"), 3).sendKeys(yearsOwned);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// ********************** PAGE 5 - NO PAGE 4  *********************************************************************************
		wt.getDropdown(By.xpath("//select[@id='PolicyCurrentInsuranceValue']"), 3, currSt);
		wt.getDropdown(By.xpath("//select[@id='PolicyPrevInsuranceCarrierValue']"), 3, priorCarr);
		wt.locateElement(By.xpath("//input[@id='PolicyAutoDataPriorAutoPolicyExpirationDate']"), 3)
				.sendKeys(priorExpDt);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataPrevLiabilityType']"), 3, liability);
		wt.locateElement(By.xpath("//input[@id='PolicyPriorPolicyDuration']"), 3).sendKeys(monthsCarr);
		wt.locateElement(By.xpath("//input[@id='PolicyPriorSafecoPolicyNumber']"), 3).sendKeys(priorPolicyNum);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataResidenceType']"), 3, resType);
		// Thread.sleep(3000);
		// wt.clickWhenReady(By.xpath("//input[@id='PolicyCustomerSinceDate']"),
		// 3);
		wt.locateElement(By.xpath("//input[@id='PolicyCustomerSinceDate']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyCustomerSinceDate']"), 3).sendKeys(autoInsSince);
		// wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataResidenceType']"),
		// 3, resType);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// ********************** PAGE 6  *********************************************************************************
		if (pptyTerm.equals("6")) {
			wt.locateElement(By.xpath("//input[@id='PolicyPolicyTerm0']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyPolicyTerm1']"), 3).click();
		}
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataPackageSelection']"), 3, covLevel);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonCSLLimit']"), 3, CSL);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonUMCSLLimit']"), 3, uninsMotorCSL);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonBILimit']"), 3, BI);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonPDLimit']"), 3, PD);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonUMLimit']"), 3, uninsBodilyInjury);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

	// ********************** PAGE 7  *********************************************************************************
		wt.locateElement(By.xpath("//input[@id='PolicyPostNotificationYN']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// ********************** PAGE 8*********************************************************************************
		wt.locateElement(By.xpath("//input[@id='PolicyDrivers1LicenseNumber']"), 3).sendKeys(license);
		wt.getDropdown(By.xpath("//select[@id='PolicyDrivers1MVRMVROrderWaive']"), 3, mvrAction);
		wt.getDropdown(By.xpath("//select[@id='PolicyCLUECLUEOrderWaive']"), 3, clueReport);
		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// ********************** PAGE 9 *********************************************************************************
		wt.locateElement(By.xpath("//input[@id='PolicyBillingPlanAutoDeduction2']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyBillingPlanPaymentMethod3']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyMonthlyPaymentPlanNewExisting0']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyAccountType1']"), 3).click();

		wt.locateElement(By.xpath("//input[@id='PolicyEFTFirstName']"), 3).sendKeys(fNameBilling);
		wt.locateElement(By.xpath("//input[@id='PolicyEFTLastName']"), 3).sendKeys(lNameBilling);
		wt.locateElement(By.xpath("//input[@id='PolicyDFIRoutingNumber']"), 3).sendKeys(rountingNum);
		wt.locateElement(By.xpath("//input[@id='PolicyDFIAccountNumber']"), 3).sendKeys(accNum);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// ********************** PAGE 10  *********************************************************************************
		wt.selByIndex(By.xpath("//select[@id='PolicyLocations1TaxCode']"), 3, 1);
		wt.locateElement(By.name("PolicyClientHomePhoneNumberAreaCode"), 3).sendKeys(phone1);
		wt.locateElement(By.name("PolicyClientHomePhoneNumberPrefix"), 3).sendKeys(phone2);
		wt.locateElement(By.name("PolicyClientHomePhoneNumberSuffix"), 3).sendKeys(phone3);
		wt.locateElement(By.xpath("//input[@id='PolicyClientEmailAddress']"), 3).sendKeys(email1);
		wt.locateElement(By.xpath("//input[@id='PolicyIsEffectiveDateValidYNY']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyInsuranceCancelNonRenewYNN']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyAutoDataAllDriversListedYNY']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyElectronicSignatureDocusignPrimaryInsuredEmail']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyElectronicSignatureDocusignPrimaryInsuredEmail']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyElectronicSignatureDocusignPrimaryInsuredEmail']"), 3)
				.sendKeys(email2);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// *****************************POLICY RETRIEVAL  ***********************************************************
		wt.locateElement(By.xpath("//input[@id='SAMSearchMyActivityOnly']"), 3).click();
		wt.getDropdown(By.xpath("//select[@id='SAMSearchModifiedDateRange']"), 3, "Today");
		String nameSearch = lastName + "," + firstName;
		System.out.println("nameSearch :" + nameSearch);
		wt.locateElement(By.xpath("//input[@id='SAMSearchName']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='SAMSearchName']"), 3).sendKeys(nameSearch);
		System.out.println("agencyNumber : " + agencyId);
		wt.locateElement(By.xpath("//input[@id='SAMSearchAgentNumber']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='SAMSearchAgentNumber']"), 3).sendKeys(agencyId);
		wt.locateElement(By.xpath("//input[@id='asearch']"), 3).click();

		StringBuilder sb = new StringBuilder();

		if (gm.isElementPresent("xpath", "//div[@id='divMain']//a[@class='cai']")) {
			System.out.println("display details already open");
			sb.append(wt.locateElement(By.xpath("//div[@id='divMain']//a[@class='cai']"), 3).getText()).append(",");
		}

		if (gm.isElementPresent("xpath","//div[@id='divMain']//a[@title='Click to Display Activity']")) {
			List<WebElement> list = gm.getElements("xpath","//div[@id='divMain']//a[@title='Click to Display Activity']");
			System.out.println("list size &&&&&: " + list.size());
			List<String> listarr = new ArrayList<String>();

			for (WebElement W : list) {
				System.out.println(W.getAttribute("href"));
				if (W.getAttribute("href") != null) {
					listarr.add(W.getAttribute("href"));
				}
			}

			System.out.println("list array size which contains all the links: " +listarr.size());
			for (String link : listarr) {
				System.out.println("link: " + link);
				driver.get(link);
				//ss.getScreenshots();
				Thread.sleep(1000);
				if (gm.isElementPresent("xpath", "//div[@id='divMain']//td[contains(@class,'cai') and contains(text(),'Quoted / Not Issued')]") || gm.isElementPresent("xpath", "//div[@id='divMain']//td[contains(@class,'cai') and contains(text(),'Incomplete')]"))
				{
					continue;
				}
				else
				{
				sb.append(wt.locateElement(By.xpath("//div[@id='divMain']//a[@class='cai']"), 3).getText()).append(",");
				Thread.sleep(2000);
				}
			}
			
			
		}
		wt.locateElement(By.xpath("//table[@id='criteriaTable']//input[@value = 'Reset']"), 3).click();

		String policy = sb.toString();
		policy = policy.substring(0, policy.length() - 1);
		System.out.println("result: " + policy);

				
		if (!policy.isEmpty() && policy != null) {
			System.out.println("writing into row: " +row);
			ew.writeExcel("PolicyCreated");
			ew.setCellData(row, nameSearch, agencyId, policy);
		}
		
	
		row++;

		test.log(LogStatus.INFO, "Created policy for input " + row  + "from excel");
	

	}

/*	@Test(dataProvider = "PolicyInformation")
	public void policyInfo(String ratingState, String agency, String prodIden, String effDt, String fName, String lName,
			String Addr, String SSNstatus, String zip, String city, String state, String dob, String garageInd,
			String reason, String incidentInd, String deliveryInd) throws Exception {

		// ********************** PAGE
		// 1**********************************************************************************
		String str1 = driver.getWindowHandle();
		System.out.println("str1 : " + str1);
		WebElement E1 = gm.getElement("id", "cadd");
		WebElement E2 = gm.getElement("xpath", "//div[@id='mnuAddC']//a[contains(text(),'Personal Auto')]");
		Actions action = new Actions(driver);
		action.moveToElement(E1).click().perform();
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", E2);
		Thread.sleep(7000);

		Set<String> set = driver.getWindowHandles();
		for (String handle : set) {
			System.out.println("handle : " + handle);
			if (!handle.equals(str1)) {
				driver.switchTo().window(handle);
			}
		}

		Thread.sleep(2000);
		wt.getDropdown(By.xpath("//select[@id='PolicyRatingState']"), 3, ratingState);
		wt.locateElement(By.xpath("//input[@id='PolicyAgentNumber']"), 3).sendKeys(agency);
		wt.locateElement(By.xpath("//input[@id='PolicyProducerName']"), 3).sendKeys(prodIden);
		wt.locateElement(By.xpath("//input[@id='PolicyEffectiveDate']"), 3).sendKeys(effDt);

		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonFirstName']"), 3).click();
		wt.locateElement(By.xpath("//span[@class='ui-icon ui-icon-closethick']"), 3).click();
		// span[@id = 'ui-dialog-title-2']

		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonFirstName']"), 3).sendKeys(fName);
		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonLastName']"), 3).sendKeys(lName);
		wt.getDropdown(By.xpath("//select[@id='PolicyClientPersonSocialSecurityNumberStatus']"), 3, SSNstatus);
		wt.locateElement(By.xpath("//input[@id='PolicyClientMailingLocationAddressLine1']"), 3).sendKeys(Addr);
		wt.locateElement(By.xpath("//input[@id='PolicyClientMailingLocationZipCode']"), 3).sendKeys(zip);
		wt.locateElement(By.xpath("//input[@id='PolicyClientMailingLocationCity']"), 3).sendKeys(city);
		wt.getDropdown(By.xpath("//select[@id='PolicyClientMailingLocationState']"), 3, state);

		garageInd = garageInd.toUpperCase().trim();
		System.out.println("garageInd :" + garageInd);
		if (garageInd.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataVehicleGaragingAddressYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataVehicleGaragingAddressYNY']"), 3).click();
		}

		wt.locateElement(By.xpath("//input[@id='PolicyClientPersonBirthdate']"), 3).sendKeys(dob);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataAutoBusinessType']"), 3, reason);

		incidentInd = incidentInd.toUpperCase().trim();
		WebElement e1 = null;
		System.out.println("incidentInd : " + incidentInd);
		if (incidentInd.equals("NO")) {
			e1 = wt.locateElement(By.xpath("//input[@id='PolicyAutoDataAnyIncidentsOnPolicyYNN']"), 3);
		} else {
			e1 = wt.locateElement(By.xpath("//input[@id='PolicyAutoDataAnyIncidentsOnPolicyYNY']"), 3);
		}
		js.executeScript("arguments[0].scrollIntoView(true);", e1);
		e1.click();
		Thread.sleep(2000);

		deliveryInd = deliveryInd.toUpperCase().trim();
		System.out.println("deliveryInd :" + deliveryInd);
		if (deliveryInd.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataDeliveryVehicleYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyAutoDataDeliveryVehicleYNY']"), 3).click();
		}
		Thread.sleep(2000);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		Set<String> set1 = driver.getWindowHandles();
		for (String handle1 : set1) {
			System.out.println("handle : " + handle1);
			if (!handle1.equals(str1)) {
				driver.switchTo().window(handle1);
			}
		}

	}

	@Test(dependsOnMethods = { "policyInfo" }, dataProvider = "Drivers")
	public void drivers(String fName, String lName, String dob, String gender, String maritalSt, String ssnSt,
			String licenseSt, String age, String driverLicenseSt, String occupation, String education, String sr22St)
			throws Exception {

		// ********************** PAGE 2
		// **********************************************************************************
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonFirstName']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonFirstName']"), 3).sendKeys(fName);
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonLastName']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonLastName']"), 3).sendKeys(lName);
		wt.locateElement(By.xpath("//input[@id='PolicyDriverPersonBirthdate']"), 3).sendKeys(dob);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonGender']"), 3, gender);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonMaritalStatus']"), 3, maritalSt);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonSocialSecurityNumberStatus']"), 3, ssnSt);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverLicenseState']"), 3, licenseSt);
		wt.locateElement(By.xpath("//input[@id='PolicyDriverFirstAgeLicensed']"), 3).sendKeys(age);

		driverLicenseSt = driverLicenseSt.toUpperCase().trim();
		if (driverLicenseSt.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyDriverLicenseSuspendedRevokedYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyDriverLicenseSuspendedRevokedYNY']"), 3).click();
		}

		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonCommonOccupationCategory']"), 3, occupation);
		wt.getDropdown(By.xpath("//select[@id='PolicyDriverPersonEducation']"), 3, education);

		sr22St = sr22St.toUpperCase().trim();
		WebElement e2 = null;
		if (sr22St.equals("NO")) {
			e2 = wt.locateElement(By.xpath("//input[@id='PolicyDriverSR22FilingYNN']"), 3);
		} else {
			e2 = wt.locateElement(By.xpath("//input[@id='PolicyDriverSR22FilingYNY']"), 3);
		}
		js.executeScript("arguments[0].scrollIntoView(true);", e2);
		e2.click();
		Thread.sleep(2000);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// er.readExcel();

	}

	@Test(dependsOnMethods = { "drivers" }, dataProvider = "Vehicles")
	public void vehicles(String vinInd, String vin, String vehicleUse, String miles, String yearsOwned)
			throws Exception {
		// public void vehicles(String garageLoc, String principalOp, String
		// vinInd, String vin, String vehicleUse, String miles, String
		// yearsOwned) throws Exception {
		// ********************** PAGE 3
		// **********************************************************************************
		// wt.getDropdown(By.xpath("//select[@id='PolicyVehiclemp_GaragedLocation_ID']"),
		// 3, garageLoc);
		// wt.getDropdown(By.xpath("//select[@id='PolicyVehiclemp_PrincipalOperator_ID']"),
		// 3, principalOp);

		vinInd = vinInd.toUpperCase().trim();
		if (vinInd.equals("NO")) {
			wt.locateElement(By.xpath("//input[@id='PolicyVehicleVINKnownYNN']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyVehicleVINKnownYNY']"), 3).click();
			wt.locateElement(By.xpath("//input[@id='PolicyVehicleVINKnownYNY']"), 3).click();
		}
		Thread.sleep(2000);

		wt.locateElement(By.xpath("//input[@id='PolicyVehicleVIN']"), 3).sendKeys(vin);
		wt.locateElement(By.xpath("//input[@id='imgVINLookUp']"), 3).click();
		wt.getDropdown(By.xpath("//select[@id='PolicyVehicleUse']"), 3, vehicleUse);
		wt.locateElement(By.xpath("//input[@id='PolicyVehicleAnnualMiles']"), 3).sendKeys(miles);
		wt.locateElement(By.xpath("//input[@id='PolicyVehicleYearsVehicleOwned']"), 3).sendKeys(yearsOwned);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

		// er.readExcel();

	}*/

	/*
	 * @Test(dependsOnMethods = {"vehicles"}) public void telematics() throws
	 * Exception {
	 * 
	 * // ********************** PAGE 4
	 * ************************************************************************
	 * 
	 * wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();
	 * 
	 * 
	 * }
	 */

	// @Test(dependsOnMethods = {"telematics"}, dataProvider = "Underwriting")
/*	@Test(dependsOnMethods = { "vehicles" }, dataProvider = "Underwriting")
	public void underwriting(String currSt, String priorCarr, String priorExpDt, String liability,
			// String monthsCarr, String priorPolicyNum, String resType) throws
			// Exception {
			String monthsCarr, String priorPolicyNum, String autoInsSince, String resType) throws Exception {

		// ********************** PAGE 5
		// *********************************************************************************
		wt.getDropdown(By.xpath("//select[@id='PolicyCurrentInsuranceValue']"), 3, currSt);
		wt.getDropdown(By.xpath("//select[@id='PolicyPrevInsuranceCarrierValue']"), 3, priorCarr);
		wt.locateElement(By.xpath("//input[@id='PolicyAutoDataPriorAutoPolicyExpirationDate']"), 3)
				.sendKeys(priorExpDt);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataPrevLiabilityType']"), 3, liability);
		wt.locateElement(By.xpath("//input[@id='PolicyPriorPolicyDuration']"), 3).sendKeys(monthsCarr);
		wt.locateElement(By.xpath("//input[@id='PolicyPriorSafecoPolicyNumber']"), 3).sendKeys(priorPolicyNum);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataResidenceType']"), 3, resType);
		// Thread.sleep(3000);
		// wt.clickWhenReady(By.xpath("//input[@id='PolicyCustomerSinceDate']"),
		// 3);
		wt.locateElement(By.xpath("//input[@id='PolicyCustomerSinceDate']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyCustomerSinceDate']"), 3).sendKeys(autoInsSince);
		// wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataResidenceType']"),
		// 3, resType);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

	}

	
	@Test(dependsOnMethods = { "underwriting" }, dataProvider = "Coverages")
	public void coverages(String pptyTerm, String covLevel, String CSL, String uninsMotorCSL, String BI, String PD,
			// String monthsCarr, String priorPolicyNum, String resType) throws
			// Exception {
			String uninsBodilyInjury) throws Exception {

		// ********************** PAGE 6
		// *********************************************************************************
		if (pptyTerm.equals("6")) {
			wt.locateElement(By.xpath("//input[@id='PolicyPolicyTerm0']"), 3).click();
		} else {
			wt.locateElement(By.xpath("//input[@id='PolicyPolicyTerm1']"), 3).click();
		}
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataPackageSelection']"), 3, covLevel);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonCSLLimit']"), 3, CSL);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonUMCSLLimit']"), 3, uninsMotorCSL);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonBILimit']"), 3, BI);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonPDLimit']"), 3, PD);
		wt.getDropdown(By.xpath("//select[@id='PolicyAutoDataCommonUMLimit']"), 3, uninsBodilyInjury);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

	}

	@Test(dependsOnMethods = { "coverages" })
	public void summary() throws Exception {

		// ********************** PAGE 7
		// *********************************************************************************
		wt.locateElement(By.xpath("//input[@id='PolicyPostNotificationYN']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

	}

	@Test(dependsOnMethods = { "summary" }, dataProvider = "OrderReports")
	public void orderReports(String license, String mvrAction, String clueReport) throws Exception {

		// ********************** PAGE
		// 8*********************************************************************************
		wt.locateElement(By.xpath("//input[@id='PolicyDrivers1LicenseNumber']"), 3).sendKeys(license);
		wt.getDropdown(By.xpath("//select[@id='PolicyDrivers1MVRMVROrderWaive']"), 3, mvrAction);
		wt.getDropdown(By.xpath("//select[@id='PolicyCLUECLUEOrderWaive']"), 3, clueReport);
		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

	}

	@Test(dependsOnMethods = { "orderReports" }, dataProvider = "Billing")
	public void billing(String fName, String lName, String rountingNum, String accNum) throws Exception {

		// ********************** PAGE 9
		// *********************************************************************************

		wt.locateElement(By.xpath("//input[@id='PolicyBillingPlanAutoDeduction2']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyBillingPlanPaymentMethod3']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyMonthlyPaymentPlanNewExisting0']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyAccountType1']"), 3).click();

		wt.locateElement(By.xpath("//input[@id='PolicyEFTFirstName']"), 3).sendKeys(fName);
		wt.locateElement(By.xpath("//input[@id='PolicyEFTLastName']"), 3).sendKeys(lName);
		wt.locateElement(By.xpath("//input[@id='PolicyDFIRoutingNumber']"), 3).sendKeys(rountingNum);
		wt.locateElement(By.xpath("//input[@id='PolicyDFIAccountNumber']"), 3).sendKeys(accNum);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

	}

	@Test(dependsOnMethods = { "billing" }, dataProvider = "Issue")
	public void issue(String phone1, String phone2, String phone3, String email1, String email2) throws Exception {

		// ********************** PAGE 10
		// *********************************************************************************
		wt.selByIndex(By.xpath("//select[@id='PolicyLocations1TaxCode']"), 3, 1);
		wt.locateElement(By.name("PolicyClientHomePhoneNumberAreaCode"), 3).sendKeys(phone1);
		wt.locateElement(By.name("PolicyClientHomePhoneNumberPrefix"), 3).sendKeys(phone2);
		wt.locateElement(By.name("PolicyClientHomePhoneNumberSuffix"), 3).sendKeys(phone3);
		wt.locateElement(By.xpath("//input[@id='PolicyClientEmailAddress']"), 3).sendKeys(email1);
		wt.locateElement(By.xpath("//input[@id='PolicyIsEffectiveDateValidYNY']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyInsuranceCancelNonRenewYNN']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyAutoDataAllDriversListedYNY']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyElectronicSignatureDocusignPrimaryInsuredEmail']"), 3).click();
		wt.locateElement(By.xpath("//input[@id='PolicyElectronicSignatureDocusignPrimaryInsuredEmail']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='PolicyElectronicSignatureDocusignPrimaryInsuredEmail']"), 3)
				.sendKeys(email2);

		wt.locateElement(By.xpath("//input[@id='Continue']"), 3).click();

	}

	@Test()
	public void policy() throws IOException, InterruptedException {
		//Reporter.log("thread is " +Thread.currentThread().getId() +" started", true );
		wt.locateElement(By.xpath("//input[@id='SAMSearchMyActivityOnly']"), 3).click();
		wt.getDropdown(By.xpath("//select[@id='SAMSearchModifiedDateRange']"), 3, "Today");
		// String nameSearch = lastName + "," + firstName;
		// System.out.println("nameSearch :" +nameSearch);
		wt.locateElement(By.xpath("//input[@id='SAMSearchName']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='SAMSearchName']"), 3).sendKeys("TestLastDD,TestoneDD");
		wt.locateElement(By.xpath("//input[@id='SAMSearchAgentNumber']"), 3).clear();
		wt.locateElement(By.xpath("//input[@id='SAMSearchAgentNumber']"), 3).sendKeys("897404");
		wt.locateElement(By.xpath("//input[@id='asearch']"), 3).click();
		//Reporter.log("thread is " +Thread.currentThread().getId() + " ended", true);
		StringBuilder sb = new StringBuilder();

		//List<WebElement> list = wt.getListElements(By.xpath("//div[@id='divMain']//a[@title='Click to Display Activity']"), 3);

		if (gm.isElementPresent("xpath", "//div[@id='divMain']//a[@class='cai']")) {
			sb.append(wt.locateElement(By.xpath("//div[@id='divMain']//a[@class='cai']"), 3).getText()).append(",");
		}
		
		if (gm.isElementPresent("xpath","//div[@id='divMain']//a[@title='Click to Display Activity']")) {
			List<WebElement> list = wt.getListElements(By.xpath("//div[@id='divMain']//a[@title='Click to Display Activity']"), 3);
			System.out.println("list size &&&&&: " + list.size());
			List<String> listarr = new ArrayList<String>();

			for (WebElement W : list) {
				System.out.println(W.getAttribute("href"));
				if (W.getAttribute("href") != null) {
					//driver.get(W.getAttribute("href"));
				//	sb.append(wt.locateElement(By.xpath("//div[@id='divMain']//a[@class='cai']"), 4).getText()).append(",");
					//Thread.sleep(1000);
					// System.out.println("%%%%");
					listarr.add(W.getAttribute("href"));
				}
			}

			System.out.println("list array size which contains all the links: " +listarr.size());
			for (String link : listarr) {
				System.out.println("link: " + link);
				driver.get(link);
				//ss.getScreenshots();
				Thread.sleep(1000);
				if (gm.isElementPresent("xpath", "//div[@id='divMain']//td[contains(@class,'cai') and contains(text(),'Incomplete')]"))
				{
					continue;
				}
				else
				{
				sb.append(wt.locateElement(By.xpath("//div[@id='divMain']//a[@class='cai']"), 3).getText()).append(",");
				Thread.sleep(2000);
				}
				
			}
			
			
		}
		//ss.getScreenshots();

		String policy = sb.toString();
		policy = policy.substring(0, policy.length() - 1);
		System.out.println("result: " + policy); 
		//row =0;
		//		agencyId= "897404";
		
		//String nameSearch = "TestLastAA, TestthreeAA";
		//if (!policy.isEmpty() && policy != null) {
		//	System.out.println("writing into row: " +row);
		//	ew.writeExcel("PolicyCreated");
		//	ew.setCellData(row, nameSearch, agencyId, policy);
		// }

	}*/ 

	

	@AfterMethod
	public void getScreenshots(ITestResult result) throws Exception {
			if(result.getStatus() == ITestResult.FAILURE)
			{
				String path = sf.getScreenshotsOnFailure(result.getName());
				String imagePath = test.addScreenCapture(path);
				
				test.log(LogStatus.FAIL, "test failed ",imagePath);
			}
			
			if(result.getStatus() == ITestResult.SUCCESS)
			{
				/*sf.getScreenshotsOnSuccess(result.getName());
				test.log(LogStatus.PASS, "test passed ");*/
				
				String path = sf.getScreenshotsOnSuccess(result.getName());
				String imagePath = test.addScreenCapture(path);
				
				test.log(LogStatus.PASS, "test passed ",imagePath);
			}
			

	}
	
	@AfterClass
	public  void afterClass()
	{
		rpt.endTest(test);
		rpt.flush();
	}
	

	public WebDriver getDriver() {
		return driver;
	}
}
