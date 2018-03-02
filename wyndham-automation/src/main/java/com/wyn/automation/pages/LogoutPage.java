package com.wyn.automation.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.Browsers;

public class LogoutPage extends BasePage<LogoutPage> {
	private static Logger Log = Logger.getLogger(LogoutPage.class.getName());

	public LogoutPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("");
	}

	WebElement signedIn;

	@FindBy(className = "sign-out-button")
	WebElement signOutButtons;

	public LogoutPage openPage() {
		return super.openPage(LogoutPage.class);

	}

	public void logOut() {
		Log.info("SignedIn Text" + signedIn.getText());
		signedIn.click();
		Log.info("SignedOut Text" + signOutButtons.getText());

		signOutButtons.click();

	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageLoad();
		signedIn = driver.findElement(By.className("signed-in-container"));

	}
}
