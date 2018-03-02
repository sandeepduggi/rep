package com.wr.automation.rewards.pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class ForgetPassword extends BasePage<ForgetPassword> {

	
	
	public ForgetPassword() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
		ExcelUtils.load("Enrollment");
	}

	public ForgetPassword openPage() throws Exception {
		return super.openPage(ForgetPassword.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}
	
	@FindBy(id="lastName")
    WebElement lastName;
	
	@FindBy(id = "email")
	WebElement emailAddress;
	
	
	@FindBy(id = "zipCode")
	WebElement zipCode0;
	

	@FindBy(xpath = "//button[@class='submit btn btn-secondary btn-block']")
	WebElement clickNext;
	

	@FindBy(xpath = "//button[@class='btn btn-block btn-secondary verify-answer']")
	WebElement clickAnswerSecQuestion;
	
	
	@FindBy(xpath = "//p[@data-binding='SecurityInfo.SecurityQuestions.0.question']")
	WebElement secQuestion0;
	
	@FindBy(xpath = "//p[@data-binding='SecurityInfo.SecurityQuestions.1.question']")
	WebElement secQuestion1;
	
	@FindBy(id = "answer1")
	WebElement answer1;
	
	@FindBy(id = "answer2")
	WebElement answer2;
	
	@FindBy(xpath = "//button[@class='submit btn btn-secondary btn-block']")
	WebElement clickSecNextButton;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;
	
	@FindBy(xpath = "//button[@class='submit btn btn-secondary btn-block']")
	WebElement clickPasswordChangeNextButton;
	
	public void findYourAccount(int TestCaseNumber) throws Exception {
		// TODO Auto-generated method stub
		try {
			AutomationUtils.Fluentwait(driver, "//div[@class='app-step-container container']", 15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelUtils.load("Enrollment");
		XSSFRow row = ExcelUtils.getRowData("Enrollment", TestCaseNumber);
		lastName.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.lastName)).getStringCellValue());
		sendKeys(zipCode0, ExcelUtils.getNumericCellValue(row, ExcelUtils.getColumnNumber(Constants.zipCode)));
		emailAddress.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.email)).getStringCellValue());
		clickNext.click();
		
		try {
			AutomationUtils.Fluentwait(driver,"//button[@class='btn btn-block btn-secondary verify-answer']", 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickAnswerSecQuestion.click();
	}



	public void answerSecQuestion(int TestCaseNo) {
		try {
			AutomationUtils.Fluentwait(driver,"//p[@data-binding='SecurityInfo.SecurityQuestions.0.question']", 15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFRow row = ExcelUtils.getRowData("Enrollment", TestCaseNo);
		String question0=row.getCell(ExcelUtils.getColumnNumber(Constants.q1)).getStringCellValue();
		if(question0.equals(secQuestion0.getText()))
		answer1.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a1)).getStringCellValue());
		
		String question1=row.getCell(ExcelUtils.getColumnNumber(Constants.q2)).getStringCellValue();
		if(question1.equals(secQuestion0.getText()))
		answer1.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a2)).getStringCellValue());
		
		String question2=row.getCell(ExcelUtils.getColumnNumber(Constants.q3)).getStringCellValue();
		if(question2.equals(secQuestion0.getText()))
		answer1.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a3)).getStringCellValue());
		
		if(question0.equals(secQuestion1.getText()))
		answer2.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a1)).getStringCellValue());
			
			
	    if(question1.equals(secQuestion1.getText()))
	    answer2.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a2)).getStringCellValue());
			
	    if(question2.equals(secQuestion1.getText()))
	    answer2.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a3)).getStringCellValue());
			
	    clickSecNextButton.click();
	    
	}

	public void changePassword(int TestCaseNo) {
		try {
			AutomationUtils.Fluentwait(driver, "//input[@id='forgetPassword']", 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFRow row = ExcelUtils.getRowData("Enrollment", TestCaseNo);
		password.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.NewPassword2)).getStringCellValue());
		confirmPassword.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.NewPassword2)).getStringCellValue());
		clickPasswordChangeNextButton.click();
	}
}
