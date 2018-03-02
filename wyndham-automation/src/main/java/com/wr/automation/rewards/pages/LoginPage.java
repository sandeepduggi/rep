package com.wr.automation.rewards.pages;



import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;



public class LoginPage extends BasePage<LoginPage> {

	public LoginPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham-rewards/login");
	}

	@FindBy(name = "login-username")
	WebElement loginUsername;

	@FindBy(name = "login-password")
	WebElement loginPassword;

	@FindBy(id = "answer1")
	WebElement answer1;

	@FindBy(className = "submit")
	WebElement submit;

	@FindBy(xpath = "//*[@id='securityVerify']/button[2]")
	WebElement securitySubmit;

	@FindBy(className = "question")
	WebElement question;

	@FindBy(className = "data-binding")
	WebElement isLoggedIn;

	@FindBy(className = "sign-in")
	WebElement signIn;

	WebElement signedIn;

	@FindBy(className = "sign-out-button")
	WebElement signOutButtons;
	

	@FindBy(xpath="//div[contains(@class,'signed-in-container recognized-container signed-in')]/i")
    WebElement  clickAccount;
	
	@FindBy(xpath="//div[@data-dropdown='account']//a[contains(@href,'profile')]")
    WebElement  clickMyProfile;
	
	@FindBy(xpath="//a[@class='login-forgot-password']")
    WebElement  clickForgetPassword;
	
	@FindBy(xpath = "//div[contains(@class,'centered-container-content mobile-fluid')]//input[@name='login-username']")
	WebElement physicalLoginUsername;

	@FindBy(xpath = "//div[contains(@class,'centered-container-content mobile-fluid')]//input[@name='login-password']")
	WebElement physicalLoginPassword;
	
	@FindBy(xpath = "//div[contains(@class,'centered-container-content mobile-fluid')]//*[@class='btn-primary btn-block submit']")
	WebElement physicalLoginSubmit;
	
	public LoginPage openPage() {
		return super.openPage(LoginPage.class);

	}

	public void loginWithCredentials(int testCaseNo) throws Exception {
		ExcelUtils.load("Enrollment");
		XSSFRow row = ExcelUtils.getRowData("Enrollment", testCaseNo);
		signIn.click();
		loginUsername.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.userName)).getStringCellValue());
		loginPassword.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.password)).getStringCellValue());
		submit.click();

	}
	public void myProfileNewLogin(int testCaseNo) throws Exception {
		ExcelUtils.load("Enrollment");
		XSSFRow row = ExcelUtils.getRowData("Enrollment", testCaseNo);
		signIn.click();
		loginUsername.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.userName)).getStringCellValue());
		ExcelUtils.load("MyProfile");
		XSSFRow row1 = ExcelUtils.getRowData("MyProfile", 1);
		String password=row1.getCell(16).getStringCellValue();
		System.out.println("changed password:"+password);
		loginPassword.sendKeys(password);
		submit.click();

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
	public void myProfileValidateSecurityQuestion() {
		 
		 try {
			 AutomationUtils.waitVisibilityOf(driver, question);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			myProfileValidateSecurityQuestion();
		}
		
		  if (question.getText().contains("born")) {
			answer1.clear();
			answer1.sendKeys("answer0");
		} else if (question.getText().contains("street")){
			answer1.clear();
			answer1.sendKeys("answer2");
		} else if (question.getText().contains("school")){
			answer1.clear();
			answer1.sendKeys("answer1");
		}
		AutomationUtils.scrollToElment(driver, "//*[@id='securityVerify']/button[2]");
		securitySubmit.click();

	}
 public void waitForHomePage() throws Exception{
//	 AutomationUtils.waitVisibilityOf(driver, "//input[@name='destination']");
     Thread.sleep(11000);
 }
	public void loginWithWrongCredentials(String userName, String passWord) {
		loginUsername.sendKeys(userName);
		loginPassword.sendKeys(passWord);
		submit.click();

	}

	public void signOut() {
		String page = driver.getCurrentUrl();
		signedIn.click();
		signOutButtons.click();

	}

	public void clear() {
		loginUsername.clear();
		loginPassword.clear();

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	public WebElement getWhenVisible(By locator, int timeout) {

		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}
	
public void clickMyProfile() {
	try {
		Thread.sleep(12000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		try {
			AutomationUtils.Fluentwait(driver, "//div[contains(@class,'signed-in-container recognized-container signed-in')]/i",10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickAccount.click();
		clickMyProfile.click();
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageLoad();
		signedIn = driver.findElement(By.className("signed-in-container"));

	}

	public void forgetPassword() {
		signIn.click();
		clickForgetPassword.click();
		
		
	}

	public void loginWithCredentialsPhysically(int testCaseNo) throws Exception {
		XSSFRow row = ExcelUtils.getRowData("Enrollment", testCaseNo);
		AutomationUtils.Fluentwait(driver, "//div[contains(@class,'centered-container-content mobile-fluid')]//input[@name='login-username']", 15);
		physicalLoginUsername.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.userName)).getStringCellValue());
		physicalLoginPassword.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.password)).getStringCellValue());
		physicalLoginSubmit.click();
	}
	public void newLogin(int testCaseNo) throws Exception {
		XSSFRow row = ExcelUtils.getRowData("Enrollment", testCaseNo);
		AutomationUtils.Fluentwait(driver, "//div[contains(@class,'centered-container-content mobile-fluid')]//input[@name='login-username']", 15);
		Thread.sleep(2000);
		physicalLoginUsername.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.userName)).getStringCellValue());
		physicalLoginPassword.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.NewPassword2)).getStringCellValue());
		physicalLoginSubmit.click();
	}

}

