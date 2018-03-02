package com.wr.automation.rewards.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;



public class MyReservation extends  BasePage<MyReservation> {

	public MyReservation() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public MyReservation openPage() throws Exception {
		return super.openPage(MyReservation.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}
	@FindBy(xpath="//div[@class='hidden-xs']//li[3]/a")
    WebElement clickMyReservationButton;
	
	@FindBy(id = "timeFilter")
	WebElement myReservationFilter;
	public void clickMyReservation(){
		AutomationUtils.waitVisibilityOf(driver, clickMyReservationButton);
		AutomationUtils.scrollToElment(driver, "//div[@class='hidden-xs']//li[3]/a");
		clickMyReservationButton.click();
	}

	public void checkViewDetails() {
		Boolean upcomingResListIsPresent=driver.findElements(By.xpath("//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[1]")).size()>0;
		Boolean pastResListIsPresent=driver.findElements(By.xpath("//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[1]")).size()>0;
		if(upcomingResListIsPresent){
			AutomationUtils.waitVisibilityOf(driver,"//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[1]");
			AutomationUtils.scrollToElment(driver,"//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[1]");
			driver.findElement(By.xpath("//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[1]")).click();
		}
		else if(pastResListIsPresent)
		{
			AutomationUtils.waitVisibilityOf(driver,"//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[1]");
			AutomationUtils.scrollToElment(driver, "//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[1]");
			driver.findElement(By.xpath("//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[1]")).click();
		}
		AutomationUtils.waitVisibilityOf(driver, "//*[@class='confirmation-title']");
		String Url=driver.getCurrentUrl();
		if(!Url.contains("reservation/view")){
			Assert.assertTrue(false);
		}
		
	}
	public void checkCinfirmationNumber(String confirmatioNumberExpected){
		AutomationUtils.waitVisibilityOf(driver, "//div[@id='upcoming-res-listings']//span[@class='confirm-number']");
		String confrimationNumberActual=null;
		List<WebElement> elements=driver.findElements(By.xpath("//div[@id='upcoming-res-listings']//span[@class='confirm-number']"));
		boolean pass=false;
		for (int i = 0; i < elements.size(); i++) {
			confrimationNumberActual=elements.get(i).getText();
			if(confrimationNumberActual.equals(confirmatioNumberExpected)){
				pass=true;
				break;
			}
		}
		if(!pass){
			Assert.assertTrue(false);
		}
		
	}

	public void clickBookNowAndCheckRnRPage() throws Exception {
		driver.navigate().back();
		Boolean upcomingResBookNowIsPresent=driver.findElements(By.xpath("//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[2]")).size()>0;
		Boolean pastResBookNowIsPresent=driver.findElements(By.xpath("//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[2]")).size()>0;
		if(upcomingResBookNowIsPresent){
			AutomationUtils.waitVisibilityOf(driver,"//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[2]");
			AutomationUtils.scrollToElment(driver,"//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[2]");
			driver.findElement(By.xpath("//div[@id='upcoming-res-listings']/div[1]//div[@class='button-link-container']/a[2]")).click();
		}
		else if(pastResBookNowIsPresent)
		{
			AutomationUtils.waitVisibilityOf(driver,"//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[2]");
			AutomationUtils.scrollToElment(driver, "//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[2]");
			driver.findElement(By.xpath("//div[@id='past-res-listings']/div[1]//div[@class='button-link-container']/a[2]")).click();
		}
		AutomationUtils.Fluentwait(driver, "//div[contains(@class,'listing by-room')]",30);
		String Url=driver.getCurrentUrl();
		if(!Url.contains("rooms-rates")){
			Assert.assertTrue(false);
		}
		
	}

	public void clickPropertyAndCheckPropertyPage() throws Exception {
		driver.navigate().back();
		Boolean upcomingResBookNowIsPresent=driver.findElements(By.xpath("//div[@id='upcoming-res-listings']/div[1]//a[@class='property-name']")).size()>0;
		Boolean pastResBookNowIsPresent=driver.findElements(By.xpath("//div[@id='past-res-listings']/div[1]//a[@class='property-name']")).size()>0;
		if(upcomingResBookNowIsPresent){
			AutomationUtils.waitVisibilityOf(driver,"//div[@id='upcoming-res-listings']/div[1]//a[@class='property-name']");
			AutomationUtils.scrollToElment(driver,"//div[@id='upcoming-res-listings']/div[1]//a[@class='property-name']");
			driver.findElement(By.xpath("//div[@id='upcoming-res-listings']/div[1]//a[@class='property-name']")).click();
		}
		else if(pastResBookNowIsPresent)
		{
			AutomationUtils.waitVisibilityOf(driver,"//div[@id='past-res-listings']/div[1]//a[@class='property-name']");
			AutomationUtils.scrollToElment(driver, "//div[@id='past-res-listings']/div[1]//a[@class='property-name']");
			driver.findElement(By.xpath("//div[@id='past-res-listings']/div[1]//a[@class='property-name']")).click();
		}
		AutomationUtils.Fluentwait(driver, "//*[@class='nav nav-pills nav-stacked fixed left-nav__ul bordered-box']",30);
		String Url=driver.getCurrentUrl();
		if(!Url.contains("overview")){
			Assert.assertTrue(false);
		}
	}

	public void searchFilter() {
		driver.navigate().back();
		selectDropDownText(myReservationFilter,"Show last 30 days");
		AutomationUtils.waitVisibilityOf(driver, "//div[@id='past-res-listings']/div[1]//a[@class='property-name']");
		selectDropDownText(myReservationFilter,"Show last 18 months");
		AutomationUtils.waitVisibilityOf(driver, "//div[@id='past-res-listings']/div[1]//a[@class='property-name']");
	}
}
