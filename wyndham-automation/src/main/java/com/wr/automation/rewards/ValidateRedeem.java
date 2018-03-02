package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.LoginPage;
import com.wr.automation.rewards.pages.MyAccount;
import com.wr.automation.rewards.pages.RedeemPage;
import com.wyn.automation.base.BasePageTest;

public class ValidateRedeem extends BasePageTest{
	@Test
	public void validatingRedeemPage() throws Exception{
		test=extent.startTest("validatingRedeemPage");
		int testCaseNo=11;
		LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
		super.captureScreenShot("1.Enter LoginDeatils");
		loginPage.loginWithCredentials(testCaseNo);
		super.captureScreenShot("2.Enter SecurityQuestion");
		loginPage.validateSecurityQuestion();
		MyAccount myAccount=new MyAccount().openPage(MyAccount.class);
		super.captureScreenShot("3.Click MyRedeem");
		myAccount.clickRedeem();
		RedeemPage redeemPage=new RedeemPage().openPage(RedeemPage.class);
		super.captureScreenShot("4.click Destination");
		redeemPage.changeDestination();
		super.captureScreenShot("5.Validateing Destination Filter");
		redeemPage.validateSelectedDestination();
		super.captureScreenShot("6.Validateing SearchBy Activity");
		redeemPage.selectSearchByActivity();
		redeemPage.validateSelectedActivity();
		super.captureScreenShot("7.Validating FilterBy");
		redeemPage.selectFilterBy();
		redeemPage.validateFilterBy();
		redeemPage.selectCombinationFilters();
		redeemPage.validatingselectCombinationFilters();
		
		redeemPage.resetFilter();
		super.captureScreenShot("9.Validating ResetFilter");
		redeemPage.vaildateResetFilter();
		super.captureScreenShot("10.Click Booking Options");
		redeemPage.clickBookingOptions();
		super.captureScreenShot("11.Validating Booking Options Tab");
		redeemPage.validateBookingOptionsTab();
		super.captureScreenShot("12.Click Pointcatalog");
		redeemPage.clickPointCatalog();
		super.captureScreenShot("13.Validating PointCatalog");
		redeemPage.validatePointCatalogTab();
		super.captureScreenShot("14.Click Auctions tab");
		redeemPage.clickAuctions();
		super.captureScreenShot("15.Validating Auctions tab");
		redeemPage.validateAuctionsPage();
		
	}
}
