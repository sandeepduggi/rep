package com.wyn.automation.pages;

import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;


public class PropertyOverviewPage extends BasePage<PropertyOverviewPage> {
	
	 @FindBy(xpath="//button[@class='search-btn btn-primary'][text()='Book Now']")
		WebElement BookNow;

	public PropertyOverviewPage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		XSSFRow  row = ExcelUtils.getRowData("Configurations", 1);
		currentPageUrl(ExcelUtils.getStringCellValue(row, 1));
	}
	public PropertyOverviewPage openPage() throws Exception{
		return super.openPage(PropertyOverviewPage.class);
	}
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
		// TODO Auto-generated method stub
		
	}
	public String ClickBookNow() throws Exception{
		String sp;
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		AutomationUtils.Fluentwait(driver,"div[class='pricing']");
		BookNow.click();
		return "";
	}

}
