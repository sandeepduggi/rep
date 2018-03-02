package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.LogoutPage;
import com.wyn.automation.base.BasePageTest;
import com.wr.automation.rewards.pages.ForgetPassword;
public class ForgetPasswordScenario extends BasePageTest{

	@Test
	public void forgetPassword() throws Exception {
		 test = extent.startTest("forgetPassword");
		 int TestCaseNumber=4;
			LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
			super.captureScreenShot("1.Enter forgetPassword");
			loginPage.forgetPassword();
			ForgetPassword forgetPassword =new ForgetPassword().openPage(ForgetPassword.class);
			super.captureScreenShot("2.Enter account Details");
			forgetPassword.findYourAccount(TestCaseNumber);
			super.captureScreenShot("3.Enter Security Question");
			forgetPassword.answerSecQuestion(TestCaseNumber);
			super.captureScreenShot("4.Change Password");
			forgetPassword.changePassword(TestCaseNumber);
			LogoutPage logoutPage = new LogoutPage().openPage(LogoutPage.class);
			super.captureScreenShot("5.Click LogOut After Login");
			logoutPage.logOut();
			LoginPage loginPage2 = new LoginPage().openPage(LoginPage.class);
			super.captureScreenShot("6.Login Physically");
			loginPage2.newLogin(TestCaseNumber);
			loginPage2.validateSecurityQuestion();	
	
	}
	
}
