package com.wyn.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.Browsers;


public class LoginPage extends BasePage<LoginPage> {

	public LoginPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
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

	public LoginPage openPage() {
		return super.openPage(LoginPage.class);

	}

	public void loginWithCredentials(String userName, String passWord) {
		signIn.click();
		loginUsername.sendKeys(userName);
		loginPassword.sendKeys(passWord);
		submit.click();

	}

	public void validateSecurityQuestion() {

		String que = question.getText();

		if (question.getText().contains("street")) {
			answer1.clear();
			answer1.sendKeys("street");
		} else if (question.getText().contains("city or town")) {
			answer1.clear();
			answer1.sendKeys("city");
		} else if (question.getText().contains("born")) {
			answer1.clear();
			answer1.sendKeys("city");
		} else {
			answer1.clear();
			answer1.sendKeys("school");
		}
		securitySubmit.click();

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

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageLoad();
		signedIn = driver.findElement(By.className("signed-in-container"));

	}
}
