package com.wr.automation.rewards.pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class ContactUsPage extends BasePage<ContactUsPage> {
	public ContactUsPage() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
		ExcelUtils.load("WrContactUsForm");
	}

	public ContactUsPage openPage() throws Exception {
		return super.openPage(ContactUsPage.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}
	@FindBy(xpath = "//ul[@class='footer-links-list']//a[contains(@href,'contact-us')]")
	WebElement clickContactUs;
	
	@FindBy(xpath = "//div[@class='button-container']/a")
	WebElement clickGetStarted;
	
	@FindBy(id = "arrivalMonth")
	WebElement arrivalMonth;

	@FindBy(id = "arrivalDay")
	WebElement arrivalDay;

	@FindBy(id = "arrivalYear")
	WebElement arrivalYear;
	
	@FindBy(id = "departureMonth")
	WebElement departureMonth;

	@FindBy(id = "departureDay")
	WebElement departureDay;

	@FindBy(id = "departureYear")
	WebElement departureYear;
	
	
	@FindBy(id = "hotelBrand")
	WebElement hotelBrand;

	@FindBy(id = "hotelAddress")
	WebElement hotelAddress;
	
	@FindBy(id = "hotelCountry")
	WebElement hotelCountry;

	@FindBy(id = "hotelCity")
	WebElement hotelCity;

	@FindBy(id = "hotelState")
	WebElement hotelState;
	
	@FindBy(id = "comment")
	WebElement description;
	
	@FindBy(xpath = "//button[@class='submit btn btn-primary btn-block contact-us-submit']")
	WebElement submitButton;
	
	public void clickContactUs() throws Exception {
		Thread.sleep(12000);
		AutomationUtils.Fluentwait(driver, "//ul[@class='footer-links-list']//a[contains(@href,'contact-us')]", 30);
		AutomationUtils.scrollToElment(driver, "//ul[@class='footer-links-list']//a[contains(@href,'contact-us')]");
		clickContactUs.click();
		
	}

	public void enterDetails(int number) throws Exception {
		
		AutomationUtils.Fluentwait(driver, "//*[@id='arrivalMonth']", 30);
		XSSFRow row = ExcelUtils.getRowData("WrContactUsForm", number);
		
		selectDropDownText(arrivalMonth, ExcelUtils.getStringCellValue(row, 1));
		selectDropDownText(arrivalDay, ExcelUtils.getStringCellValue(row, 2));
		String arrivalYear1=ExcelUtils.getStringCellValue(row, 3);
		selectDropDownText(arrivalYear, arrivalYear1);
		selectDropDownText(departureMonth, ExcelUtils.getStringCellValue(row, 4));
		selectDropDownText(departureDay, ExcelUtils.getStringCellValue(row, 5));
		String departureYear1=ExcelUtils.getStringCellValue(row, 6);
		selectDropDownText(departureYear, departureYear1);
		selectDropDownText(hotelBrand, ExcelUtils.getStringCellValue(row, 7));
		hotelAddress.sendKeys(ExcelUtils.getStringCellValue(row, 8));
		selectDropDownText(hotelCountry, ExcelUtils.getStringCellValue(row, 9));
		hotelCity.sendKeys(ExcelUtils.getStringCellValue(row, 10));
		selectDropDownText(hotelState, ExcelUtils.getStringCellValue(row, 11));
		description.sendKeys(ExcelUtils.getStringCellValue(row, 12));
	}

	public void clickSubmitButton() {
		AutomationUtils.scrollToElment(driver, "//button[@class='submit btn btn-primary btn-block contact-us-submit']");
		submitButton.click();
	}

	public void clickGetStarted() throws Exception {
		AutomationUtils.Fluentwait(driver, "//div[@class='button-container']/a", 30);
		AutomationUtils.scrollToElment(driver, "//div[@class='button-container']/a");
		clickGetStarted.click();
		
	}

	public void validateContactUs() throws Exception {
		Boolean isPresent=driver.findElements(By.xpath("//span[@class='headline-c']")).size()>0;
		Thread.sleep(2500);
		if(isPresent){
	   String value=driver.findElement(By.xpath("//span[@class='headline-c']")).getText();
		if(!value.contains("Thank")){
			Assert.assertTrue(false);
		}
		}
	}
	
}
