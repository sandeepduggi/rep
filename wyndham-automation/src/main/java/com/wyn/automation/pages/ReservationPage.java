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

public class ReservationPage extends BasePage<ReservationPage> {

	@FindBy(id = "customerFirstName")
	WebElement firstName;

	@FindBy(id = "customerLastName")
	WebElement lastName;

	@FindBy(id = "email")
	WebElement emailAddress;

	@FindBy(id = "confirmEmail")
	WebElement ConfirmEmail;

	@FindBy(id = "customerPhone")
	WebElement Phoneno;

	@FindBy(id = "customerAddress")
	WebElement Street;

	@FindBy(id = "customerAddress2")
	WebElement Street2;

	@FindBy(id = "customerCity")
	WebElement City;

	@FindBy(id = "customerStateCode")
	WebElement State;

	@FindBy(id = "customerCountryCode")
	WebElement Country;
	@FindBy(id = "customerPostalCode")
	WebElement Postalcode;
	@FindBy(id = "myc_credit_number")
	WebElement Credit_click;

	@FindBy(id = "myc_credit_number")
	WebElement CreditCardNumber;
	@FindBy(id = "myc_full_year")
	WebElement ExpireDate;
	@FindBy(id = "myc_cvc")
	WebElement Cvv;
	@FindBy(id = "myc_postal_code")
	WebElement Zipcode;

	@FindBy(id = "myc-wallet-submitBtn")
	WebElement WalletSubmit;

	@FindBy(css = "button[class='submit btn-primary']")
	WebElement Submitbutton;

	@FindBy(css = "div[class='booking-error'] ")
	WebElement booking_error;
	WebDriver driver;

