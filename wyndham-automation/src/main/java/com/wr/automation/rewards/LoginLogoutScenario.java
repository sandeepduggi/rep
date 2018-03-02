package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.LogoutPage;
import com.wyn.automation.base.BasePageTest;

public class LoginLogoutScenario extends BasePageTest{


	@Test
	public void login() throws Exception {
		 test = extent.startTest("LoginLogoutScenario");
		    int testCaseNo=2;
			LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
			super.captureScreenShot("1.Enter LoginDetails");
			loginPage.loginWithCredentials(testCaseNo);
			loginPage.validateSecurityQuestion();	
			super.captureScreenShot("2.Enter secQuestion");
			LogoutPage logoutPage = new LogoutPage().openPage(LogoutPage.class);
			super.captureScreenShot("3.Click LogOut After Login");
			logoutPage.logOut();
			LoginPage loginPage2 = new LoginPage().openPage(LoginPage.class);
			super.captureScreenShot("4.Login Physically");
			loginPage2.loginWithCredentialsPhysically(testCaseNo);
			loginPage2.validateSecurityQuestion();	
			super.captureScreenShot("5.Enter secQuestion");
			LogoutPage logoutPage2 = new LogoutPage().openPage(LogoutPage.class);
			super.captureScreenShot("6.Click LogOut After Login");
			logoutPage2.logOut();
		}
	
	
}
