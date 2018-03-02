package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.pages.ReservationPage;
import com.wyn.automation.pages.RnRPage;
import com.wyn.automation.pages.SearchResultsPage;
import com.wyn.automation.pages.WyndhamHomePage;

public class ErrorValidationGlobalBookingBar extends BasePageTest{

	@Test
	public void ErrorvalidationGlobalbookingBookingformSearchresults() throws Exception {
		test = extent.startTest("ErrorvalidationGlobalbookingBookingformSearchresults");
	
		String RnRSpecialRateCode="RROD";
		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		super.captureScreenShot("1.validating globalbooking bar in homepage");
		wyndhamhomepage.errorvalidation();
		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		super.captureScreenShot("2.validating globalbooking bar and search reultspage");
		searchresultspage.SearchResultErrorvalidation();
		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
		rnrpage.clickRateButton(RnRSpecialRateCode);
	
		ReservationPage reservationpage = new ReservationPage().openPage(ReservationPage.class);
		super.captureScreenShot("3.validating mandatory fileds in ReservationPage");
		reservationpage.Bookingformvalidation();
	}
}
