package com.wr.automation.rewards.pages;


import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class WrSiteMap extends BasePage<WrSiteMap> {
	private int number;
	public WrSiteMap() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham-rewards/sitemap");
		ExcelUtils.load("SiteMap");
	}

	public WrSiteMap openPage() throws Exception {
		return super.openPage(WrSiteMap.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}

	public void linksVerify() throws Exception {
		
		int lastRow = ExcelUtils.getLastRow("SiteMapLinksInOrder");
		String expectedValue="";
		String getUrl="";
		for (int i = 0; i <= lastRow; i++) {
			Thread.sleep(2500);
			number=i;
			if(number==8){
				AutomationUtils.scroll(driver, 0, 280);
			}
			XSSFRow row = ExcelUtils.getRowData("SiteMapLinksInOrder", i);
			expectedValue=row.getCell(0).getStringCellValue();
			linksLooping();
			Thread.sleep(4500);
			getUrl=driver.getCurrentUrl();
			if(!getUrl.contains(expectedValue)){
				Assert.assertTrue(false);
			}
			driver.navigate().back();
		}
			
	}
	 public void linksLooping(){
		 List<WebElement> links=driver.findElements(By.xpath("//p[@style='text-align: left;']/following-sibling::p//a"));		
      	links.get(number).click();  
	}
public void linkNav() throws Exception{
	int lastRow = ExcelUtils.getLastRow("SiteMapLinksInOrder");
	String expectedValue="";
	String getUrl="";
	for (int i = 1; i <= lastRow; i++) {
		Thread.sleep(2500);
		number=i;
		if(number==8){
			AutomationUtils.scroll(driver, 0, 280);
		}
		XSSFRow row = ExcelUtils.getRowData("SiteMapLinksInOrder", i);
		expectedValue=row.getCell(1).getStringCellValue();
		clickLinks(expectedValue);
		Thread.sleep(4500);
		getUrl=driver.getCurrentUrl();
		if(!getUrl.contains(expectedValue)){
			Assert.assertTrue(false);
		}
		driver.navigate().back();
	}
}
public void clickLinks(String value){
	 List<WebElement> links=driver.findElements(By.xpath("//p[@style='text-align: left;']/following-sibling::p//a"));		
   	for (int i = 0; i <links.size() ; i++) {
		if(links.get(i).getAttribute("href").contains(value)){
			links.get(i).click();
			break;
		}
	}
}
	
}
