package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.LogoutPage;
import com.wr.automation.rewards.pages.MyProfile;
import com.wyn.automation.base.BasePageTest;

public class MyProfileScenario extends BasePageTest {
	@Test
	public void myProfile() throws Exception {
		    int testCaseNo=9;
		    test=extent.startTest("myProfile");
			LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
			super.captureScreenShot("1.Click SignIn");
			loginPage.loginWithCredentials(testCaseNo);
			super.captureScreenShot("2.Enter the Security Question");
			loginPage.validateSecurityQuestion();
			super.captureScreenShot("3.Click MyProfile");
			loginPage.clickMyProfile();
			MyProfile myProfile=new MyProfile().openPage(MyProfile.class);
			super.captureScreenShot("4.Enter adders details");
			myProfile.changeDetails();
			super.captureScreenShot("5.Change Password");
			myProfile.changePassword();
			super.captureScreenShot("6.Change security Question");
			myProfile.changeSecQuestions();
			super.captureScreenShot("7.Enter Preferences");
			myProfile.preferences();
			LogoutPage logoutPage = new LogoutPage().openPage(LogoutPage.class);
			super.captureScreenShot("8.Click LogOut After Login");
			logoutPage.logOut();
			
			LoginPage loginPage2 = new LoginPage().openPage(LoginPage.class);
			super.captureScreenShot("9.Click SignIn");
			loginPage2.myProfileNewLogin(testCaseNo);
			super.captureScreenShot("10.Enter the Security Question");
			loginPage2.myProfileValidateSecurityQuestion();
			super.captureScreenShot("11.Click MyProfile");
			loginPage2.clickMyProfile();
//			MyProfile myProfile2=new MyProfile().openPage(MyProfile.class);
//			myProfile2.validateMyProfile();
			
	}
}
