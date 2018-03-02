package com.wyn.automaton.workflows;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.pages.CancellationPage;
import com.wyn.automation.pages.ConfirmationPage;
import com.wyn.automation.pages.DealsPage;
import com.wyn.automation.pages.ReservationPage;
import com.wyn.automation.pages.RnRPage;
import com.wyn.automation.pages.SearchResultsPage;
import com.wyn.automation.pages.WyndhamHomePage;
import com.wyn.automation.utils.ExcelUtils;

public class DealsLanding extends BasePageTest {
	@Test
	public void bookingFlowDealLanding() throws Exception {
		test = extent.startTest("bookingFlowDealLanding");
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_10);

		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		super.captureScreenShot("1.clik deals page");
		 wyndhamhomepage.Deallanding();
		
		DealsPage dealspage=new DealsPage().openPage(DealsPage.class);
		dealspage.DealsClick();
		dealspage.DealsGlobalbookingbarSearch(rowNumber);
		
		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		super.captureScreenShot("2. Click first Availabule property in SearchResults page");
		searchresultspage.clickBook();
	
		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
	    rnrpage.clickRateButton(DealsPage.code);
 	
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
