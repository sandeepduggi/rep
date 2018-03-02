package com.wr.automation.rewards.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class HotelDealsPage extends BasePage<HotelDealsPage> {
	public HotelDealsPage() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham-rewards/hotel-deals");
		ExcelUtils.load("Enrollment");
	}

	public HotelDealsPage openPage() throws Exception {
		return super.openPage(HotelDealsPage.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}
	@FindBy(xpath="//div[@class='textoffers base parbase'][2]//a[@class='btn-secondary card-button']")
    WebElement clickAutoClubBookNow;
	
	@FindBy(xpath="//div[@class='textoffers base parbase'][1]//a[@class='btn-secondary card-button']")
    WebElement clickstayTwiceLearnMoreButton;
	
	public void clickAutoClub() throws Exception {
		
		AutomationUtils.scrollToElment(driver, "//div[@class='textoffers base parbase'][2]//a[@class='btn-secondary card-button']");
		Thread.sleep(1200);
		clickAutoClubBookNow.click();
	}

	public void clickStayTwiceTextOffer() throws Exception {
		
		AutomationUtils.scrollToElment(driver, "//div[@class='textoffers base parbase'][1]//a[@class='btn-secondary card-button']");
		Thread.sleep(1200);
		clickstayTwiceLearnMoreButton.click();
	}
	
}
