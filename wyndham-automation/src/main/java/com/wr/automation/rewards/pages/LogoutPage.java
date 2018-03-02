package com.wr.automation.rewards.pages;



import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AjaxHandler;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;



public class LogoutPage extends BasePage<LogoutPage> {
	private static Logger Log = Logger.getLogger(LogoutPage.class.getName());

	public LogoutPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("");
	}
	@FindBy(xpath = "//div[contains(@class,'signed-in-container recognized-container signed-in')]/i")
	WebElement signedIn;

	@FindBy(className = "sign-out-button")
	WebElement signOutButtons;

	public LogoutPage openPage() {
		return super.openPage(LogoutPage.class);

	}

	public void logOut() throws Exception {

		 AutomationUtils.waitVisibilityOf(driver, signedIn);
         Thread.sleep(12000);
        
         String hi=driver.findElement(By.xpath("//div[@class='welcome-text menu-title']")).getText();
 		String member=driver.findElement(By.xpath("//span[@data-binding='Channel.WR.MembershipID']")).getText();
 		String points=driver.findElement(By.xpath("//span[@data-binding='AccountInfo.PointBalance']")).getText();
 	   if(!hi.contains("HI,")||member.equals("")||points.equals("")){
 		   Assert.assertTrue(false);
 	   }
         
         
        signedIn.click();
		
		Log.info("SignedOut Text" + signOutButtons.getText());

		signOutButtons.click();

	}
	public void validateLogin(){
		String hi=driver.findElement(By.xpath("//div[@class='welcome-text menu-title']")).getText();
		String member=driver.findElement(By.xpath("//span[@data-binding='Channel.WR.MembershipID']")).getText();
		String points=driver.findElement(By.xpath("//span[@data-binding='AccountInfo.PointBalance']")).getText();
	   if(!hi.contains("Hi,")||member.equals("")||points.equals("")){
		   Assert.assertTrue(false);
	   }
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		waitForPageLoad();

	}
}

