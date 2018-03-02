package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.pages.CancellationPage;
import com.wyn.automation.pages.ConfirmationPage;
import com.wyn.automation.pages.ReservationPage;
import com.wyn.automation.pages.RnRPage;
import com.wyn.automation.pages.SearchResultsPage;
import com.wyn.automation.pages.WyndhamHomePage;

public class Gofastbooking extends BasePageTest{
	@Test
	public void goFaskBookingFlow() throws Exception {
		test = extent.startTest(Constants.TC_07);
		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_07);
		wyndhamhomepage.enterDestination("BrandsBooking1",rowNumber);
		wyndhamhomepage.changeCheckIn("BrandsBooking1",rowNumber);
		wyndhamhomepage.changeCheckOut("BrandsBooking1",rowNumber);
		super.captureScreenShot("2. Click first Availabule property in SearchResults page");
		wyndhamhomepage.clickSearch();

		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		searchresultspage.clickWyndhamRewardsFilter();
		super.captureScreenShot("2.Click First avilable Rewards button in SearchResults page");
		searchresultspage.rateClick();

		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
		rnrpage.ClickgoFree_goFast("gofast");
		super.captureScreenShot("3.Click GoFast in RnR page");
	    rnrpage.LoginEntry(rowNumber);

		ReservationPage reservationpage = new ReservationPage().openPage(ReservationPage.class);
		super.captureScreenShot("4.Enter the Reservation details in Reservation Page");
		reservationpage.SubmitgoFast();
	    
		ConfirmationPage confirmationpage = new ConfirmationPage().openPage(ConfirmationPage.class);
	    super.captureScreenShot("5.Completed the reservation in Confrimation page");
	    confirmationpage.verfyConfirmationPage();

		
		CancellationPage Cancel=new CancellationPage().openPage(CancellationPage.class);
		super.captureScreenShot("6.Cancelled the booking in Cancellation page");
		Cancel.cancelReservation();
		
	}
}
