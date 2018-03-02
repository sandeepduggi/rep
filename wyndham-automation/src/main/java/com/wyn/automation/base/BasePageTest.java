package com.wyn.automation.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.util.CellAddress;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;
import com.wyn.automation.utils.PropertiesUtil;

public abstract class BasePageTest {

	WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;

	public BasePageTest() {

	}

	@BeforeSuite
	public void beforeSuite() throws Exception {

	}

	@AfterSuite
	public void afterSuite() {

	}

	@BeforeMethod
	public void beforeMethod() {

//		DOMConfigurator.configure(PropertiesUtil.getPropertyValue(Constants.LOG4J_FILE_PATH));
//		AutomationUtils.loadExcelFile(PropertiesUtil.getPropertyValue(Constants.EXCEL_FILE_PATH));
//		extent = new ExtentReports(PropertiesUtil.getPropertyValue(Constants.DASHBOARD_PATH), false);

	}

	@AfterMethod
	protected void afterMethod(ITestResult result) throws Exception {

//		if (result.getStatus() == ITestResult.FAILURE) {
//			test.log(LogStatus.FAIL, result.getThrowable());
//
//		} else if (result.getStatus() == ITestResult.SKIP) {
//			test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
//		} else {
//			test.log(LogStatus.PASS, "Test passed");
//
//		}
//		captureScreenShot();
//		extent.endTest(test);
//		driver.manage().deleteAllCookies();
//		driver.quit();
//		Browsers.nullDriver();
	}

	@AfterTest
	public void endReport() throws Exception {
		extent.flush();
		driver.get(PropertiesUtil.getPropertyValue("domain"));
		
		
	} 


		public void captureScreenShot() throws Exception {

			driver = Browsers.getBrowser();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String pageName = this.getClass().getSimpleName()  + cal.getTimeInMillis();
			String imagePath = AutomationUtils.captureScreenShot(driver, pageName);
			test.log(LogStatus.INFO, pageName + test.addScreenCapture(imagePath));

		} 
		
		public void captureScreenShot(String message) throws Exception {

            driver = Browsers.getBrowser();
            String dateFormat = new SimpleDateFormat("dd MMM yyyy, HH.mm.ss").format(new Date());

            String pageName = this.getClass().getSimpleName()  + "_" + dateFormat;
            String imagePath = AutomationUtils.captureScreenShot(driver, pageName);
            test.log(LogStatus.INFO, pageName + test.addScreenCapture(imagePath) + " " + message);
           
            }

		public int getTestCaseRowNumber(String testCaseName) {

            AutomationUtils.loadExcelFile(PropertiesUtil.getPropertyValue(Constants.EXCEL_FILE_PATH));

            CellAddress address = ExcelUtils.getCellAddress(testCaseName);
            int rowNumber = address.getRow() + 1;
            return rowNumber;

     }

	 


}
