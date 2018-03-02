package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.LogoutPage;
import com.wr.automation.rewards.pages.MyAccount;
import com.wr.automation.rewards.pages.MyReservation;
import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.utils.Browsers;

public class MyReservationScenario extends BasePageTest {
	
	@Test
	public void MyReservationCheckPages() throws Exception{
	test=extent.startTest("MyReservationCheckPages");
	int testCaseNo=10;
	LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
	loginPage.loginWithCredentials(testCaseNo);
	super.captureScreenShot("1.Enter LoginDeatils");
	loginPage.validateSecurityQuestion();
	MyAccount myAccount=new MyAccount().openPage(MyAccount.class);
	super.captureScreenShot("2.Click MyAccount Link");
	myAccount.clickMyAccount();
	MyReservation myReservation=new MyReservation().openPage(MyReservation.class);
	super.captureScreenShot("3.Click MyReservation Link");
	myReservation.clickMyReservation();
	myReservation.checkViewDetails();
	super.captureScreenShot("4.Click viewDetails than check the confirmation page");
	myReservation.clickBookNowAndCheckRnRPage();
	super.captureScreenShot("5.Click Booknow than check the RnR page");
	myReservation.clickPropertyAndCheckPropertyPage();
	super.captureScreenShot("6.Click Property than check the Property page");
	myReservation.searchFilter();
	LogoutPage logoutPage = new LogoutPage().openPage(LogoutPage.class);
	logoutPage.logOut();
	}

}
