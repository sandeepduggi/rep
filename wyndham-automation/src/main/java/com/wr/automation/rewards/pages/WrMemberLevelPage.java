package com.wr.automation.rewards.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;

public class WrMemberLevelPage extends BasePage<WrMemberLevelPage>{
	public WrMemberLevelPage() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham-rewards/member-levels");
		
	}

	public WrMemberLevelPage openPage() throws Exception {
		return super.openPage(WrMemberLevelPage.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}

	public void validateComponents() throws Exception {
		AutomationUtils.scrollToElment(driver, "//*[contains(@id,'perklisting')]");
		List<WebElement> perkList=driver.findElements(By.xpath("//*[contains(@id,'perklisting')]/td/button"));
		for (int i = 0; i < perkList.size(); i++) {
			if(i!=13){
			AutomationUtils.scrollToElment(driver, "//*[contains(@id,'perklisting"+i+"')]/td/button");
			Thread.sleep(1200);
			perkList.get(i).click();
			}
			
		}
		Boolean perklistingIsPresent=driver.findElements(By.xpath("//*[contains(@id,'perklisting')]//button[contains(@class,'collapsed')]")).size()==1;
		if(!perklistingIsPresent){
			Assert.assertTrue(false);
		}
	}
}
