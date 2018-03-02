package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.pages.CancellationPage;
import com.wyn.automation.pages.ConfirmationPage;
import com.wyn.automation.pages.FindReservation;
import com.wyn.automation.pages.ReservationPage;
import com.wyn.automation.pages.RnRPage;
import com.wyn.automation.pages.SearchResultsPage;
import com.wyn.automation.pages.WyndhamHomePage;

public class RetrieveReservation  extends BasePageTest{
	@Test
	public void retrieveReservation() throws Exception {
		test = extent.startTest("RetrieveReservation");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_02);
		wyndhamHomePage.enterDestination("BrandsBooking1",rowNumber);
		wyndhamHomePage.changeCheckIn("BrandsBooking1",rowNumber);
		wyndhamHomePage.changeCheckOut("BrandsBooking1",rowNumber);
		super.captureScreenShot("2. Click first Availabule property in SearchResults page");
		wyndhamHomePage.clickSearch();
		
		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		super.captureScreenShot("2.Click first available button in Search Resultspage");
		searchresultspage.clickBook();
	
		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
		super.captureScreenShot("3.Click Bar+Bonus button in RnR page");
		rnrpage.clickBarPlusButton();

		ReservationPage reservationpage = new ReservationPage().openPage(ReservationPage.class);
		super.captureScreenShot("4.Enter the reservation details in Reservation page");
		reservationpage.completeReservation();
		
		ConfirmationPage confirmationpage = new ConfirmationPage().openPage(ConfirmationPage.class);
		super.captureScreenShot("5.Complete the booking");
		String confirmationNumber=confirmationpage.verfyConfirmationPage();
		
		FindReservation FindReservation=new FindReservation().openPage(FindReservation.class);
		super.captureScreenShot("6.Booking Cancelled in find Reservation page");
		FindReservation.findReservation(confirmationNumber);
		super.captureScreenShot();
		
		CancellationPage Cancel = new CancellationPage().openPage(CancellationPage.class);
		super.captureScreenShot("7.Cancelled the reservation in Cancellation page");
		Cancel.cancelReservation();
		
	
		
	}

}
