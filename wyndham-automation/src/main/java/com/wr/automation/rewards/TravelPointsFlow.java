package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.MyAccount;
import com.wr.automation.rewards.pages.RedeemPage;
import com.wr.automation.rewards.pages.TravelPoints;
import com.wyn.automation.base.BasePageTest;

public class TravelPointsFlow extends BasePageTest{
	@Test
	public void travelPointsClaim() throws Exception{
		test=extent.startTest("travelPointsClaim");
		int testCaseNo=14;
		LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
		super.captureScreenShot("1.Enter LoginDeatils");
		loginPage.loginWithCredentials(testCaseNo);
		super.captureScreenShot("2.Enter SecurityQuestion");
		loginPage.validateSecurityQuestion();
		MyAccount myAccount=new MyAccount().openPage(MyAccount.class);
		super.captureScreenShot("3.Click MyRedeem");
		myAccount.clickRedeem();
		RedeemPage redeemPage=new RedeemPage().openPage(RedeemPage.class);
		super.captureScreenShot("4.Click PointsCatalog");
		redeemPage.clickPointCatalog();
		super.captureScreenShot("5.Click AirLineRedeem");
		redeemPage.clickAirLineRedeem();
		TravelPoints travelPoints=new TravelPoints().openPage(TravelPoints.class);
		super.captureScreenShot("6.Click American AirLine Redeem");
		travelPoints.clickAmericanAirlineRedeem();
		super.captureScreenShot("7.Enter Traveler details");
		travelPoints.enterDetails();
		super.captureScreenShot("8.Validate Diduction Points");
		travelPoints.validateTravelPoints();
	}

}
