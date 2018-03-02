package com.wyn.automation.pages;

import static org.testng.Assert.fail;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class RnRPage extends BasePage<RnRPage> {

	WebDriver driver;
	@FindBy(id = "wrUsername")
	WebElement Username;
	@FindBy(id = "wrPassword")
	WebElement password;
	@FindBy(css = "div[class='login-btn-text']")
	WebElement Signin;

	@FindBy(xpath = "//p[@id='securityQuestion']")
	WebElement AskSecQuesion;
	@FindBy(id = "securityAnswer")
	WebElement Answer;
	@FindBy(id = "submit-button")
	WebElement Submitbutton;

	public RnRPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public RnRPage openPage() throws Exception {
		return super.openPage(RnRPage.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}

	public String clickBarPlusButton() throws Exception {
		String sp;
		String Special_Rate = "SDC2";
		int e = AutomationUtils.Fluentwait(driver,
				"//div[contains(@class,'listing by-room')]/div//div[@class='room']/ul//li[@class='rate cash-rate']/div[3]",
				100);
		Thread.sleep(5000);
		if (e == 0) {
			Assert.assertTrue(false);
		}
		Log.info("Waiting till RNR Page get loaded PageUrl:" + driver.getCurrentUrl());
		Log.info("Scrooling down the page to first button");
		ratebuttonClick(Special_Rate);
		Thread.sleep(800);
		Log.info("Clicking the button");
		// ClickRoomsnRateBtn();
		Log.info("Button got Clicked");

		return "";

	}

	public String clickRateButton(String Special_Rate) throws Exception {
		String sp;
		int e = AutomationUtils.Fluentwait(driver,
				"//div[contains(@class,'listing by-room')]/div//div[@class='room']/ul//li[@class='rate cash-rate']/div[3]",
				100);

		if (e == 0) {
			Assert.assertTrue(false);
		}
		Log.info("Waiting till RNR Page get loaded PageUrl:" + driver.getCurrentUrl());
		Log.info("Scrooling down the page to first button");
		ratebuttonClick(Special_Rate);

		Log.info("Clicking the button");
		// ClickRoomsnRateBtn();
		Log.info("Button got Clicked");

		return "";

	}

	public void ratebuttonClick(String Rate) {

		Boolean IsPresent1 = driver.findElements(By.xpath("//div[@class='rate-not-found']/h2")).size() > 0;
		if (IsPresent1) {
			String Expected = "The rate you requested is not available.";
			String Actaul = driver.findElement(By.xpath("//div[@class='rate-not-found']/h2")).getText();
			if (Actaul.contains(Expected)) {
				Assert.assertTrue(false);
			}
		}
		Boolean IsPresent = driver
				.findElements(By.xpath("//div[@class='room'][1]//li[contains(@class,'rate cash-rate')]")).size() > 5;
		if (IsPresent) {
			List<WebElement> element = driver.findElements(By.xpath("//div[@class='room']//a[@class='see-more-text']"));
			for (int i = 0; i < element.size(); i++) {
				AutomationUtils.scrollToElment(driver, "//div[contains(@class,'listing by-room')]//div[@class='room']["
						+ (i + 1) + "]//a[@class='see-more-text']");
				element.get(i).click();
				Boolean isPresent = driver
						.findElements(By.xpath("//div[contains(@class,'listing by-room')]//div[@class='room']["
								+ (i + 1) + "]//li[@rate='" + Rate + "']"))
						.size() > 0;
				if (isPresent) {
					AutomationUtils.scrollToElment(driver,
							"//div[contains(@class,'listing by-room')]//div[@class='room'][" + (i + 1) + "]//li[@rate='"
									+ Rate + "']");
					driver.findElement(
							By.xpath("//div[contains(@class,'listing by-room')]//div[@class='room'][1]//li[@rate='"
									+ Rate + "']//div[@class='book-btn']/a"))
							.click();
					break;
				}
				if ((i + 1) == element.size()) {
					Assert.assertTrue(false);
				}
			}
		} else {
			Boolean isPresent = driver.findElements(By.xpath(
					"//div[contains(@class,'listing by-room')]//div[@class='room'][1]//li[@rate='" + Rate + "']"))
					.size() > 0;
			if (isPresent) {
				AutomationUtils.scrollToElment(driver,
						"//div[contains(@class,'listing by-room')]//div[@class='room'][1]//li[@rate='" + Rate + "']");
				driver.findElement(
						By.xpath("//div[contains(@class,'listing by-room')]//div[@class='room'][1]//li[@rate='" + Rate
								+ "']//div[@class='book-btn']/a"))
						.click();
			} else {
				Assert.assertTrue(false);
			}
		}

	}

	public String ClickgoFree_goFast(String type) throws Exception {
		String sp;
		int k = 0;
		int e = AutomationUtils.Fluentwait(driver, "//div[contains(@class,'listing by-room')]/div/div[2]/ul/li", 100);
		Thread.sleep(3500);
		if (e == 0) {
			sp = AutomationUtils.captureScreenShot(driver, "ErrorScreenshot");
			Assert.assertTrue(false);
		}
		if (type == "gofree") {
			k = 1;
		} else {
			k = 2;
		}
		Log.info("Waiting till RNR Page get loaded PageUrl:" + driver.getCurrentUrl());
		Log.info("Scrooling down the page to first button");
		Thread.sleep(500);
		Thread.sleep(800);
		Log.info("Clicking the button");
		ClickgoFree_goFastBtn(k);

		return "";
	}

	public void LoginEntry(int TestNo) throws Exception {
		Thread.sleep(2000);
		String sp;
		XSSFRow row = ExcelUtils.getRowData("BrandsBooking1", TestNo);
		Username.sendKeys(ExcelUtils.getStringCellValue(row, 9));
		password.sendKeys(ExcelUtils.getStringCellValue(row, 10));
		Signin.click();
		AutomationUtils.Fluentwait(driver, "//p[@id='securityQuestion']", 40);
		Thread.sleep(8500);
		String Question = AskSecQuesion.getText();
		if (Question.equals("What elementary school did you attend?")) {
			Answer.sendKeys("school");
		}
		if (Question.equals("What is your mother's maiden name?")) {
			Answer.sendKeys("mother");
		}
		if (Question.equals("In what city were you born?")) {
			Answer.sendKeys("city");
		}
		if (Question.equals("In what city or town was your first job?")) {
			Answer.sendKeys("job");
		}
		if (Question.equals("In what city or town did you meet your spouse/partner?")) {
			Answer.sendKeys("city");
		}
		if (Question.equals("In what city or town does your nearest sibling live?")) {
			Answer.sendKeys("town");
		}
		Submitbutton.click();
		
	}

	public void ClickRoomsnRateBtn() {
		List<WebElement> prop = driver
				.findElements(By.xpath("//div[@class='room'][1]/ul/li[@class='rate cash-rate']/div[2]/a"));
		Log.info("Method for Cliking Rooms and rates button");
		WebElement element = null;
		for (int i = 0; i < prop.size() - 5; i++) {
			element = prop.get(i);

			if (element.getAttribute("class").contains("book-now price-button btn-primary")
					&& (!element.getAttribute("class").contains("disabled"))) {
				element.click();
				return;
			}
		}
	}

	public void ClickgoFree_goFastBtn(int k) {
		int l = 0;
		AutomationUtils.scrollToElment(driver,
				"//div[@class='room'][1]//li[contains(@class,'rate points-rate')][@style='display: list-item;']");
		List<WebElement> prop = driver.findElements(By.xpath(
				"//div[@class='room'][1]//li[contains(@class,'rate points-rate')][@style='display: list-item;']/div[3]/a"));
		Log.info("Method for Cliking Rooms and rates button");
		WebElement element = null;
		for (int j = 0; j <= prop.size(); j++) {
			l++;
			if (k == l) {
				prop.get(j).click();
				return;
			}
		}
	}

}
