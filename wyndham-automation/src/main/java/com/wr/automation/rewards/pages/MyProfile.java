package com.wr.automation.rewards.pages;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;



public class MyProfile extends  BasePage<MyProfile> {

	
	@FindBy(id="emailAddress")
    WebElement emailId;
	
	@FindBy(xpath="//button[@class='add-address-btn add-fields-button btn-link']")
    WebElement addAddress;
	
	@FindBy(id="addressType1")
    WebElement addressType;
	//addressType1 addtype sel(Home)
	
	@FindBy(id="businessName0")
    WebElement companyName;
	
	@FindBy(id="address1-1")
    WebElement address;
	
	
	@FindBy(id="address2-1")
    WebElement address2;
	
	@FindBy(id="address3-1")
    WebElement address3;
	
	@FindBy(id="country1")
    WebElement country;
	
//	country1 cont selec(United States)
	
	@FindBy(id="city1")
    WebElement city;
	
	@FindBy(id="zipCode1")
    WebElement zipCode;
	
	@FindBy(id="state1")
    WebElement state;
	
//	state1 state select(Alabama)
	
	@FindBy(xpath="//button[@class='submit btn btn-primary btn-block']")
    WebElement saveButton;
	
	@FindBy(xpath="//a[@aria-controls='signinandsecurity']")
    WebElement signinAndSecurity;
	
	@FindBy(xpath="//a[@aria-controls='preferences']")
    WebElement preferences;
	
	@FindBy(xpath="//input[@id='earnPoints']/following-sibling::span")
    WebElement inputCheckboxPreference;
	
	@FindBy(xpath="//input[@id='earnAirfare']/following-sibling::span")
    WebElement inputCheckbox2Preference;
	
	@FindBy(id="hotelBrand")
    WebElement hotelBrands;
	
	
	@FindBy(id="currentPassword")
    WebElement currentPassword;
	
	@FindBy(id="password")
    WebElement password;
	
	@FindBy(id="confirmPassword")
    WebElement confirmPassword;
	
	@FindBy(xpath="//form[@id='securityUpdatePassword']/button[2]")
    WebElement passwordSaveButton;
	
	@FindBy(xpath="//div[@class='tab-pane active'][@id='preferences']//button[@class='submit btn btn-primary btn-block']")
    WebElement preferencesSaveButton;
	
	
	@FindBy(id="travelPartner")
    WebElement travelPartner;
	
	@FindBy(id="flyerNum")
    WebElement flyerNum;
	
	@FindBy(id="firstName")
    WebElement firstName;
	
	@FindBy(id="lastName")
    WebElement lastName;
	
	
	
	@FindBy(id="question0")
    WebElement question0;
	@FindBy(id="answer0")
    WebElement answer0;
	
