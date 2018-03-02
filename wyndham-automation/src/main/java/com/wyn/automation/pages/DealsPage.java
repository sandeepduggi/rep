package com.wyn.automation.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class DealsPage extends BasePage<DealsPage> {

	static WebDriver driver;
	public static String code = "";

	@FindBy(xpath = "//a[contains(@href,'bar-bonus')]")
	WebElement BarBonusClick;

	@FindBy(css = ".destination.ui-autocomplete-input")
	WebElement destination;

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

	@FindBy(xpath = "//button[@data-label='Special Rates']")
	WebElement SpecialRateClick;

	public DealsPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public DealsPage openPage() throws Exception {
		return super.openPage(DealsPage.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
	}

	public void DealsClick() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		AutomationUtils.Fluentwait(driver, "//a[contains(@href,'bar-bonus')]", 30);
		Thread.sleep(2000);
		AutomationUtils.scrollToElment(driver, "//a[contains(@href,'bar-bonus')]");
		BarBonusClick.click();

	}

	public void DealsGlobalbookingbarSearch(int number) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		XSSFRow row = ExcelUtils.getRowData("BrandsBooking1", number);
		Thread.sleep(1000);
		Log.info("Last row in Excel:" + ExcelUtils.getLastRow("BrandsBooking1"));
		String destinationPoint = ExcelUtils.getStringCellValue(row, 0);
		Log.info("Entering destination in homepage :" + destinationPoint);
		super.sendKeys(destination, destinationPoint);
		Thread.sleep(800);

		String rooms = ExcelUtils.getStringCellValue(row, 5);
		String adult = ExcelUtils.getStringCellValue(row, 6);
		String childrens = ExcelUtils.getStringCellValue(row, 7);
		//WyndhamHomePage.selectdate(ExcelUtils.getStringCellValue(row, 1), ExcelUtils.getStringCellValue(row, 2));
		Thread.sleep(1500);
		//WyndhamHomePage.selectdate(ExcelUtils.getStringCellValue(row, 3), ExcelUtils.getStringCellValue(row, 4));
		Thread.sleep(800);
		RoomsAndGuestButton.click();
		Thread.sleep(600);
		WyndhamHomePage.Roomsadd(Rooms, Adults, Childrens, rooms, adult, childrens);
		Thread.sleep(800);

		SpecialRateClick.click();
		promocodeSelect("Rate Code");
		Log.info("Clicking Search Button:" + Search);

		Search.click();
	}

	public void promocodeSelect(String rate) throws Exception {
		String Special_Rate = "";

		List<WebElement> element = driver
				.findElements(By.xpath("//div[@class='specialty-rates-radio clearfix']//following::span/span"));
		for (int i = 0; i < element.size(); i++) {

			Special_Rate = element.get(i).getText();
			if (Special_Rate.equals(rate)) {
				Thread.sleep(1500);
				AutomationUtils.scrollToElment(driver,
						"//div[@class='specialty-rates-radio clearfix']/div[3]/div[4]/div/label/div");
				Thread.sleep(1200);
				code = driver.findElement(By.xpath("//input[@type='hidden'][@name='rateCodeData']"))
						.getAttribute("value");
				if (code.equals("")) {
					Assert.assertTrue(false);
				}
				break;
			}
		}
	}
}
