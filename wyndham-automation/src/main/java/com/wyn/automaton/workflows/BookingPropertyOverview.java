package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.pages.CancellationPage;
import com.wyn.automation.pages.ConfirmationPage;
import com.wyn.automation.pages.PropertyOverviewPage;
import com.wyn.automation.pages.ReservationPage;
import com.wyn.automation.pages.RnRPage;
import com.wyn.automation.pages.SearchResultsPage;
import com.wyn.automation.pages.WyndhamHomePage;

public class BookingPropertyOverview extends BasePageTest{

	@Test
	public void BARbookingPropertyPage() throws Exception {
		test = extent.startTest("TC_15");
		String RnR_Special_Rate_Code="LV0";
		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber("TC_15");
		wyndhamhomepage.enterDestination("screen1",rowNumber);
		wyndhamhomepage.changeCheckIn("screen1",rowNumber);
		wyndhamhomepage.changeCheckOut("screen1",rowNumber);
		super.captureScreenShot();
		wyndhamhomepage.clickSearch();

		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		super.captureScreenShot();
		String sp1=searchresultspage.ClickProperty();

		PropertyOverviewPage overviewPage=new PropertyOverviewPage().openPage(PropertyOverviewPage.class);
		super.captureScreenShot();
		String sp2=overviewPage.ClickBookNow();

		RnRPage rnrpage = new RnRPage().openPage(RnRPage.class);
		super.captureScreenShot();
		rnrpage.clickBarPlusButton();

		ReservationPage reservationpage = new ReservationPage().openPage(ReservationPage.class);
		super.captureScreenShot();
		reservationpage.completeReservation();
		

		ConfirmationPage confirmationpage = new ConfirmationPage().openPage(ConfirmationPage.class);
		super.captureScreenShot();
		confirmationpage.verfyConfirmationPage();

		
		CancellationPage Cancel=new CancellationPage().openPage(CancellationPage.class);
		super.captureScreenShot();
		Cancel.cancelReservation();
	}
}
