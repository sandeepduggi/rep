package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.MyAccount;
import com.wr.automation.rewards.pages.MyReservation;
import com.wr.automation.rewards.pages.WrConfirmationPage;
import com.wr.automation.rewards.pages.WrHomePage;
import com.wr.automation.rewards.pages.WrReservationPage;
import com.wr.automation.rewards.pages.WrRnRPage;
import com.wr.automation.rewards.pages.WrSearchResultsPage;
import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.utils.ExcelUtils;

public class VerifyMyReservation extends BasePageTest{
	@Test
	public void verifyMyReservation() throws Exception{
	test=extent.startTest("verifyMyReservation");
	int testCaseNo=12;
	LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
	loginPage.loginWithCredentials(testCaseNo);
	super.captureScreenShot("1.Enter LoginDeatils");
	loginPage.validateSecurityQuestion();
	loginPage.waitForHomePage();
	WrHomePage wrHomePage=new WrHomePage().openPage(WrHomePage.class);
	int rowNumber = ExcelUtils.getRowNumber("wrBooking","TC_01");
	wrHomePage.enterDestination("WrBooking",rowNumber);
	wrHomePage.changeCheckIn("WrBooking",rowNumber);
	wrHomePage.changeCheckOut("WrBooking",rowNumber);
	super.captureScreenShot("2.Click search button in homepage");
	wrHomePage.clickSearch();
	WrSearchResultsPage wrSearchResultsPage=new WrSearchResultsPage().openPage(WrSearchResultsPage.class);
	super.captureScreenShot("3. Click first Availabule property in SearchResults page");
	wrSearchResultsPage.clickBook();
	WrRnRPage WrRnRPage=new WrRnRPage().openPage(WrRnRPage.class);
	
	super.captureScreenShot("4.Click Bar+Bonus button in RnR Page");
	WrRnRPage.clickBarPlusButton();

	WrReservationPage reservationpage = new WrReservationPage().openPage(WrReservationPage.class);
	super.captureScreenShot("5.Enter the information in Reservation page");
	reservationpage.goFreeCompleteReservation();
	
	WrConfirmationPage wrConfirmationPage=new WrConfirmationPage().openPage(WrConfirmationPage.class);
	super.captureScreenShot("6.Get confirmation Number in ConfirmationPage");
	String ConfirmationNumber=wrConfirmationPage.verfyConfirmationPage();
//	String ConfirmationNumber="5136B0192567";
	MyAccount myAccount=new MyAccount().openPage(MyAccount.class);
	super.captureScreenShot("7.Click MyAccount Link");
	myAccount.refreshPage();
	myAccount.clickMyAccount();
	MyReservation myReservation=new MyReservation().openPage(MyReservation.class);
	super.captureScreenShot("8.Click MyReservation Link");
	myReservation.clickMyReservation();
	myReservation.checkCinfirmationNumber(ConfirmationNumber);
	
	}
	

}
