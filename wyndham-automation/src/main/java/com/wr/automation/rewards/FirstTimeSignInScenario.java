package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.EnrollPage;
import com.wr.automation.rewards.pages.EnrollPageFinalStep;
import com.wr.automation.rewards.pages.EnrollPageThree;
import com.wr.automation.rewards.pages.EnrollPageTwo;
import com.wr.automation.rewards.pages.FirstTimeSigninPage;
import com.wyn.automation.base.BasePageTest;

public class FirstTimeSignInScenario extends BasePageTest{

	@Test
	public void firstTimeSignIn() throws Exception {
		int TestCaseNumber=5;
		test = extent.startTest("firstTimeSignIn");
		EnrollPage enrollPage = new EnrollPage().openPage(EnrollPage.class);
		super.captureScreenShot("1.Enter Details in EnrollmentPage");
		enrollPage.submitRecord(TestCaseNumber,"Enrollment");
		
		EnrollPageTwo enrollPageTwo = new EnrollPageTwo().openPage(EnrollPageTwo.class);
		super.captureScreenShot("2.Enter click FirstTime SignIn");
		String member=enrollPageTwo.getMembershipNumber();
		enrollPageTwo.clickSetupAccount();
		
		FirstTimeSigninPage firstTimeSigninPage=new FirstTimeSigninPage().openPage(FirstTimeSigninPage.class);
		super.captureScreenShot("3.Enter MemeberDetails");
		firstTimeSigninPage.memberDetails(member);
		
		enrollPageTwo.submitData(TestCaseNumber);
		EnrollPageThree enrollPageThree = new EnrollPageThree().openPage(EnrollPageThree.class);
		super.captureScreenShot("4.Enter secrity Questions");
		enrollPageThree.submit(TestCaseNumber);
		EnrollPageFinalStep enrollPageFinal = new EnrollPageFinalStep().openPage(EnrollPageFinalStep.class);
		super.captureScreenShot("5.Account Created");
		enrollPageFinal.submitData(TestCaseNumber);
		}
}
