package com.wyn.automation.fragments;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class BookingFragment extends BasePage<BookingFragment> {
	ExtentReports extent;
	ExtentTest test;
	static XSSFRow row = ExcelUtils.getRowData("Screen1", 1);

	@FindBy(css = ".destination.ui-autocomplete-input")
	static WebElement destination;

	@FindBy(xpath = "//button[contains(@class,'search-btn btn-primary')]")
	WebElement Search;

	@FindBy(css = "span[class='number rooms']+button")
	WebElement Rooms;

	@FindBy(css = "span[class='number guests adults']+button")
	WebElement Adults;

	@FindBy(css = "span[class='number guests children']+button")
	WebElement Childrens;

	@FindBy(css = "button[class*='has-caret mask rooms-and-guests-button rooms-and-guest']")
	static WebElement RoomsAndGuestButton;

	@FindBy(css = ".btn.check-in-button.check-in.calendar-button")
	WebElement Checkindate;

	static WebDriver driver;

	public BookingFragment() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public BookingFragment openPage() throws Exception {
		return super.openPage(BookingFragment.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}

	public void changeCheckIn(String ScreenName) {
		XSSFRow row = ExcelUtils.getRowData(ScreenName, 1);
		try {
			super.click(Checkindate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		selectdate(ExcelUtils.getStringCellValue(row, 1), ExcelUtils.getStringCellValue(row, 2));
	}

	public void changeCheckOut(String ScreenName) {
		XSSFRow row = ExcelUtils.getRowData(ScreenName, 1);
		selectdate(ExcelUtils.getStringCellValue(row, 3), ExcelUtils.getStringCellValue(row, 4));
	}

	public void clickSearch() {

		try {
			super.click(Search);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Special_Rate_Select(String rate) {
		String Special_Rate = "";
		List<WebElement> element = driver
				.findElements(By.xpath("//div[@class='specialty-rates-radio clearfix']//following::span/span"));
		for (int i = 0; i < element.size(); i++) {

			Special_Rate = element.get(i).getText();
			if (Special_Rate.equals(rate)) {
				element.get(i).click();
				break;
			}
		}
	}

	public void promocode_Select(String rate, String code) throws Exception {
		String Special_Rate = "";
		List<WebElement> element = driver
				.findElements(By.xpath("//div[@class='specialty-rates-radio clearfix']//following::span/span"));
		for (int i = 0; i < element.size(); i++) {

			Special_Rate = element.get(i).getText();
			if (Special_Rate.equals(rate)) {
				element.get(i).click();

				driver.findElement(By
						.xpath("//div[@class='specialty-rates-radio clearfix']/div[3]/div[1]//input[@placeholder='Enter Code']"))
						.sendKeys(code);
				break;
			}
		}
	}

	public void enterDestination(String ScreenName) throws Exception {
		XSSFRow row = ExcelUtils.getRowData(ScreenName, 1);
		Log.info("Entering destination in homepage URL:" + driver.getCurrentUrl());
		super.sendKeys(destination, ExcelUtils.getStringCellValue(row, 0));

	}

	public ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) {
		return new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(by);
				return element.isDisplayed() ? element : null;
			}
		};
	}

	public static void selectdate(String checkin_month, String Checkin_days) {

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = new String("");
		Log.info("Selecting CheckIn and CheckOut date Homepage URL:" + driver.getCurrentUrl());

		List<WebElement> elements = driver.findElements(By.xpath(
				"//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]//div[@class='ui-datepicker-title']/span"));

		for (int i = 0; i < elements.size(); i++) {
			System.out.println(elements.get(i).getText());
			if (i == 2) {
				s = s + ":" + elements.get(i).getText();
			} else {
				s = s + elements.get(i).getText();
			}
		}

		String split[] = s.split(":");
		for (int j = 0; j < split.length; j++) {
			if (split[j].equalsIgnoreCase(checkin_month)) {
				System.out.println("-------------------");
				List<WebElement> d = driver
						.findElements(By.xpath("//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]["
								+ (j + 1) + "]/table/tbody/tr/td/*"));
				for (int k = 0; k < d.size(); k++) {
					System.out.println(d.get(k).getText());
					if (d.get(k).getText().equals(Checkin_days)) {
						d.get(k).click();

					}

				}
			}
		}
	}

	public static void Rooms_add(WebElement element_Rooms, WebElement element_Adult, WebElement element_Children,
			String Rooms, String Adult, String Children) throws InterruptedException {

		Log.info("Selecting Number of Rooms,Adults,Childrens" + "Rooms:" + Rooms + "Adults:" + Adult + "Childrens:"
				+ Children);
		int rooms = Integer.parseInt(Rooms);
		int adults = Integer.parseInt(Adult);
		int childrens = Integer.parseInt(Children);
		for (int i = 1; i < rooms; i++) {

			element_Rooms.click();
		}
		for (int j = 1; j < adults; j++) {

			element_Adult.click();
		}
		for (int k = 1; k <= childrens; k++) {

			element_Children.click();
		}
	}

	public void error_validation() throws Exception {

		sendKeys(destination, "");

		AutomationUtils.scrollToElment(driver,
				"//button[contains(@class,'has-caret mask rooms-and-guests-button rooms-and-guest')]");

		RoomsAndGuestButton.click();

		Rooms_add(Rooms, Adults, Childrens, "1", "1", "1");
		Search.click();
		String Error = driver.findElement(By.xpath("//li[@class='parsley-required']")).getText();

		sendKeys(destination, "Nogales, Arizona");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Search.click();

	}

}
