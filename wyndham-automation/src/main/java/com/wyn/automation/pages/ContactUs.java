package com.wyn.automation.pages;

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


public class ContactUs extends BasePage<ContactUs> {
	static WebDriver driver;

	@FindBy(xpath = "//select[@name='contactCategory']")
	WebElement Category;

	@FindBy(name = "customerFirstName")
	WebElement FirstName;

	@FindBy(name = "customerLastName")
	WebElement LastName;

	@FindBy(id = "customerAddress")
	WebElement Street;

	@FindBy(id = "customerAddress2")
	WebElement Street2;

	@FindBy(id = "customerCity")
	WebElement City;

	@FindBy(name = "customerStateCode")
	WebElement State;

	@FindBy(name = "customerCountryCode")
	WebElement Country;

	@FindBy(id = "customerPostalCode")
	WebElement PostalCode;

	@FindBy(id = "email")
	WebElement Email;

	@FindBy(id = "confirmEmail")
	WebElement confirmEmail;

	@FindBy(id = "customerPhone")
	WebElement Phone;

	@FindBy(id = "customerDateOfStay")
	WebElement DateofStay;

	@FindBy(id = "hotelName")
	WebElement HotelName;

	@FindBy(id = "hotelLocation")
	WebElement HotelLocation;

	@FindBy(name = "yourFeedback")
	WebElement YourFeedback;

	@FindBy(xpath = "//button[@class='submit btn-primary ']")
	WebElement Submit;

	@FindBy(xpath = "//a[contains(text(),'About My Stay')]")
	WebElement AboutUs_Click;

	@FindBy(xpath = "//a[contains(text(),'Best Rate Claim')]")
	WebElement BestRate_Click;

	@FindBy(xpath = "//a[contains(text(),'Corporate Account Inquiry Form')]")
	WebElement CoorporateRequest_Click;

	public ContactUs() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
		ExcelUtils.load("BrandsContactUsForm");
	}

	public ContactUs openPage() throws Exception {
		return super.openPage(ContactUs.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}
      public static String Brand="";
	public void ContactUs_Form(String category, int Contact_us_no) throws Exception {
		XSSFRow row = ExcelUtils.getRowData("BrandsContactUsForm", Contact_us_no);

		selectDropDownText(Category, category);
		Thread.sleep(800);
		FirstName.sendKeys(ExcelUtils.getStringCellValue(row, 2));
		Thread.sleep(800);
		LastName.sendKeys(ExcelUtils.getStringCellValue(row, 3));
		Thread.sleep(800);
		Street.sendKeys(ExcelUtils.getStringCellValue(row, 4));
		Thread.sleep(800);
		Street2.sendKeys(ExcelUtils.getStringCellValue(row, 5));
		Thread.sleep(800);
		City.sendKeys(ExcelUtils.getStringCellValue(row, 6));
		Thread.sleep(800);
		selectDropDownText(State, ExcelUtils.getStringCellValue(row, 7));
		Thread.sleep(800);
		selectDropDownText(Country, ExcelUtils.getStringCellValue(row, 8));
		Thread.sleep(800);
		PostalCode.sendKeys(ExcelUtils.getNumericCellValue(row, 9));
		Thread.sleep(800);
		Email.sendKeys(ExcelUtils.getStringCellValue(row, 10));
		Thread.sleep(800);
		confirmEmail.sendKeys(ExcelUtils.getStringCellValue(row, 11));
		Thread.sleep(800);
		String number = ExcelUtils.getStringCellValue(row, 12);
		Phone.sendKeys(number);
		Thread.sleep(800);
		String date = ExcelUtils.getStringCellValue(row, 13);
		DateofStay.sendKeys(date);
		Thread.sleep(800);
		HotelName.sendKeys(ExcelUtils.getStringCellValue(row, 14));
		Thread.sleep(800);
		HotelLocation.sendKeys(ExcelUtils.getStringCellValue(row, 15));
		Thread.sleep(800);
		YourFeedback.sendKeys(ExcelUtils.getStringCellValue(row, 16));
		Thread.sleep(3000);
		AutomationUtils.scrollToElment(driver, "//button[@class='submit btn-primary ']");
		Thread.sleep(1000);
		Submit.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(5000);
		if(Brand.equals("Ramada")){
			String ActualValueRamada=driver.findElement(By.xpath("//div[contains(@class,'generic-text')]/h3")).getText();
			if(!ActualValueRamada.contains("THANK YOU FOR SUBMITTING YOUR FEEDBACK!")){
				Assert.assertTrue(false);
			}
		}else{
		String Actual_value = driver.findElement(By.xpath("//div[contains(@class,'generic-text')]/p")).getText();
		if (!Actual_value.contains("Thank you for submiiting your feedback")) {
			Assert.assertTrue(false);
		} 
		}

	}

	public void BestRate_Forms(String category, int Contact_us_no) throws Exception {
		driver.navigate().back();
		Thread.sleep(1200);
		AutomationUtils.scrollToElment(driver, "//a[contains(text(),'Best Rate Claim')]");
		Thread.sleep(1200);
		BestRate_Click.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ContactUs_Form(category, Contact_us_no);
	}

	public void General_form(String category, int Contact_us_no) throws Exception {
		ContactUs_Form(category, Contact_us_no);
	}

	public void Coorporate_Forms(String category, int Contact_us_no) throws Exception {
		driver.navigate().back();
		Thread.sleep(1200);
		AutomationUtils.scrollToElment(driver, "//a[contains(text(),'Corporate Account Inquiry Form')]");
		Thread.sleep(1200);
		CoorporateRequest_Click.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ContactUs_Form(category, Contact_us_no);
	}

}
