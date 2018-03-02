package com.wyn.automation.pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class FindReservation extends BasePage<FindReservation>{

	@FindBy(xpath = "//a[contains(@href,'find-reservation')]")
	WebElement findReservationClick;
	
	@FindBy(id = "findReservationFirstName")
	WebElement firstName;
	
	@FindBy(id = "findReservationLastName")
	WebElement lastName;
	
	@FindBy(id = "findReservationConfirmationNum")
	WebElement confirmatioNum;
	
	@FindBy(id = "btnReviewReservation")
	WebElement submit;
	
	public FindReservation() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham");
		ExcelUtils.load("BrandsBooking2");
		
	}

	public FindReservation openPage() throws Exception {
		return super.openPage(FindReservation.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}

	public void findReservation(String confirmationNumber) {

		findReservationClick.click();
		
		XSSFRow row = ExcelUtils.getRowData("BrandsBooking2", 1);
		firstName.sendKeys(ExcelUtils.getStringCellValue(row, 0));
		lastName.sendKeys(ExcelUtils.getStringCellValue(row, 1));
		confirmatioNum.sendKeys(confirmationNumber);
		AutomationUtils.scrollToElment(driver, "//button[@id='btnReviewReservation']");
		submit.click();
	}
	
	
}
