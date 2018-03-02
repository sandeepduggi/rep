package com.wr.automation.rewards;

import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.EnrollPage;
import com.wr.automation.rewards.pages.EnrollPageFinalStep;
import com.wr.automation.rewards.pages.EnrollPageThree;
import com.wr.automation.rewards.pages.EnrollPageTwo;
import com.wr.automation.rewards.pages.FirstTimeSigninPage;
import com.wr.automation.rewards.pages.ForgetPassword;
import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.LogoutPage;
import com.wr.automation.rewards.pages.MyAccount;
import com.wr.automation.rewards.pages.MyProfile;
import com.wr.automation.rewards.pages.WrHomePage;
import com.wr.automation.rewards.pages.WrReservationPage;
import com.wr.automation.rewards.pages.WrRnRPage;
import com.wr.automation.rewards.pages.WrSearchResultsPage;
import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.pages.ReservationPage;
import com.wyn.automation.pages.RnRPage;
import com.wyn.automation.pages.WyndhamHomePage;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class MyaccountGoFreeGoFast extends BasePageTest {
	
	
	@Test
	public void bookingGoFree() throws Exception{
	test=extent.startTest("bookingGoFree");
	int testCaseNo=7;
	LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
	loginPage.loginWithCredentials(testCaseNo);
	loginPage.validateSecurityQuestion();
	MyAccount myAccount=new MyAccount().openPage(MyAccount.class);
	myAccount.clickMyAccount();
	myAccount.clickGoFreeBookNow();
	myAccount.enterDetailsinBooknow();
	
	WrSearchResultsPage wrSearchResultsPage=new WrSearchResultsPage().openPage(WrSearchResultsPage.class);
	super.captureScreenShot("2. Click first Availabule property in SearchResults page");
	wrSearchResultsPage.clickBook();
	
	WrRnRPage WrRnRPage=new WrRnRPage().openPage(WrRnRPage.class);
	super.captureScreenShot("3.Click Bar+Bonus button in RnR Page");
	WrRnRPage.clickBarPlusButton();

	WrReservationPage reservationpage = new WrReservationPage().openPage(WrReservationPage.class);
	super.captureScreenShot("4.Enter the information in Reservation page");
	reservationpage.goFreeCompleteReservation();
	}
	
	
}
