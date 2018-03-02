package com.wr.automation.rewards.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AjaxHandler;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

import net.sourceforge.htmlunit.corejs.javascript.ast.ForLoop;



public class MyAccount extends BasePage<MyAccount>{

	public MyAccount() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public MyAccount openPage() throws Exception {
		return super.openPage(MyAccount.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}
	@FindBy(xpath = "//div[contains(@class,'signed-in-container recognized-container signed-in')]/i")
	WebElement signedIn;

	@FindBy(xpath = "//li[@class='mm-nav-list-item ']//a[contains(@href,'my-account')]")
	WebElement clickAccount;
	
	@FindBy(xpath = "//div[@class='col-xs-24 col-sm-14 col-md-12']")
	WebElement goFreeGoFast;
	
	@FindBy(xpath = "//div[@class='col-xs-24 col-sm-14 col-md-12']//div[@class='unlocked-message']/a")
	WebElement clickGoFreeBookNow;
	
	@FindBy(xpath = "//button[contains(@class,'search-btn btn-primary')]")
	static
	WebElement Search;
	
	@FindBy(xpath = "//div[@aria-expanded='true'][@style='display: block;']//li[@class='mm-nav-list-item ']//a[contains(@href,'redeem')]")
	WebElement clickRedeem;
	
	
	@FindBy(css = ".destination.ui-autocomplete-input")
	static
	 WebElement destination;

	public void clickMyAccount() {

//		AutomationUtils.waitVisibilityOf(driver, signedIn);
//		AjaxHandler.handleSignInClick(driver);
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        signedIn.click();
        AutomationUtils.waitVisibilityOf(driver,clickAccount);
        clickAccount.click();
		
	}
	public void clickRedeem() {

//		AutomationUtils.waitVisibilityOf(driver, signedIn);
//		AjaxHandler.handleSignInClick(driver);
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        signedIn.click();
        AutomationUtils.waitVisibilityOf(driver,clickRedeem);
        clickRedeem.click();
		
	}
	
	
	public void refreshPage(){
		driver.get("https://rqa.wyndhamhotels.com/wyndham-rewards");
	}
	public void verifyComponentPresnets() {
    Boolean goFreeGoFastIsPresent=driver.findElements(By.xpath("//div[@class='col-xs-24 col-sm-14 col-md-12']")).size()>0;
    Boolean pointsIsPresent=driver.findElements(By.xpath("//div[@class='headline-c points-message member-level-color']")).size()>0;
    Boolean myActivityIsPresent=driver.findElements(By.xpath("//div[@class='recent-activity-component']")).size()>0;
    Boolean myNextReservationIsPresent=driver.findElements(By.xpath("//div[@class='col-xs-24 col-sm-24 col-md-11']")).size()>0;
    if(!goFreeGoFastIsPresent||!pointsIsPresent||!myActivityIsPresent||!myNextReservationIsPresent)
    {
    	Assert.assertTrue(false);
    }	
	List<WebElement> elements=driver.findElements(By.xpath("//table[@class='table wyn-table']/tbody/tr/td[4]/span"));
    for(int i=0;i<elements.size();i++){
    	String value=elements.get(i).getText();
	    if(value.equals("")){
	    	Assert.assertTrue(false);
	    }
   }
    Boolean myActivityNoColums=driver.findElements(By.xpath("//table[@class='table wyn-table']/thead/tr/th")).size()==4;
    if(!myActivityNoColums){
    	Assert.assertTrue(false);
    }
    }

	public void verifyQuickNav() throws Exception{
		String TestcaseName="TC_wr_1";
		int TestcaseNumber=0;
		try {
			TestcaseNumber=ExcelUtils.getRowNumber("MyAccount", TestcaseName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		XSSFRow row = ExcelUtils.getRowData("MyAccount", TestcaseNumber);
		
		try {
			AutomationUtils.Fluentwait(driver,"//img[@class='img img-responsive']", 25);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cellValue="";
		String input="";
		System.out.println("Number of links:"+Constants.quickLinks.length);
		for (int i = 0; i < Constants.quickLinks.length; i++) {
			input=Constants.quickLinks[i];
			Thread.sleep(1200);
			quickLinks(Constants.quickLinks[i]);
			System.out.println("links from arraylist:"+input);
			Thread.sleep(3000);
		for (int k = 2; k < Constants.quickLinks.length; k++) {
			cellValue=ExcelUtils.getStringCellValue(row, k);
			System.out.println("Expected Excel values:"+cellValue);
			Thread.sleep(800);
			System.out.println("Actual Url to be Compared:"+driver.getCurrentUrl());
			if(!driver.getCurrentUrl().contains(cellValue)&&k==Constants.quickLinks.length+1)
			{
				Assert.assertTrue(false);
			}
		}
		driver.navigate().back();
		}
	}
	public void quickLinks(String input) throws Exception{
		AutomationUtils.Fluentwait(driver, "//div[@class='hidden-xs']//li[contains(@class,'quick-links-item')]/a", 10);
		List<WebElement> elements=driver.findElements(By.xpath("//div[@class='hidden-xs']//li[contains(@class,'quick-links-item')]/a"));
		   
		for (int i = 0; i < elements.size()-1; i++) {
			AutomationUtils.scrollToElment(driver,"//div[@class='hidden-xs']//li[contains(@class,'quick-links-item')]/a");
			System.out.println("clicked link:"+elements.get(i).getText());
			if(elements.get(i).getText().equals(input))
			{
				elements.get(i).click();
				break;
			}
			
		}
		System.out.println("----------------------------------------------------");
	}

	public void clickGoFreeBookNow() {
		String statsNumber="";
		String freeAwardsNumber="";
		 Boolean goFreeGoFastIsPresent=driver.findElements(By.xpath("//div[@class='col-xs-24 col-sm-14 col-md-12']")).size()>0;
		 if(!goFreeGoFastIsPresent){
			 Assert.assertTrue(false);
		 }
		 AutomationUtils.waitVisibilityOf(driver, clickGoFreeBookNow);
	    List<WebElement> element=driver.findElements(By.xpath("//div[@class='col-xs-24 col-sm-14 col-md-12']//div[@class='icon-unlocked icon-circle-wrapper member-level-color member-level-border-color']/div"));
	    statsNumber=element.get(0).getText()+"-"+element.get(1).getText();
	    List<WebElement> element1=driver.findElements(By.xpath("//div[@class='col-xs-24 col-sm-14 col-md-12']//span[@class='free-awards']"));
	    freeAwardsNumber=element1.get(0).getText()+"-"+element1.get(1).getText();   
	    if(!statsNumber.equals(freeAwardsNumber)){
	    Assert.assertTrue(false);
	    }
	    clickGoFreeBookNow.click();
	}
	
	

	public void enterDetailsinBooknow() throws Exception {
	
	AutomationUtils.Fluentwait(driver, "//form[@name='booking-bar-form']", 10);
	AutomationUtils.Fluentwait(driver, ".destination.ui-autocomplete-input");
	int rowNumber = ExcelUtils.getRowNumber("wrBooking","TC_01");
	XSSFRow row = ExcelUtils.getRowData("WrBooking", rowNumber);
	String destinationPoint = ExcelUtils.getStringCellValue(row, 0);
	destination.sendKeys(destinationPoint);
	driver.findElement(By.xpath("//i[@aria-label='Clear search']")).click();
	destination.sendKeys(destinationPoint);
	Thread.sleep(2500);
	driver.findElement(By.xpath("//ul[@id='ui-id-1']/li[1]")).click();
//	WrHomePage.Checkindate.click();
//	WrHomePage.checkInDate(ExcelUtils.getStringCellValue(row, 1), ExcelUtils.getStringCellValue(row, 2));
//	
//	WrHomePage.checkOutDate(ExcelUtils.getStringCellValue(row, 3), ExcelUtils.getStringCellValue(row, 4));

	Search.click();

		
	}
	
}
