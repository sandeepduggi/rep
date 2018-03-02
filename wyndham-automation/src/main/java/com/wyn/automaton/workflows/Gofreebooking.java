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

public class Gofreebooking extends BasePageTest  {
	
	@Test
	public void goFreeBookingFlow() throws Exception {
		
		test = extent.startTest(Constants.TC_06);
		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_06);
		wyndhamhomepage.enterDestination("BrandsBooking1",rowNumber);
		wyndhamhomepage.changeCheckIn("BrandsBooking1",rowNumber);
		wyndhamhomepage.changeCheckOut("BrandsBooking1",rowNumber);
		super.captureScreenShot("2. Click first Availabule property in SearchResults page");
		wyndhamhomepage.clickSearch();

		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		searchresultspage.clickWyndhamRewardsFilter();
		super.captureScreenShot("2.Click the first available rewards button in SearchReults Page");
		searchresultspage.rateClick();

		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
		super.captureScreenShot();
		rnrpage.ClickgoFree_goFast("gofree");
		super.captureScreenShot("3.Click GoFree button in RnR page");
        rnrpage.LoginEntry(rowNumber);

		ReservationPage reservationpage = new ReservationPage().openPage(ReservationPage.class);
		super.captureScreenShot("4.Enter the Reservation details in Reservation Page");
		reservationpage.SubmitgoFree();
		
		ConfirmationPage confirmationpage = new ConfirmationPage().openPage(ConfirmationPage.class);
	    super.captureScreenShot("5.Complete the booking in Confirmation page");
	    confirmationpage.verfyConfirmationPage();

		
		CancellationPage Cancel=new CancellationPage().openPage(CancellationPage.class);
		super.captureScreenShot("6.Cancel the booking in Cancellation page");
		Cancel.cancelReservation();


	}
}
