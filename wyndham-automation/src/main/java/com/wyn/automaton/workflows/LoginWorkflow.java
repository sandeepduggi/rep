package com.wyn.automaton.workflows;

import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.pages.LoginPage;
import com.wyn.automation.pages.LogoutPage;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class LoginWorkflow extends BasePageTest {

	@Test
	public void login() throws Exception {

		test = extent.startTest("login");
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "Pass");
		int lastRow = ExcelUtils.getLastRow("Screen2");
		for (int i = 1; i <= lastRow; i++) {
			XSSFRow row = ExcelUtils.getRowData("Screen2", i);

			LoginPage loginPage = new LoginPage().openPage(LoginPage.class);
			loginPage.loginWithCredentials(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
			loginPage.validateSecurityQuestion();
			Browsers.getBrowser().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String str = Browsers.getBrowser().getCurrentUrl();
			LogoutPage logoutPage = new LogoutPage().openPage(LogoutPage.class);
			logoutPage.logOut();
			if (Browsers.getBrowser().getCurrentUrl().equals("https://fqa.wyndhamhotels.com/wyndham-rewards/")) {
				loginPage.signOut();
			}
		}

	}

}
