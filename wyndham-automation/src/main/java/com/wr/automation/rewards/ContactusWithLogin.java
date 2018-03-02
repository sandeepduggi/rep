package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.ContactUsPage;
import com.wr.automation.rewards.pages.LoginPage;
import com.wyn.automation.base.BasePageTest;

public class ContactusWithLogin extends BasePageTest {
	
	@Test
	public void contactUsFormWithLogin() throws Exception{
		test=extent.startTest("contactUsFormWithLogin");
		int testCaseNo=6;
		LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
		super.captureScreenShot("1.Click SignIn");
		loginPage.loginWithCredentials(testCaseNo);
		loginPage.validateSecurityQuestion();
		ContactUsPage contactUsPage=new ContactUsPage().openPage(ContactUsPage.class);
		super.captureScreenShot("2.Click ContactUs Form");
		contactUsPage.clickContactUs();
		contactUsPage.clickGetStarted();
		super.captureScreenShot("3.Enter form Details");
		contactUsPage.enterDetails(1);
		super.captureScreenShot("4.Click Submit Button");
		contactUsPage.clickSubmitButton();
		contactUsPage.validateContactUs();
	}
}
