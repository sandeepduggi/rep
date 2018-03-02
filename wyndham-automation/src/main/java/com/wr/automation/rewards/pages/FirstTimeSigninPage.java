package com.wr.automation.rewards.pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class FirstTimeSigninPage extends BasePage<FirstTimeSigninPage> {

	
	@FindBy(id="memberNumber")
    WebElement memberNumber;
	
	@FindBy(id = "email")
	WebElement emailAddress;
	
	
	@FindBy(id = "zipCode")
	WebElement zipCode0;
	
	@FindBy(xpath = "//button[@class='submit btn btn-primary btn-block']")
	WebElement continueSubmit;
	
	
	public FirstTimeSigninPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public FirstTimeSigninPage openPage() throws Exception {
		return super.openPage(FirstTimeSigninPage.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}

	public void memberDetails(String member) {
		
		try {
			AutomationUtils.Fluentwait(driver,"//input[@id='memberNumber']", 20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		memberNumber.sendKeys(member);
		XSSFRow row = ExcelUtils.getRowData("Enrollment", 1);
		
		sendKeys(emailAddress, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.email)));
		
		sendKeys(zipCode0, ExcelUtils.getNumericCellValue(row, ExcelUtils.getColumnNumber(Constants.zipCode)));
		
		continueSubmit.click();
		
	}
}