	@FindBy(id="question1")
    WebElement question1;
	@FindBy(id="answer1")
    WebElement answer1;
	
	
	@FindBy(id="question2")
    WebElement question2;
	@FindBy(id="answer2")
    WebElement answer2;
	
	
	public MyProfile() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
		ExcelUtils.load("MyProfile");
	}

	public MyProfile openPage() throws Exception {
		return super.openPage(MyProfile.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}

	public void changeDetails() throws Exception {
		XSSFRow row = ExcelUtils.getRowData("MyProfile", 1);
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
	   AutomationUtils.scrollToElment(driver, "//button[@class='add-address-btn add-fields-button btn-link']"); 
	   Thread.sleep(3500);
	   addAddress.click();
	   
		addressType.sendKeys(ExcelUtils.getStringCellValue(row, 1));
		companyName.clear();
		companyName.sendKeys(ExcelUtils.getStringCellValue(row, 2));
		address.clear();
		address.sendKeys(ExcelUtils.getStringCellValue(row, 3));
		address2.clear();
		address2.sendKeys(ExcelUtils.getStringCellValue(row, 4));
		address3.clear();
		address3.sendKeys(ExcelUtils.getStringCellValue(row, 5));
		country.sendKeys(ExcelUtils.getStringCellValue(row, 6));
		city.clear();
		city.sendKeys(ExcelUtils.getStringCellValue(row, 7));
		zipCode.clear();
		zipCode.sendKeys(ExcelUtils.getNumericCellValue(row, 8));
		state.sendKeys(ExcelUtils.getStringCellValue(row, 9));
		emailId.clear();
		emailId.sendKeys(ExcelUtils.getStringCellValue(row, 0));
		AutomationUtils.scrollToElment(driver, "//button[@class='submit btn btn-primary btn-block']");
		saveButton.click();
		Thread.sleep(3800);
		Boolean isdiplayed=driver.findElements(By.xpath("//form[@id='profileUpdate']//button[@class='submit btn btn-primary btn-block'][@disabled='disabled']")).size()>0;
	if(!isdiplayed){
		Assert.assertTrue(false);
	}
	}

	public void changePassword() throws Exception {
		XSSFRow row = ExcelUtils.getRowData("MyProfile", 1);
		AutomationUtils.scrollToElment(driver, "//a[@aria-controls='signinandsecurity']");
		signinAndSecurity.click();
		try {
			AutomationUtils.Fluentwait(driver,"//div[@class='tab-pane active'][@id='signinandsecurity']", 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPassword.clear();
		String cp=ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.CurrentPassword));
		sendKeys(currentPassword, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.CurrentPassword)));
		
		password.clear();
		String pw= ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.NewPassword));
		sendKeys(password, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.NewPassword)));
		confirmPassword.clear();
		String cpw=ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.NewConfirmPassword));
		sendKeys(confirmPassword, ExcelUtils.getStringCellValue(row, ExcelUtils.getColumnNumber(Constants.NewConfirmPassword)));
		AutomationUtils.scrollToElment(driver, "//form[@id='securityUpdatePassword']//button[2]");
		passwordSaveButton.click();
		Thread.sleep(3800);
		Boolean isPresent=driver.findElements(By.xpath("//form[@id='securityUpdatePassword']//button[2][@disabled='disabled']")).size()>0;
	    if(!isPresent){
	    	Assert.assertTrue(false);
	    }
	}

	public void preferences() {
		AutomationUtils.scrollToElment(driver, "//a[@aria-controls='preferences']");
		preferences.click();
		try {
			AutomationUtils.Fluentwait(driver, "//div[@class='tab-pane active'][@id='preferences']", 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFRow row = ExcelUtils.getRowData("MyProfile", 1);
		inputCheckboxPreference.click();
		hotelBrands.sendKeys(ExcelUtils.getStringCellValue(row, 10));
		inputCheckbox2Preference.click();
		travelPartner.sendKeys(ExcelUtils.getStringCellValue(row, 11));
		flyerNum.clear();
		flyerNum.sendKeys(ExcelUtils.getStringCellValue(row, 12));
		firstName.clear();
		firstName.sendKeys(ExcelUtils.getStringCellValue(row, 13));
		lastName.clear();
		lastName.sendKeys(ExcelUtils.getStringCellValue(row, 14));
		AutomationUtils.scrollToElment(driver, "//div[@class='tab-pane active'][@id='preferences']//button[@class='submit btn btn-primary btn-block']");
		preferencesSaveButton.click();
	}

	public void changeSecQuestions() throws Exception {
	Boolean isPresent=driver.findElements(By.xpath("//div[@class='tab-pane active'][@id='signinandsecurity']//*[@id='question0']")).size()>0;
	System.out.println("question0:"+question0.getText()+"question1"+question1.getText()+"question2:"+question2.getText());
	if(isPresent){
		answer0.clear();
		answer0.sendKeys("answer0");
		answer1.clear();
		answer1.sendKeys("answer1");
		answer2.clear();
		answer2.sendKeys("answer2");
		AutomationUtils.scrollToElment(driver, "//form[@id='securityUpdateQuestion']/button[2]");
		Thread.sleep(1200);
		driver.findElement(By.xpath("//form[@id='securityUpdateQuestion']/button[2]")).click();
			Thread.sleep(3800);
			Boolean isPresent1=driver.findElements(By.xpath("//form[@id='securityUpdateQuestion']/button[2][@disabled='disabled']")).size()>0;
		    if(!isPresent1){
		    	Assert.assertTrue(false);
		    }
			
	}
		
	}

	public void validateMyProfile() {
		XSSFRow row = ExcelUtils.getRowData("MyProfile", 1);
		String addresst=ExcelUtils.getStringCellValue(row, 1);
//		(JTextField)addressType
		companyName.clear();
		companyName.sendKeys(ExcelUtils.getStringCellValue(row, 2));
		address.clear();
		address.sendKeys(ExcelUtils.getStringCellValue(row, 3));
		address2.clear();
		address2.sendKeys(ExcelUtils.getStringCellValue(row, 4));
		address3.clear();
		address3.sendKeys(ExcelUtils.getStringCellValue(row, 5));
		country.sendKeys(ExcelUtils.getStringCellValue(row, 6));
		city.clear();
		city.sendKeys(ExcelUtils.getStringCellValue(row, 7));
		zipCode.clear();
		zipCode.sendKeys(ExcelUtils.getNumericCellValue(row, 8));
		state.sendKeys(ExcelUtils.getStringCellValue(row, 9));
		emailId.clear();
		emailId.sendKeys(ExcelUtils.getStringCellValue(row, 0));
	}

	

	
}
