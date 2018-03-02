package com.wyn.automation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;
import com.wyn.automaton.workflows.BookingWorkflow;

public class SearchResultsPage extends BasePage<SearchResultsPage> {

	WebDriver driver;
	@FindBy(css = "*[class='more-results-nearby']")
	WebElement NoHotels;
	@FindBy(css = "label[for='wyndham-rewards-filter']>span>span")
	WebElement Wyndhamrewardsfilter;
	@FindBy(css = ".destination.ui-autocomplete-input")
	WebElement destination;
	@FindBy(css = ".btn.check-in-button.check-in.calendar-button")
	WebElement Checkindate;
	@FindBy(xpath = "//button[contains(@class,'search-btn btn-primary')]")
	WebElement Search;
	@FindBy(xpath = "//a[@class='wr-logged-user-name']")
	WebElement SininClick;
	@FindBy(xpath = "//a[@class='sign-out-link']")
	WebElement SininOut;
	@FindBy(xpath = "//a[@class='wr-login-links login-display']")
	WebElement Signin;

	@FindBy(css = "span[class='number rooms']+button")
	WebElement Rooms;

	@FindBy(css = "span[class='number guests adults']+button")
	WebElement Adults;

	@FindBy(css = "span[class='number guests children']+button")
	WebElement Childrens;

