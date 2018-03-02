package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.pages.SearchResultsPage;
import com.wyn.automation.pages.WyndhamHomePage;

public class WrLoginAndLogout extends BasePageTest{
	
	@Test
	public void wrLoginAndLogout() throws Exception {
		test = extent.startTest(Constants.TC_05);
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_05);
		wyndhamHomePage.loginPerformOperation("BrandsBooking1",rowNumber);
		wyndhamHomePage.enterDestination("BrandsBooking1",rowNumber);
		wyndhamHomePage.changeCheckIn("BrandsBooking1",rowNumber);
		wyndhamHomePage.changeCheckOut("BrandsBooking1",rowNumber);
		wyndhamHomePage.clickResultsinPoints();
		super.captureScreenShot("1.Login with valid credentails and click Search button in HomePage");
		wyndhamHomePage.clickSearch();
		
		SearchResultsPage searchresultspage = new SearchResultsPage().openPage(SearchResultsPage.class);
		searchresultspage.validateDefaultState();
		super.captureScreenShot("2.Login out after verifying the rewards points in Search Results Page");
		searchresultspage.logOut();
		
	}

}
