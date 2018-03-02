package com.wr.automation.rewards.pages;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class WrHowItWork extends BasePage<WrHowItWork>{
	private int number;
	public WrHowItWork() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham-rewards/how-it-works");
		
	}

	public WrHowItWork openPage() throws Exception {
		return super.openPage(WrHowItWork.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}
	public void validateComponents(){
		
		Boolean textOfferComponentIsPresent=driver.findElements(By.xpath("//div[contains(@class,'text-offer-card offer')]")).size()==3;
		Boolean invertColourComponentIsPresent=driver.findElements(By.xpath("//div[contains(@class,'invert-colors component-body')]")).size()==2;
		Boolean contentModuleIsPresent=driver.findElements(By.xpath("//div[contains(@class,'content-module-section-holder')]")).size()==4;
		if(!textOfferComponentIsPresent||!invertColourComponentIsPresent||!contentModuleIsPresent){
			Assert.assertTrue(false);
		}
	}
	
}
