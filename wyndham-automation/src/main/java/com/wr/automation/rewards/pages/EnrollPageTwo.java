package com.wr.automation.rewards.pages;



import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;



public class EnrollPageTwo extends BasePage<EnrollPageTwo> {

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(name = "confirmPassword")
	WebElement confirmPassword;

	@FindBy(xpath = "//*[@id='accountSetup']/button")
	WebElement submit;
	
	@FindBy(xpath="//span[@class='user-account']")
    WebElement memberNumber;

	@FindBy(xpath="//div[@class='top-nav-item sign-in signed-out-container']/i")
    WebElement signClick;
	
	@FindBy(xpath="//div[@class='login-join-links']//div[@class='new-account']/a")
    WebElement clickSepUpAccount;

	WebDriver driver;

	public EnrollPageTwo() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("join");
	}

	public String getUserName() {

		return TextElement.getText();
	}
	public EnrollPageTwo openPage() {
		return super.openPage(EnrollPageTwo.class);
	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
		// Assert.assertEquals("testuser_14", str);
	}

	WebElement TextElement;

	@Override
	protected void load() {

	}

	public void submitData(int number) {
		popuateData(number);
		submit.click();
	}

	private void popuateData(int number) {
		XSSFRow row = ExcelUtils.getRowData("Enrollment", number);
		username.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.userName)).getStringCellValue());
		password.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.password)).getStringCellValue());
		confirmPassword.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.confirmPassword)).getStringCellValue());
	}

	public void clear() {
		username.clear();
		password.clear();
		confirmPassword.clear();
	}

	public String getMembershipNumber() {
		String memberNu=memberNumber.getText();
		memberNu = memberNu.substring(1);
		Log.info("MemberNumber number:"+memberNu);
		return memberNu;
	}

	public void clickSetupAccount() {
		
		signClick.click();
		try {
			AutomationUtils.Fluentwait(driver, "//div[@class='login-join-links']//div[@class='new-account']/a",15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickSepUpAccount.click();
	}

}