	public ReservationPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public ReservationPage openPage() throws Exception {
		return super.openPage(ReservationPage.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
	}

	public String completeReservation() {
		
		AutomationUtils.Fluentwait(driver, "input[id='myc_credit_number']");
		Log.info("Waiting for page to load page Url:" + driver.getCurrentUrl());
		populatedata1(1);
		Log.info("Resrvation data filled except the credit info data");
		AutomationUtils.scroll(driver, 0, 380);
		String sp;
		populatedata2(1);
		Log.info("Credit info data filled");
		Log.info("Complete reservation buton got pressed");
		// int
		// e=Utils.Fluentwait(driver,"//div[@id='myc-payment-method-guest']/span[text()='XXXX-1111']",15);
		// if(e==0){
		// if(BookingWorkflow.flow==1){
		// sp=Utils.capturescreenshot(driver, "ErrorScreenshot1");
		// BookingWorkflow.error="ErrorScreenshot1";
		// }if(BookingWorkflow.flow==0){
		// sp=Utils.capturescreenshot(driver, "ErrorScreenshot7");
		// BookingWorkflow.error="ErrorScreenshot7";
		// }
		// Assert.assertTrue(false);
		// }
	
		AutomationUtils.scrollToElment(driver, "//button[@class='submit btn-primary']");
		try {
			Thread.sleep(1300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		driver.findElement(By.xpath("//b[text()='Terms and Privacy']")).click();
		Submitbutton.click();
		Boolean isPresent=driver.findElements(By.xpath("//ul[@id='parsley-id-multiple-privacyterms']/li")).size()>0;
		if(isPresent){
			driver.findElement(By.xpath("//b[text()='Terms and Privacy']")).click();
			Submitbutton.click();
		}
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return "";
	}

	public String SubmitgoFree() throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		AutomationUtils.Fluentwait(driver, "//div[@class='tablet-col rate-summary-container']/div/p", 200);
		Thread.sleep(12000);
		Log.info("Waiting for page to load page Url:" + driver.getCurrentUrl());
		populategoFree();
		Log.info("Complete reservation buton got pressed");
		AutomationUtils.scrollToElment(driver, "//button[@class='submit btn-primary']");
		driver.findElement(By.xpath("//b[text()='Terms and Privacy']")).click();
		Submitbutton.click();
		Boolean isPresent=driver.findElements(By.xpath("//ul[@id='parsley-id-multiple-privacyterms']/li")).size()>0;
		if(isPresent){
			driver.findElement(By.xpath("//b[text()='Terms and Privacy']")).click();
			Submitbutton.click();
		}
		int e = AutomationUtils.Fluentwait(driver, "//*[contains(text(),'Our reservation systems')] ", 30);
		int e1 = AutomationUtils.Fluentwait(driver, "//*[contains(text(),'We are sorry')]", 30);
		if (e == 1) {
			BookingWorkflow.error = "ErrorScreenshot2";
			BookingWorkflow.errorDetails = "Server Error";
			Assert.assertTrue(false);
		}
		if (e1 == 1) {
			BookingWorkflow.error = "ErrorScreenshot2";
			BookingWorkflow.errorDetails = "points not available for given credentails";
			Assert.assertTrue(false);
		}
		return "";
	}

	public String SubmitgoFast() throws Exception {
		AutomationUtils.Fluentwait(driver, "input[id='myc_credit_number']");
		populategoFree();
		Log.info("Waiting for page to load page Url:" + driver.getCurrentUrl());
		Log.info("Complete reservation buton got pressed");
		int e = AutomationUtils.Fluentwait(driver, "//span[text()='XXXX-1111']", 15);
		if (e == 0) {
			BookingWorkflow.error = "ErrorScreenshot4";
			Assert.assertTrue(false);
		}
		
		AutomationUtils.scrollToElment(driver, "//button[@class='submit btn-primary']");
		driver.findElement(By.xpath("//b[text()='Terms and Privacy']")).click();
		Submitbutton.click();
		Boolean isPresent=driver.findElements(By.xpath("//ul[@id='parsley-id-multiple-privacyterms']/li")).size()>0;
		if(isPresent){
			driver.findElement(By.xpath("//b[text()='Terms and Privacy']")).click();
			Submitbutton.click();
		}
		int e1 = AutomationUtils.Fluentwait(driver, "//*[contains(text(),'Our reservation systems')] ", 30);
		if (e1 == 1) {
			BookingWorkflow.error = "ErrorScreenshot3";
			BookingWorkflow.errorDetails = "Server Error";
			Assert.assertTrue(false);
		}
		return "";
	}

	public void populategoFree() {
		XSSFRow row = ExcelUtils.getRowData("BrandsBooking2", 1);
		Street.clear();
		Street.sendKeys(ExcelUtils.getStringCellValue(row, 4));
		Boolean isPresent=driver.findElements(By.xpath("//*[@id='customerAddress2']")).size()>0;
		if(isPresent){
		Street2.clear();
		Street2.sendKeys(ExcelUtils.getStringCellValue(row, 5));
		}
		City.clear();
		City.sendKeys(ExcelUtils.getStringCellValue(row, 6));
		selectDropDownText(State, ExcelUtils.getStringCellValue(row, 7));
		selectDropDownText(Country, ExcelUtils.getStringCellValue(row, 8));
	}

	private void populatedata1(int number) {
		Log.info("Enterted to Reservation data entering method");
//		Boolean isPresent = driver.findElements(By.cssSelector("input[id='confirmEmail']")).size() > 0;
		XSSFRow row = ExcelUtils.getRowData("BrandsBooking2", number);
		firstName.sendKeys(ExcelUtils.getStringCellValue(row, 0));
		lastName.sendKeys(ExcelUtils.getStringCellValue(row, 1));
		emailAddress.sendKeys(ExcelUtils.getStringCellValue(row, 2));
//		if (isPresent) {
//			ConfirmEmail.sendKeys(ExcelUtils.getStringCellValue(row, 2));
//		}
		Phoneno.sendKeys(ExcelUtils.getNumericCellValue(row, 3));
		Street.sendKeys(ExcelUtils.getStringCellValue(row, 4));
//		Boolean isPresent1 = driver.findElements(By.cssSelector("input[id='customerAddress2']")).size() > 0;
//		if (isPresent1) {
//			Street2.sendKeys(ExcelUtils.getStringCellValue(row, 5));
//		}

		City.sendKeys(ExcelUtils.getStringCellValue(row, 6));
		selectDropDownText(State, ExcelUtils.getStringCellValue(row, 7));
		selectDropDownText(Country, ExcelUtils.getStringCellValue(row, 8));
		Postalcode.sendKeys(ExcelUtils.getNumericCellValue(row, 9));
	}

	private void populatedata2(int number) {
		Log.info("Entered to Credit card data entering method entered");
		XSSFRow row = ExcelUtils.getRowData("BrandsBooking2", number);
		CreditCardNumber.sendKeys(ExcelUtils.getStringCellValue(row, 10));
		ExpireDate.sendKeys(ExcelUtils.getNumericCellValue(row, 11));
		Cvv.sendKeys(ExcelUtils.getNumericCellValue(row, 12));
		Zipcode.sendKeys(ExcelUtils.getNumericCellValue(row, 13));
		try {
			super.click(WalletSubmit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String Bookingformvalidation() throws Exception {

		AutomationUtils.Fluentwait(driver, "input[id='myc_credit_number']");
		

		AutomationUtils.scrollToElment(driver, "//button[@class='submit btn-primary']");

		Submitbutton.click();

		String error = "";
		

		List<WebElement> elements = driver.findElements(By.xpath("//li[@class='parsley-required']"));
		for (int i = 0; i < elements.size(); i++) {
			error = elements.get(i).getText();
			if (!error.contains("equired")) {
				Assert.assertTrue(false);
			}
		}

		return null;
	}

}
