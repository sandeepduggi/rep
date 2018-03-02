package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wyn.automation.base.BasePageTest;
import com.wr.automation.rewards.pages.MyAccount;
public class MyAccountScenario extends BasePageTest{
	
	@Test
	public void MyAccount() throws Exception {
		    int testCaseNo=8;
		    test=extent.startTest("MyAccount");
			LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
			super.captureScreenShot("1.Click SignIn");
			loginPage.loginWithCredentials(testCaseNo);
			super.captureScreenShot("2.Enter the Security Question");
			loginPage.validateSecurityQuestion();
			MyAccount myAccount=new MyAccount().openPage(MyAccount.class);
			super.captureScreenShot("3.Click MyAccount");
			myAccount.clickMyAccount();
			super.captureScreenShot("4.Verify MyAccount Components");
			myAccount.verifyComponentPresnets();
	}
}
