package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.EnrollPage;
import com.wr.automation.rewards.pages.EnrollPageFinalStep;
import com.wr.automation.rewards.pages.EnrollPageThree;
import com.wr.automation.rewards.pages.EnrollPageTwo;
import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.Browsers;

public class LongEnrollmentScenario extends BasePageTest {

	@Test
	public void enrollmentScreenOne() throws Exception {
		    test = extent.startTest("LongEnrollmentScenario");

			EnrollPage enrollPage = new EnrollPage().openPage(EnrollPage.class);
			int TestCaseNo=1;
			super.captureScreenShot("1.Enter Details in EnrollmentPage");
			enrollPage.submitRecord(TestCaseNo,"Enrollment");
			
			EnrollPageTwo enrollPageTwo = new EnrollPageTwo().openPage(EnrollPageTwo.class);
			super.captureScreenShot("2.Enter Username And Password");
			enrollPageTwo.submitData(TestCaseNo);
			
			EnrollPageThree enrollPageThree = new EnrollPageThree().openPage(EnrollPageThree.class);
			super.captureScreenShot("3.Enter Security Question");
			enrollPageThree.submit(TestCaseNo);
			
			EnrollPageFinalStep enrollPageFinal = new EnrollPageFinalStep().openPage(EnrollPageFinalStep.class);
			super.captureScreenShot("4.Enter Address");
			enrollPageFinal.submitData(TestCaseNo);
			super.captureScreenShot();
			
	}
}
