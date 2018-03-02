package com.wr.automation.rewards.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;

public class WrConfirmationPage extends BasePage<WrConfirmationPage> {

	public WrConfirmationPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public WrConfirmationPage openPage() throws Exception {
		return super.openPage(WrConfirmationPage.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}
	@FindBy(xpath = "//button[@class='close x-button']")
	WebElement clickBanner;
	
	public String verfyConfirmationPage() throws Exception{
		Boolean bannerIsPresent=driver.findElements(By.xpath("//button[@class='close x-button']")).size()>0;
		if(bannerIsPresent){
			Thread.sleep(3500);
			clickBanner.click();
		}
		String confrimationNumber=null;
		Log.info("Entered to confirmation page page Url:"+driver.getCurrentUrl());
		AutomationUtils.Fluentwait(driver, "//p[@class='confirmation-number']/span",30);
		confrimationNumber=driver.findElement(By.xpath("//p[@class='confirmation-number']/span")).getText();
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	
     	Log.info("Confirmation screenshot captured");
		return confrimationNumber;
	}

}
