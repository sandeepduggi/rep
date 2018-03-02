package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.pages.WyndhamHomePage;

public class LoginFunctionality extends BasePageTest{

	@Test(enabled=true)
	public void loginFunctionality() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_18);
		super.captureScreenShot("1.Login with valid credentails in HomePage");
		wyndhamHomePage.clickLogin();
		wyndhamHomePage.logintoAccount(rowNumber);
		wyndhamHomePage.wrongSecAnswer();
		wyndhamHomePage.correctSecAnswer();
		wyndhamHomePage.validateLogin();
	}
	@Test(enabled=false)
	public void loginValidWithouSecQuestion() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_19);
		super.captureScreenShot("1.Login with valid credentails without security question in HomePage");
		wyndhamHomePage.clickLogin();
		wyndhamHomePage.logintoAccount(rowNumber);
		wyndhamHomePage.selectSameQusTwice(rowNumber);
		wyndhamHomePage.enterThreeUniqueAnswers(rowNumber);
		wyndhamHomePage.reviewSecQue();
		wyndhamHomePage.submitAnsers();
		wyndhamHomePage.validateLogin();
	}
	@Test(enabled=false)
	public void loginRandomCredentails() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_20);
		wyndhamHomePage.clickLogin();
		super.captureScreenShot("1.Login with Random Credentails and validate the Login Functionality");
		wyndhamHomePage.randomCredentails(rowNumber);
	}
    
	@Test(enabled=false)
	public void loginSuspendedCredentails() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_21);
		wyndhamHomePage.clickLogin();
		super.captureScreenShot("1.Login with suspended Credentails and validate the Login Functionality");
		wyndhamHomePage.suspendedCredentails(rowNumber);
	}
	@Test(enabled=false)
	public void lockingAccountWithPasswordfield() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_22);
		wyndhamHomePage.clickLogin();
		super.captureScreenShot("1.Locking the Account with password Fields");
		wyndhamHomePage.enterWrongAnswerFiveTimes(rowNumber);
		wyndhamHomePage.enterLockedCredentails();
	}
	
	@Test(enabled=false)
	public void lockingAccountWithSecQuestion() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_23);
		wyndhamHomePage.clickLogin();
		super.captureScreenShot("1.Locking the account with security question");
		wyndhamHomePage.logintoAccount(rowNumber);
		wyndhamHomePage.wrongSecAnswerFiveTimes();
		wyndhamHomePage.enterLockedCredentails();
	}
	
	@Test(enabled=false)
	public void loginValidCredentailsWithoutDeletingCache() throws Exception {
		test = extent.startTest("LoginTest");
		loginFunctionality();
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_24);
		super.captureScreenShot("1.Login with valid credentails without deleting the Cache");
		wyndhamHomePage.enterSameValidCredentails(rowNumber);
		wyndhamHomePage.validateLogin();
		
	}
	
	@Test(enabled=false)
	public void loginValidCredentailsWithDeletingCache() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_25);
		wyndhamHomePage.clickLogin();
		wyndhamHomePage.logintoAccount(rowNumber);
		wyndhamHomePage.wrongSecAnswer();
		wyndhamHomePage.correctSecAnswer();
		super.captureScreenShot("1.Login with valid credentails with deleting the Cache");
		wyndhamHomePage.enterSameValidCredentailsWithDeletingCache(rowNumber);
		
	}
	
	@Test(enabled=false)
	public void loginValidCredentailsLeavingDevice60minIdle() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_26);
		wyndhamHomePage.clickLogin();
		wyndhamHomePage.logintoAccount(rowNumber);
		wyndhamHomePage.wrongSecAnswer();
		wyndhamHomePage.correctSecAnswer();
		super.captureScreenShot("1.Login with valid credentails and leaving the Device 15 Min Idle");
		wyndhamHomePage.leavingsixtyMinIdle();
	}
	@Test(enabled=false)
	public void loginValidCredentailsPerformOperation() throws Exception {
		test = extent.startTest("LoginTest");
		WyndhamHomePage wyndhamHomePage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		int rowNumber = super.getTestCaseRowNumber(Constants.TC_27);
		wyndhamHomePage.clickLogin();
		wyndhamHomePage.logintoAccount(rowNumber);
		super.captureScreenShot("1.Login with valid credentails and leaving the Device Idle and performing operation");
		wyndhamHomePage.wrongSecAnswer();
		wyndhamHomePage.correctSecAnswer();
		wyndhamHomePage.leavingFiveMinIdle();
		wyndhamHomePage.clickSearch();
		wyndhamHomePage.checkUserLoggedOutAfterTenMin();
	}
	
}
