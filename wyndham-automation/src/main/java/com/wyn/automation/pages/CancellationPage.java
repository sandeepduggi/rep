package com.wyn.automation.pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.LogStatus;
import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class CancellationPage extends BasePage<CancellationPage> {

	@FindBy(css = "div[class='cancel-button']>a")
	WebElement CancelReservation;

	@FindBy(css = "*[class*='cancel-reservation-confirmation']")
	WebElement CancelReserButton;

	public CancellationPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}

	public CancellationPage openPage() throws Exception {
		return super.openPage(CancellationPage.class);
	}

	public void cancelReservation() {
		
	
		Boolean isPresent = driver.findElements(By.cssSelector("div[class='cancel-button']>a")).size() > 0;
		if (isPresent) {
			 AutomationUtils.scrollToElment(driver, "//div[@class='cancel-button']/a");
			CancelReservation.click();
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CancelReserButton.click();
			Log.info("Cancel button clicked");
			AutomationUtils.Fluentwait(driver, "div[class='head5 title-cancelled']");
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Log.info("cancel screenshot captured page Url:" + driver.getCurrentUrl());
			
		} 

	}

}
