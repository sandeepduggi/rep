package com.wyn.automation.pages;

import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class LoginValidation extends BasePage<LoginValidation> {
	@FindBy(xpath="//a[@class='wr-login-links login-display']")
	static
	WebElement Signin;

	@FindBy(id="wrUsername")
	static
	WebElement UserName;

	@FindBy(id="wrPassword")
	static
	WebElement Password;

	@FindBy(xpath="//div[@class='login-btn-text']")
	static
	WebElement LoginBtn;

	@FindBy(id="securityQuestion")
	static
	WebElement SecurityQest;

	@FindBy(id="securityAnswer")
	static
	WebElement SecurityAns;

	@FindBy(id="submit-button")
	static
	WebElement SecuritySubmit;
	static WebDriver driver;
	
	public LoginValidation() {
		super(Browsers.getBrowser());
		 driver = Browsers.getBrowser();
		 XSSFRow  row = ExcelUtils.getRowData("Configurations", 1);
		currentPageUrl(ExcelUtils.getStringCellValue(row, 2));
		
	}
	public LoginValidation openPage() throws Exception {
		return super.openPage(LoginValidation.class);
	}
	public static String login(String Username,String password) throws Exception{
		Signin.click();
		Thread.sleep(2000);
		UserName.sendKeys(Username);
		Password.sendKeys(password);
		String sp=AutomationUtils.captureScreenShot(driver,"Login");
		LoginBtn.click();
		AutomationUtils.Fluentwait(driver,"//button[@id='submit-button']", 30);
		Thread.sleep(5000);
		System.out.println(SecurityQest.getText());
		//wrong answer for the question
		SecurityAns.sendKeys("city1");
		SecuritySubmit.click();
		Thread.sleep(7000);
		//right answer for the question
		
		if (SecurityQest.getText().contains("street")) {
//			SecurityAns.clear();
			SecurityAns.sendKeys("street");
		} else if (SecurityQest.getText().contains("city or town")) {
//			SecurityAns.clear();
			SecurityAns.sendKeys("city");
		} else if (SecurityQest.getText().contains("born")) {
//			SecurityAns.clear();
			SecurityAns.sendKeys("city");
		} else {
//			SecurityAns.clear();
			SecurityAns.sendKeys("school");
		}
		
		SecuritySubmit.click();
		Thread.sleep(7000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return sp;
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
		
	}
}