	public SearchResultsPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public SearchResultsPage openPage() throws Exception {
		return super.openPage(SearchResultsPage.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}

	public String clickBook() throws Exception {

		if (BookingWorkflow.nohotels == 0) {
			AutomationUtils.Fluentwait(driver, "*[class*='hotel-url']");
		}
		Boolean isPresent = driver.findElements(By.cssSelector("*[class='more-results-nearby']")).size() > 0;
		Boolean isPresent1 = driver
				.findElements(By.xpath("//*[@class='more-results-nearby'][contains(text(),'More results from')]"))
				.size() > 0;
		if (isPresent == true && isPresent1 == false) {
			if (NoHotels.getText().contains("No hotels were found")) {
				BookingWorkflow.error = "ErrorScreenshot10";
				Assert.assertTrue(false);
			}
		}
		Log.info("Wait till the Hotel-url loads");		
		Log.info("First Available button will be cliked Page Url:" + driver.getCurrentUrl());
		ClickFirstAvailableButton();
		return "";
	}

	public String Change_Date_Click(int number) throws Exception {
		XSSFRow row = ExcelUtils.getRowData("Screen1", number);
		String sp;
		if (BookingWorkflow.nohotels == 0) {
			AutomationUtils.Fluentwait(driver, "*[class*='hotel-url']");
		}
		Boolean isPresent = driver.findElements(By.cssSelector("*[class='more-results-nearby']")).size() > 0;
		Boolean isPresent1 = driver
				.findElements(By.xpath("//*[@class='more-results-nearby'][contains(text(),'More results from')]"))
				.size() > 0;
		if (isPresent == true && isPresent1 == false) {
			if (NoHotels.getText().contains("No hotels were found")) {
				sp = AutomationUtils.captureScreenShot(driver, "ErrorScreenshot10");
				BookingWorkflow.error = "ErrorScreenshot10";
				Assert.assertTrue(false);
			}
		}
		Log.info("Wait till the Hotel-url loads");
		
		Log.info("Capturing screenshots");
		sp = AutomationUtils.captureScreenShot(driver, ExcelUtils.getStringCellValue(row, 10));
		Log.info("First Available button will be cliked Page Url:" + driver.getCurrentUrl());
		changeSearch();
		ClickFirstAvailableButton();

		return sp;
	}

	public String rateClick() throws Exception {
		String sp;
		Log.info("Wait till the Hotel-url loads");
		
		Log.info("Capturing screenshots");
		Log.info("Cliking wyndham rewards button Page Url:" + driver.getCurrentUrl());

		
		ClickFirstAvailablegoFree();
		return "";
	}

	public String ClickProperty() throws Exception {
		String sp;
		AutomationUtils.Fluentwait(driver, "*[class*='hotel-url']");
		
		ClickFirstAvailableProperty();
		return "";
	}

	public void clickWyndhamRewardsFilter() {
		AutomationUtils.Fluentwait(driver, "*[class*='hotel-url']");
		if (NoHotels.getText().contains("No hotels were found")) {
			BookingWorkflow.error = "ErrorScreenshot6";
			Assert.assertTrue(false);
		}
		Wyndhamrewardsfilter.click();
	}

	public void ClickFirstAvailableProperty() throws Exception {
		List<WebElement> prop = driver.findElements(By.xpath(
				"//div[@id='list-view']/child::div[@class='hotel-details-sec']/div/div/div[1]/div[3]/div/div[2]/div/a"));
		WebElement element = null;
		int m = 0;
		int k = 0;
		String sp = "";
		for (int i = 0; i < prop.size(); i++) {
			if (m < prop.size()) {
				element = prop.get(i);
				if (k != 0) {
					AutomationUtils.scroll(driver, 0, 150);
				}
				k++;
				if (element.getAttribute("class").contains("book-now price-button btn-primary")
						&& (!element.getAttribute("class").contains("disabled"))) {
					WebElement element1 = driver
							.findElement(By.xpath("//div[@id='list-view']/child::div[@class='hotel-details-sec']["
									+ (i + 1) + "]/div/div/div[1]/div[2]/div/h2/a"));
					element1.click();
					return;
				}
			} else {
				sp = AutomationUtils.captureScreenShot(driver, "ErrorScreenshot6");
				BookingWorkflow.error = "ErrorScreenshot6";
				BookingWorkflow.errorDetails = "no property avalilable";
				Assert.assertTrue(false);

			}

		}

	}

	public void ClickFirstAvailableButton() throws Exception {
		List<WebElement> prop = driver.findElements(
				By.xpath("//div[@class='hotel-details-sec']//div[contains(@class,'hotel-rate')]/div/div[2]/div/a"));
		WebElement element = null;
		Log.info("Method for clicking first Availabele property");
		int n = 0;
		int m = 0;
		int k = 0;
		String sp = "";
		for (int j = 0; j <= prop.size(); j++) {
			m++;
			if (n != 0) {
				AutomationUtils.scroll(driver, 0, 150);
			}
			n++;
			element = prop.get(j);
			if (m < prop.size()) {
				if (element.getAttribute("class").contains("book-now price-button btn-primary")
						&& (!element.getAttribute("class").contains("disabled"))) {
					k++;
					if (k == 1) {
						element.click();
						return;
					}
				}
			} else {
				sp = AutomationUtils.captureScreenShot(driver, "ErrorScreenshot6");
				BookingWorkflow.error = "ErrorScreenshot6";
				BookingWorkflow.errorDetails = "no property avalilable";
				Assert.assertTrue(false);
			}
		}
	}

	public void ClickFirstAvailablegoFree() throws Exception {
		String sp;
		int y = AutomationUtils.YLocation(driver,
				"//div[@class='hotel-details-sec']//div[contains(@class,'hotel-rewards')]/div/div[1]/div/a/div");
		if (y == 0) {
			sp = AutomationUtils.captureScreenShot(driver, "ErrorScreenshot6");
			BookingWorkflow.error = "ErrorScreenshot6";
			BookingWorkflow.errorDetails = "property not avaiable to book";
			Assert.assertTrue(false);
		}
		AutomationUtils.scroll(driver, 0, y - 400);
		
		List<WebElement> prop = driver.findElements(By.xpath(
				"//div[@class='hotel-details-sec']//div[contains(@class,'hotel-rewards')]/div/div[1]/div/a/div"));
		WebElement element = null;
		Log.info("Method for clicking first Availabele property");

		for (int j = 0; j <= prop.size(); j++) {
			prop.get(j).click();
			return;
		}
	}

	public void changeSearch() throws Exception {
		AutomationUtils.Fluentwait(driver, "*[class*='hotel-url']");
		
		driver.findElement(By.xpath("//a[text()='Change Search']")).click();
		XSSFRow row = ExcelUtils.getRowData("Search_Results", 1);
		Log.info("Entering destination in homepage URL:" + driver.getCurrentUrl());
		destination.clear();
		sendKeys(destination, ExcelUtils.getStringCellValue(row, 0));
		
		Checkindate.click();
		
		//WyndhamHomePage.selectdate(ExcelUtils.getStringCellValue(row, 1), ExcelUtils.getStringCellValue(row, 2));
		
		//WyndhamHomePage.selectdate(ExcelUtils.getStringCellValue(row, 3), ExcelUtils.getStringCellValue(row, 4));
		
		Search.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public String SearchResultErrorvalidation() throws Exception {
		
		String Error = driver.findElement(By.xpath("//h5[@class='more-results-nearby']")).getText();
		if (!Error.contains("No hotels were found")) {
			Assert.assertTrue(false);
		}

		driver.findElement(By.xpath("//a[text()='Change Search']")).click();
		
		destination.clear();
		sendKeys(destination, "Florida City,");
		Search.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		AutomationUtils.Fluentwait(driver, "//div[@class='hotel-details-sec brand-divider'][1]//h5[@class='more-results-nearby']", 15);
        Thread.sleep(1200);
		String Error1 = driver
				.findElement(By
						.xpath("//div[@class='hotel-details-sec brand-divider'][1]//h5[@class='more-results-nearby']"))
				.getText();
		if (!Error1.contains("Hotels and Resorts were found based on the selections you made")) {
			Assert.assertTrue(false);
		}

		driver.findElement(By.xpath("//a[text()='Change Search']")).click();
		
		destination.clear();
		sendKeys(destination, "XXXX");
		Search.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		

		Error = driver.findElement(By.xpath("//li[@class='parsley-destinationNotFound']")).getText();
		if (!Error.contains("Location is invalid")) {
			Assert.assertTrue(false);
		}

		
		destination.clear();
		sendKeys(destination, "North Bergen");
		
		Search.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		AutomationUtils.Fluentwait(driver, "*[class*='hotel-url']");

		ClickFirstAvailableButton();

		return null;
	}

	public void validateDefaultState() {
		
		try {
			AutomationUtils.Fluentwait(driver, "//div[@class='hotel-details-sec']//div[contains(@class,'hotel-rewards')]/div/div[1]/div/a/div/span", 50);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertTrue(false);
		}
		List<WebElement> prop = driver.findElements(By.xpath(
				"//div[@class='hotel-details-sec']//div[contains(@class,'hotel-rewards')]/div/div[1]/div/a/div/span"));
		WebElement element = null;
		Log.info("Method for clicking first Availabele property");

		for (int j = 0; j <= prop.size(); j++) {
			String value=prop.get(j).getText();
			if(!value.equals("PTS")){
				Assert.assertTrue(false);
			}
			return;
		}
		
	}

	public void logOut() {
		
		SininClick.click();

		SininOut.click();		
	}

}
