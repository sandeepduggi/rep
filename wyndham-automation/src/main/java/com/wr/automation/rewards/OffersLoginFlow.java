package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.FreeHotelNightPage;
import com.wr.automation.rewards.pages.HotelDealsPage;
import com.wr.automation.rewards.pages.WrHomePage;
import com.wr.automation.rewards.pages.WrReservationPage;
import com.wr.automation.rewards.pages.WrRnRPage;
import com.wr.automation.rewards.pages.WrSearchResultsPage;
import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.utils.ExcelUtils;

public class OffersLoginFlow extends BasePageTest{
	@Test
	public void offersAndPromotionalFlow() throws Exception{
		test = extent.startTest("offersAndPromotionalFlow");
		 int TestCaseNumber=15;
		HotelDealsPage hotelDealsPage=new HotelDealsPage().openPage(HotelDealsPage.class);
		hotelDealsPage.clickStayTwiceTextOffer();
		FreeHotelNightPage freeHotelNightPage=new FreeHotelNightPage().openPage(FreeHotelNightPage.class);
		freeHotelNightPage.clickSignIn();
		freeHotelNightPage.enterLoginDetails(TestCaseNumber);
		freeHotelNightPage.validateSecurityQuestion();
		freeHotelNightPage.clickRegisterNow();
		freeHotelNightPage.clickBookNow();
		int rowNumber = ExcelUtils.getRowNumber("wrBooking","TC_03");
		WrHomePage wrHomePage=new WrHomePage().openPage(WrHomePage.class);
		wrHomePage.enterDestination("WrBooking",rowNumber);
		super.captureScreenShot("1.Click search button in homepage");
		wrHomePage.clickSearch();
		WrSearchResultsPage wrSearchResultsPage=new WrSearchResultsPage().openPage(WrSearchResultsPage.class);
		super.captureScreenShot("2. Click first Availabule property in SearchResults page");
		wrSearchResultsPage.clickBook();
		WrRnRPage WrRnRPage=new WrRnRPage().openPage(WrRnRPage.class);
		
		super.captureScreenShot("3.Click Bar+Bonus button in RnR Page");
		WrRnRPage.clickBarPlusButton();

		WrReservationPage reservationpage = new WrReservationPage().openPage(WrReservationPage.class);
		super.captureScreenShot("4.Enter the information in Reservation page");
		reservationpage.completeReservation();
	}
}
