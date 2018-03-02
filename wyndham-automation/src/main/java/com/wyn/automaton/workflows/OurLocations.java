package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.pages.Locations;
import com.wyn.automation.pages.WyndhamHomePage;

public class OurLocations extends BasePageTest{
	@Test
	public void ourLocations() throws Exception {
		test = extent.startTest("ourLocations");
		
		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		super.captureScreenShot("1.Click ourLocations link");
		wyndhamhomepage.OurLocations();
		Locations locations = new Locations().openPage(Locations.class);
		super.captureScreenShot("2.Validate OurLocations Page");
		locations.ourLocations();
		
	}

}
