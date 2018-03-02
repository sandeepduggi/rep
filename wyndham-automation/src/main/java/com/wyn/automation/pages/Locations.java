package com.wyn.automation.pages;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;



public class Locations extends BasePage<Locations>{
	 
	static WebDriver driver;
	public Locations() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}
	
	public Locations openPage() throws Exception {
		return super.openPage(Locations.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
		
	}
	
	public void ourLocations() throws Exception{
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		Thread.sleep(5000);
		AutomationUtils.scrollToElment(driver,"//div[contains(@class,'states-section')][@data-country='United States']/a[2]");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class,'states-section')][@data-country='United States']/a[2]")).click();
		Thread.sleep(7000);
		Boolean IsPresent=driver.findElements(By.xpath("//div[contains(@class,'states-section')][@data-country='United States']//a[@aria-expanded='true']")).size()>0;
		if(!IsPresent){
			Assert.assertTrue(false);
		}
		
		
	}
}
