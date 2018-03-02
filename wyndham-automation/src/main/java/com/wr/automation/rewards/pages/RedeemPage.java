package com.wr.automation.rewards.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;



public class RedeemPage extends  BasePage<RedeemPage>{
	public RedeemPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public RedeemPage openPage() throws Exception {
		return super.openPage(RedeemPage.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}

	@FindBy(xpath="//button[contains(@class,'select-menubutton dropdown-toggle destination-select')]")
    WebElement clickSearchByDestination;
	
	@FindBy(xpath="//li[@class='select-menuitem'][@data-value='NY']")
    WebElement selectDestinationNY;
	
	@FindBy(xpath="//div[@class='fieldset-elements-container']//i[@class='wyn-icon-adventure']")
    WebElement selectActivityIcon;
	
	@FindBy(xpath="//div[@class='fieldset-elements-container']/div[@class='checkbutton']")
    WebElement selectFilterByHome;
	
	@FindBy(xpath="//button[@class='btn-link reset-button']")
    WebElement clickResetFilter;
	
	@FindBy(xpath="//ul[@class='navigation-tabs']//a[contains(@href,'booking-options')]")
    WebElement clickBookingOptions;
	
	@FindBy(xpath="//ul[@class='navigation-tabs']//a[contains(@href,'points-catalog')]")
    WebElement clickPointsCatalog;
	
	@FindBy(xpath="//div[@class='card-content']/a")
    WebElement clickPointsCatalogRedeem;
	
	@FindBy(xpath="//ul[@class='navigation-tabs']//a[contains(@href,'auctions')]")
    WebElement clickAuctions;
	
	@FindBy(xpath="//*[@class='checkbutton-group']//div[@class='fieldset-elements-container']/div[2]")
    WebElement clickHomesFilter;
	
	@FindBy(xpath="//div[@class='text-offer-card offer-2']/div/a")
    WebElement clickAirLineRedeem;
	
	@FindBy(xpath="//button[@class='close']")
    WebElement clickClose;
	int resultsTotal=0;
	public void changeDestination() throws Exception {
	    String results=driver.findElement(By.xpath("//span[@class='results-total']")).getText();
	    resultsTotal=Integer.parseInt(results);
		AutomationUtils.waitVisibilityOf(driver, clickSearchByDestination);
		Thread.sleep(1200);
		clickSearchByDestination.click();
		Thread.sleep(1200);
		selectDestinationNY.click();
		
	}

	public void validateSelectedDestination() throws Exception {
		AutomationUtils.Fluentwait(driver, "//div[@class='tile-row prop-location']", 10);
		String title="";
		List<WebElement> elements=driver.findElements(By.xpath("//div[@class='tile-row prop-location']"));
		for (int i = 0; i < elements.size(); i++) {
			title=elements.get(i).getText();
			if(!title.contains("NY")){
				Assert.assertTrue(false);
			}
			
		}
	}

	public void selectSearchByActivity() throws Exception {
		Thread.sleep(1200);
		AutomationUtils.scrollToElment(driver, "//div[@class='fieldset-elements-container']//i[@class='wyn-icon-adventure']");
		selectActivityIcon.click();
	}

	public void validateSelectedActivity() throws Exception {
		AutomationUtils.Fluentwait(driver, "//div[@class='mosaic-tile-layout clearfix tile-results']//i[@class='wyn-icon-adventure']", 10);
		Thread.sleep(2000);
		int actualValue=driver.findElements(By.xpath("//div[@class='mosaic-tile-layout clearfix tile-results']/*")).size();
		int ExpectedValue=driver.findElements(By.xpath("//i[@class='wyn-icon-adventure']")).size()-1;
		if(ExpectedValue!=actualValue){
			Assert.assertTrue(false);
		}
	}

	public void selectFilterBy() throws Exception {
		selectSearchByActivity();
		AutomationUtils.scrollToElment(driver, "//div[@class='fieldset-elements-container']/div[@class='checkbutton']");
		selectFilterByHome.click();
		
	}
	public void selectCombinationFilters() throws Exception{
		selectSearchByActivity();
	}

	public void validateFilterBy() throws Exception {
		AutomationUtils.Fluentwait(driver, "//div[@class='mosaic-tile-layout clearfix tile-results']//span[@class='tile-tag']", 10);
		Thread.sleep(2000);
		String titleTag=driver.findElement(By.xpath("//span[@class='tile-tag']")).getText();
		int actualValue=driver.findElements(By.xpath("//div[@class='mosaic-tile-layout clearfix tile-results']/*")).size();
		int ExpectedValue=driver.findElements(By.xpath("//span[@class='tile-tag']")).size();
		if(ExpectedValue!=actualValue&&!titleTag.equals("Hotels")){
			Assert.assertTrue(false);
		}
		
	}

	public void resetFilter() {
		AutomationUtils.scrollToElment(driver, "//button[@class='btn-link reset-button']");
		clickResetFilter.click();
		
	}

	public void vaildateResetFilter() throws Exception {
		Thread.sleep(1200);
		String results=driver.findElement(By.xpath("//span[@class='results-total']")).getText();
	    int expectedTotalResult=Integer.parseInt(results);
		WebElement element=driver.findElement(By.xpath("//div[contains(@class,'select-menu-dropdown')]/input[@name='destination']"));
		if(!element.getAttribute("value").contains("")&&expectedTotalResult!=resultsTotal){
			Assert.assertTrue(false);
		}
	}

	public void clickBookingOptions() throws Exception {
		AutomationUtils.Fluentwait(driver, "//ul[@class='navigation-tabs']//a[contains(@href,'booking-options')]", 10);
		AutomationUtils.scrollToElment(driver, "//ul[@class='navigation-tabs']//a[contains(@href,'booking-options')]");
		Thread.sleep(1200);
		clickBookingOptions.click();
	}

	public void validateBookingOptionsTab() throws Exception {
		AutomationUtils.Fluentwait(driver,"//div[@class='contentmodule']//a[@class='btn-secondary content-btn']", 15);
		AutomationUtils.scrollToElment(driver,"//div[@class='contentmodule']//a[@class='btn-secondary content-btn']");
		driver.findElement(By.xpath("//div[@class='contentmodule']//a[@class='btn-secondary content-btn']")).click();
		Thread.sleep(2000);

		Boolean bookingModelIsPresent=driver.findElements(By.xpath("//div[@class='modal-dialog booknow-dialog upabove modal-small']/div")).size()>0;
	    if(!bookingModelIsPresent){
	    	Assert.assertTrue(false);
	    }
	    clickClose.click();
	}

	public void clickPointCatalog() throws Exception {
		AutomationUtils.Fluentwait(driver, "//ul[@class='navigation-tabs']//a[contains(@href,'points-catalog')]", 10);
		AutomationUtils.scrollToElment(driver, "//ul[@class='navigation-tabs']//a[contains(@href,'points-catalog')]");
		Thread.sleep(1200);
		clickPointsCatalog.click();
		
	}

	public void validatePointCatalogTab() throws Exception {
		AutomationUtils.Fluentwait(driver, "//div[@class='card-content']/a", 10);
		AutomationUtils.scrollToElment(driver, "//div[@class='card-content']/a");
		clickPointsCatalogRedeem.click();
		Thread.sleep(12000);
		if(!driver.getCurrentUrl().contains("https://storefront.ecomm.maritzstage.com")){
			Assert.assertTrue(false);
		}
		driver.navigate().back();
	}

	public void clickAuctions() throws Exception {
		AutomationUtils.Fluentwait(driver, "//ul[@class='navigation-tabs']//a[contains(@href,'auctions')]", 10);
		AutomationUtils.scrollToElment(driver, "//ul[@class='navigation-tabs']//a[contains(@href,'auctions')]");
		Thread.sleep(1200);
		clickAuctions.click();
		
	}

	public void validateAuctionsPage() {
	List<WebElement> elements=driver.findElements(By.xpath("//a[@class='btn-secondary content-btn']"));
	String value="";
	
	for (int i = 0; i < elements.size(); i++) {
		
		value=value+"#"+elements.get(i).getAttribute("href");
	}
	String split[] = value.split("#");
	for (int i = 1; i < split.length; i++) {
		if(!split[i].contains("entertainment")&&!split[i].contains("travel")&&!split[i].contains("fun")){
			Assert.assertTrue(false);
		}
	
	}
	
	}

	public void validatingselectCombinationFilters() throws Exception {
		
		AutomationUtils.Fluentwait(driver, "//div[@class='mosaic-tile-layout clearfix tile-results']//span[@class='tile-tag']", 10);
		Thread.sleep(2000);
		String titleTag=driver.findElement(By.xpath("//span[@class='tile-tag']")).getText();
		int actualValue=driver.findElements(By.xpath("//div[@class='mosaic-tile-layout clearfix tile-results']/*")).size();
		int ExpectedValue=driver.findElements(By.xpath("//span[@class='tile-tag']")).size();
		if(ExpectedValue!=actualValue&&!titleTag.equals("Hotels")){
			Assert.assertTrue(false);
		}
		
		Thread.sleep(2000);
		int actualValue1=driver.findElements(By.xpath("//div[@class='mosaic-tile-layout clearfix tile-results']/*")).size();
		int ExpectedValue1=driver.findElements(By.xpath("//i[@class='wyn-icon-adventure']")).size()-1;
		if(ExpectedValue1!=actualValue1){
			Assert.assertTrue(false);
		}
	}

	public void clickAirLineRedeem() throws Exception {
		
		AutomationUtils.Fluentwait(driver, "//div[@class='text-offer-card offer-2']/div/a", 15);
		AutomationUtils.scrollToElment(driver, "//div[@class='text-offer-card offer-2']/div/a");
		clickAirLineRedeem.click();
	}
	
}
