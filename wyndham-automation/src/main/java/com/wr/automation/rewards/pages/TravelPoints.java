package com.wr.automation.rewards.pages;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class TravelPoints extends BasePage<TravelPoints>{
	public TravelPoints() throws Exception {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
		ExcelUtils.load("WrRedeemTravelPoints");
	}

	public TravelPoints openPage() throws Exception {
		return super.openPage(TravelPoints.class);
	}

	@Override
	protected void isLoaded() {
		super.waitForPageLoad();
	}

	@Override
	protected void load() {
		// driver.get("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
	}

	@FindBy(xpath="//div[@id='AA'][@class='redemption-option-listing bg-white']//button")
    WebElement clickAmericanAirlineRedeem;
	
	@FindBy(id="firstName")
    WebElement firstName;
	
	@FindBy(id="lastName")
    WebElement lastName;
	
	@FindBy(id="travelerNumber")
    WebElement travelerNumber;
	
	@FindBy(xpath="//div[@class='form-group checkbox-group']//span/span")
    WebElement clickCheckBox;
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-block submit-button']")
    WebElement submitButton;
	
	
	
	public void clickAmericanAirlineRedeem() throws Exception {
		Thread.sleep(7000);
		AutomationUtils.Fluentwait(driver,"//div[@id='AA'][@class='redemption-option-listing bg-white']//button",15);
		AutomationUtils.scrollToElment(driver, "//div[@id='AA'][@class='redemption-option-listing bg-white']//button");
		Thread.sleep(1200);
		clickAmericanAirlineRedeem.click();
		
	}
	int intPoints;
	public void enterDetails() throws Exception {
		String points=driver.findElement(By.xpath("//span[@data-binding='AccountInfo.PointBalance']")).getText();
		if(points.contains(",")){
			String split[]=points.split(",");
			points=split[0]+split[1];
		intPoints=Integer.parseInt(points);
		}
		else{
			intPoints=Integer.parseInt(points);
		}
		XSSFRow row = ExcelUtils.getRowData("WrRedeemTravelPoints", 1);
		Boolean isRedeemPointspresent=driver.findElements(By.xpath("//div[@class='redemption-flow'][not(@style='display: none;')]")).size()>0;
		if(isRedeemPointspresent)
			Thread.sleep(2000);
		firstName.sendKeys(ExcelUtils.getStringCellValue(row, 1));
		lastName.sendKeys(ExcelUtils.getStringCellValue(row, 2));
		travelerNumber.sendKeys(ExcelUtils.getStringCellValue(row, 3));
		clickCheckBox.click();
		Thread.sleep(1200);
		AutomationUtils.scrollToElment(driver, "//button[@class='btn btn-primary btn-block submit-button']");
		submitButton.click();
		
	}

	public void validateTravelPoints() throws Exception {
	
		AutomationUtils.Fluentwait(driver, "//div[@class='redemption-flow'][@style='']",10);
		Boolean popUpIsPresent=driver.findElements(By.xpath("//div[@class='redemption-flow'][@style='']")).size()>0;
		Thread.sleep(2000);
		int intreductionPoints;
		String reductionPoints=driver.findElement(By.xpath("//div[@class='points-redeemed summary-line']/div[@class='value']")).getText();
		if(reductionPoints.contains(",")){
			String split[]=reductionPoints.split(",");
			reductionPoints=split[0]+split[1];
			intreductionPoints=Integer.parseInt(reductionPoints);
		}
		else{
			 intreductionPoints=Integer.parseInt(reductionPoints);
		}
		
		intPoints=intPoints-intreductionPoints;
		
		String points=driver.findElement(By.xpath("//span[@data-binding='AccountInfo.PointBalance']")).getText();
		int intPointsActual;
		if(points.contains(",")){
			String split[]=points.split(",");
			points=split[0]+split[1];
			 intPointsActual=Integer.parseInt(points);
		}
		else{
			 intPointsActual=Integer.parseInt(points);
		}
		
		if(intPointsActual!=intPoints){
			Assert.assertTrue(false);
		}
	}
	
}
