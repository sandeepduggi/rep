package com.wyn.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ReportsManager {

	WebDriver driver;
	protected ExtentReports extent;
	protected ExtentTest test;

	public static String captureScreenShot(WebDriver driver, String name) throws Exception {

		Thread.sleep(1200);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "/screenshots/" + name + ".png";
		try {
			org.apache.commons.io.FileUtils.copyFile(source, new File(dest));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dest;
	}

	public void loadConfiguration() throws Exception {

		File currentDirectory = new File(new File(".").getAbsolutePath());
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Report.html", true);
		extent.addSystemInfo("Host Name", "Anshoo").addSystemInfo("Environment", "QA");
		extent.loadConfig(new File(currentDirectory.getCanonicalPath() + "/src/extent-config.xml"));

		
	}

	public void captureScreenShot() throws Exception {

		driver = Browsers.getBrowser();
		String imagePath = captureScreenShot(driver, this.getClass().getSimpleName());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String pageName = this.getClass().getSimpleName() + dateFormat.format(cal.getTime());
		test = new ExtentTest(pageName, pageName);
		test.log(LogStatus.INFO, pageName + test.addScreenCapture(imagePath));

	}
}
