package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.EnrollPage;
import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.MyAccount;
import com.wyn.automation.base.BasePageTest;

public class VerifyQuickLinksScenario extends BasePageTest{
	
	@Test
	public void verifyQuickLinks() throws Exception{
		test=extent.startTest("verifyQuickLinks");
		int testCaseNo=13;
		LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
		super.captureScreenShot("1.Click SignIn");
		loginPage.loginWithCredentials(testCaseNo);
		super.captureScreenShot("2.Enter Security Question");
		loginPage.validateSecurityQuestion();
		
		MyAccount myAccount=new MyAccount().openPage(MyAccount.class);
		super.captureScreenShot("3.Click MyAccount");
		myAccount.clickMyAccount();
		super.captureScreenShot("4.VerifyQuick Links");
		
		myAccount.verifyQuickNav();
	}
}
