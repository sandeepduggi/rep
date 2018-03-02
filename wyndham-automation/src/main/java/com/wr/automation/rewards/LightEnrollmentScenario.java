package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.EnrollPage;
import com.wyn.automation.base.BasePageTest;

public class LightEnrollmentScenario extends BasePageTest {


	@Test
	public void lightEnrollment() throws Exception {
		 test = extent.startTest("lightEnrollment");
			EnrollPage enrollPage = new EnrollPage().openPage(EnrollPage.class);
			super.captureScreenShot("1.Enter Details in EnrollmentPage");
			enrollPage.submitRecord(3,"Enrollment");
	}
}
