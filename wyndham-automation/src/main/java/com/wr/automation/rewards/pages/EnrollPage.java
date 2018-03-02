package com.wr.automation.rewards.pages;



import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.wyn.automation.utils.AutomationUtils;

import org.openqa.selenium.By;

public class EnrollPage extends BasePage<EnrollPage> {

	@FindBy(id = "firstName")
	WebElement firstName;

	@FindBy(id = "lastName")
	WebElement lastName;

	@FindBy(id = "emailAddress")
	WebElement emailAddress;

	@FindBy(id = "country0")
	WebElement country0;

	@FindBy(id = "zipCode0")
	WebElement zipCode0;

	@FindBy(xpath = "//*[@id='phoneNumber0']")
	WebElement phoneNumber0;

	@FindBy(name = "phoneType0")
	WebElement phoneType0;

	@FindBy(id = "preferred")
	WebElement preferred;

	@FindBy(xpath = "//*[@id='liteEnroll']/button")
	WebElement submit;

	@FindBy(className = "user-name")
	WebElement userName;
	
	@FindBy(xpath="//div[@class='form-error help-block'][@style='display: block;']")
    WebElement Error;
	
	@FindBy(xpath="//div[@class='top-nav-item sign-in signed-out-container']/div")
    WebElement SignIn;
	
	@FindBy(xpath="//input[@name='login-username']")
    WebElement userId;
	
	@FindBy(name="login-password")
    WebElement password;
	
	@FindBy(xpath="//button[text()='Sign In']")
    WebElement SignInClick;
	
	@FindBy(id="question0")
    WebElement questionSelect0;
	

	@FindBy(id="question1")
    WebElement questionSelect1;
	

	@FindBy(id="question2")
    WebElement questionSelect2;
	
	@FindBy(xpath="//input[contains(@id,'answer')]")
    WebElement  answerEnter0;
	
	@FindBy(id="answer1")
    WebElement answerEnter1;
	
	@FindBy(id="answer2")
    WebElement answerEnter2;
	
	@FindBy(xpath="//button[@data-save-label='Submit']")
    WebElement submitAnswers;
	
	@FindBy(xpath="//div[contains(@class,'signed-in-container recognized-container signed')]")
    WebElement signinConainer;
	
	@FindBy(xpath="//a[text()='My Account']")
    WebElement myAccount;
	
	@FindBy(xpath="//input[@id='answer1']")
    WebElement verifyAns;
	
	@FindBy(id="currentPassword")
    WebElement currentPsd;
	
	@FindBy(id="password")
    WebElement newPsd;
	
	@FindBy(id="confirmPassword")
    WebElement confirmPsd;
	
	@FindBy(xpath="//form[@id='securityUpdateUsernamePassword']//button[@data-save-label='SAVE CHANGES']")
    WebElement saveChanges;
	
	
	
	WebDriver driver;

	public EnrollPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham-rewards/join");
	}

	public EnrollPage openPage() throws Exception {
		return super.openPage(EnrollPage.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}

	public void submitRecord(int number,String Sheet) throws Exception {
//		clear();
		ExcelUtils.load(Sheet);
		popuateData(number,Sheet);
		AutomationUtils.scrollToElment(driver, "//*[@class='submit btn btn-primary btn-block']");
		submit.click();
	}
	
	private void popuateData(int number,String Sheet) {
		XSSFRow row = ExcelUtils.getRowData(Sheet, number);
		sendKeys(firstName, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.firstName)));
		sendKeys(lastName, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.lastName)));
		sendKeys(emailAddress, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.email)));
		sendKeys(country0, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.country)));
		sendKeys(zipCode0, ExcelUtils.getNumericCellValue(row, ExcelUtils.getColumnNumber(Constants.zipCode)));
		sendKeys(phoneNumber0, ExcelUtils.getNumericCellValue(row, ExcelUtils.getColumnNumber(Constants.phoneNumber)));
		sendKeys(phoneType0, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.phoneType)));
		sendKeys(preferred, ExcelUtils.getBooleanCellValue(row, ExcelUtils.getColumnNumber(Constants.preferred)));
		
	}

	public void clear() {
		firstName.clear();
		lastName.clear();
		emailAddress.clear();
		zipCode0.clear();
		phoneNumber0.clear();

	}

	



}

