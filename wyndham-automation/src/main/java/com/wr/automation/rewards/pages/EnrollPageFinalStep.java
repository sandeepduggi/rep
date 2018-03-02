package com.wr.automation.rewards.pages;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.Assert;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;


public class EnrollPageFinalStep extends BasePage<EnrollPageFinalStep> {

	private static Logger Log = Logger.getLogger(EnrollPageFinalStep.class.getName());

	@FindBy(id = "addressType0")
	WebElement addressType0;

	@FindBy(name = "address1-0")
	WebElement address1;

	@FindBy(id = "address2-0")
	WebElement address2;

	@FindBy(name = "address3-0")
	WebElement address3;

	@FindBy(id = "city0")
	WebElement city0;

	@FindBy(name = "state0")
	WebElement state0;

	WebElement zipCode;

	@FindBy(xpath = "//*[@id='profileSetup']/button")
	WebElement submit;

	WebDriver driver;

	public EnrollPageFinalStep() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	Select addressType;

	public EnrollPageFinalStep openPage() {
		return super.openPage(EnrollPageFinalStep.class);
	}
	
	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
		addressType = new Select(addressType0);
		zipCode = driver.findElement(By.name("zipCode0"));
		Log.info("zipcode text" + zipCode.getAttribute("value"));

		// Assert.assertEquals("testuser_14", str);
	}

	static void waitForPageLoad(WebDriver wdriver) {
		WebDriverWait wait = new WebDriverWait(wdriver, 60);

		Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

			@Override
			public boolean apply(WebDriver input) {
				return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
			}

		};
		wait.until(pageLoaded);

	}

	@Override
	protected void load() {

	}

	public void submit(String add1, String add2, String city) {

		Log.info("submit text" + submit.getText());
		Log.info("zipcode text" + zipCode.getAttribute("value"));

		addressType.selectByVisibleText("Personal");
		address1.sendKeys(add1);
		address2.sendKeys(add2);
		city0.sendKeys(city);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);

	}

	public void submitData(int number) throws Exception {
		popuateData(number);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
		Thread.sleep(10000);
		AutomationUtils.Fluentwait(driver, "//a[@class='btn-primary']", 10);
		driver.findElement(By.xpath("//a[@class='btn-primary']")).click();
		AutomationUtils.Fluentwait(driver, "//button[@class='add-address-btn add-fields-button btn-link']", 15);
		Thread.sleep(3000);
		if(!driver.getCurrentUrl().contains("profile")){
			Assert.assertTrue(false);
		}
	}

	private void popuateData(int number) {
		XSSFRow row = ExcelUtils.getRowData("Enrollment", number);
		addressType.selectByVisibleText(row.getCell(ExcelUtils.getColumnNumber(Constants.addressType)).getStringCellValue());
		address1.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.address1)).getStringCellValue());
		address2.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.address2)).getStringCellValue());
		city0.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.city)).getStringCellValue());
	}

	public void clear() {
		address1.clear();
		address2.clear();
		city0.clear();
	}
}
