package com.wr.automation.rewards.pages;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class FreeHotelNightPage extends BasePage<FreeHotelNightPage>{
	public FreeHotelNightPage() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
		ExcelUtils.load("Enrollment");
	}

	public FreeHotelNightPage openPage() throws Exception {
		return super.openPage(FreeHotelNightPage.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}
      @FindBy(xpath="//a[@class='btn-secondary card-button open-login-modal']")
      WebElement clickSignin;
      
      @FindBy(xpath="//div[@id='genericLightbox']//input[@name='login-username']")
      WebElement userName;
      
      @FindBy(xpath="//div[@id='genericLightbox']//input[@name='login-password']")
      WebElement password;
      
      @FindBy(xpath="//div[@id='genericLightbox']//button[@class='btn-primary btn-block submit']")
      WebElement submitSignin;
      
      @FindBy(className = "question")
  	WebElement question;

  	@FindBy(id = "answer1")
  	WebElement answer1;
  	
  	@FindBy(xpath = "//*[@id='securityVerify']/button[2]")
	WebElement securitySubmit;
      
  	@FindBy(xpath = "//a[@class='btn-secondary card-button']")
	WebElement clickBookNow;
      
	public void clickSignIn() throws Exception {
		AutomationUtils.Fluentwait(driver, "//a[@class='btn-secondary card-button open-login-modal']", 10);
		Thread.sleep(2500);
		AutomationUtils.scrollToElment(driver, "//a[@class='btn-secondary card-button open-login-modal']");
		clickSignin.click();
	}

	public void enterLoginDetails(int testCaseNo) throws Exception {
		AutomationUtils.Fluentwait(driver, "//div[@id='genericLightbox']//button[@class='btn-primary btn-block submit']",10);
		Thread.sleep(4000);
		XSSFRow row = ExcelUtils.getRowData("Enrollment", testCaseNo);
		
		userName.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.userName)).getStringCellValue());
		password.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.password)).getStringCellValue());
		submitSignin.click();
	
	}
	
	public void validateSecurityQuestion() {
		 try {
			 AutomationUtils.waitVisibilityOf(driver, question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			validateSecurityQuestion();
		}
		
		
		if (question.getText().contains("mother")) {
			answer1.clear();
			answer1.sendKeys("mother");
		} else if (question.getText().contains("city or town")) {
			answer1.clear();
			answer1.sendKeys("city");
		} else if (question.getText().contains("born")) {
			answer1.clear();
			answer1.sendKeys("city");
		} else if (question.getText().contains("street")){
			answer1.clear();
			answer1.sendKeys("street");
		} else if (question.getText().contains("school")){
			answer1.clear();
			answer1.sendKeys("school");
		}
		AutomationUtils.scrollToElment(driver, "//*[@id='securityVerify']/button[2]");
		securitySubmit.click();

	}

	public void clickRegisterNow() throws Exception {
		Thread.sleep(7000);
		List<WebElement> elements=driver.findElements(By.xpath("//a[@class='btn-secondary card-button']"));
		elements.get(1).click();
		
	}

	public void clickBookNow() throws Exception {
		Thread.sleep(7000);
		Boolean bookNowIsPresent=driver.findElements(By.xpath("//a[@class='btn-secondary card-button']")).size()>0;
		clickBookNow.click();
		Thread.sleep(4500);
		
	}
}
