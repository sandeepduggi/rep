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

public class SpecialRatesbooking extends BasePageTest {
	
	@Test
	public void bookingFlow_AAA() throws Exception {
		test = extent.startTest(Constants.TC_08);
		String RnR_Special_Rate_Code="S3A";
		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_08);
		wyndhamhomepage.enterDestination("BrandsBooking1",rowNumber);
		super.captureScreenShot("1.Enter the details and click the search button in Home Page");
		wyndhamhomepage.specialRateClick("BrandsBooking1",rowNumber);

		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		super.captureScreenShot("2.Click first available button in Search Results page");
		searchresultspage.clickBook();
		

		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
		super.captureScreenShot("3.Click Special rates button in RnR Page"); 
		rnrpage.clickRateButton(RnR_Special_Rate_Code);
		

		ReservationPage reservationpage = new ReservationPage().openPage(ReservationPage.class);
		super.captureScreenShot("4.Enter the reservation details in Reservation Page");
		reservationpage.completeReservation();
		

		ConfirmationPage confirmationpage = new ConfirmationPage().openPage(ConfirmationPage.class);
		super.captureScreenShot("5.Complete the reservation ");
		confirmationpage.verfyConfirmationPage();

		
		CancellationPage Cancel=new CancellationPage().openPage(CancellationPage.class);
		super.captureScreenShot("6.Cancel the reservation in Cancellation Page");
		Cancel.cancelReservation();

	}
}
