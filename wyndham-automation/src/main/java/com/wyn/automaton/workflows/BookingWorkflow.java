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

public class BookingWorkflow extends BasePageTest {

	public static String error = null;
	public static String errorDetails = "";
	public static int nohotels = 0;
	public static int flow = 0;

	@Test
	public void bookingFlowTest() throws Exception {
	
		System.out.println("pass");
//		test = extent.startTest("WyndhamBookingFlow");
//		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
//		int rowNumber = super.getTestCaseRowNumber(Constants.TC_01);
//		wyndhamHomePage.enterDestination("BrandsBooking1",rowNumber);
//		wyndhamHomePage.changeCheckIn("BrandsBooking1",rowNumber);
//		wyndhamHomePage.changeCheckOut("BrandsBooking1",rowNumber);
//		super.captureScreenShot("1.Click search button in homepage");
//		wyndhamHomePage.clickSearch();
//		
//		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
//		super.captureScreenShot("2. Click first Availabule property in SearchResults page");
//		searchresultspage.clickBook();
//	
//		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
//		super.captureScreenShot("3.Click Bar+Bonus button in RnR Page");
//		rnrpage.clickBarPlusButton();
//
//		ReservationPage reservationpage = new ReservationPage().openPage(ReservationPage.class);
//		super.captureScreenShot("4.Enter the information in Reservation page");
//		reservationpage.completeReservation();
//		
//		ConfirmationPage confirmationpage = new ConfirmationPage().openPage(ConfirmationPage.class);
//		super.captureScreenShot("5.Completed the reservation in Confirmation page");
//		confirmationpage.verfyConfirmationPage();
//		
//		CancellationPage Cancel = new CancellationPage().openPage(CancellationPage.class);
//		super.captureScreenShot("6.Cancelled the reservation in Cancellation page");
//		Cancel.cancelReservation();
	

	}

}
