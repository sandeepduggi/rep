package com.wyn.automation.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.PropertiesUtil;

public abstract class BasePage<T extends CustomLoadableComponent<T>> extends CustomLoadableComponent<T> {

	
	public static Logger Log = Logger.getLogger(BasePage.class.getName());

	public WebDriver driver;

	ExtentReports extent;
	ExtentTest test;

	public BasePage(WebDriver driver) {
		this.driver = driver;

	}

	String currentPageUrl;

	public T openPage(Class<T> page) {
		T t = PageFactory.initElements(Browsers.getBrowser(), page);
		if (!driver.getCurrentUrl().contains(currentPageUrl)) {
			driver.get(PropertiesUtil.getPropertyValue(Constants.DOMAIN_URL) + currentPageUrl);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Log.info("Current Page URL : " + driver.getCurrentUrl());
		return t.get();
	}

	public void currentPageUrl(String pageUrl) {
		currentPageUrl = pageUrl;
	}

	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver input) {
				return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
			}
		};
		wait.until(pageLoaded);
	}

	public void sendKeys(WebElement element, String text) {
		Log.info("Inside SendKeys method : " + element.getTagName() + " : " + text);
		element.sendKeys(text);
	}

	public void selectDropDownText(WebElement element, String Text) {
		Log.info("Inside Dropdown method : " + element.getTagName() + " : " + Text);
		Select select = new Select(element);
		select.selectByVisibleText(Text);
	}

	public void selectDropDownValue(WebElement element, String Text) {
		Log.info("Inside Dropdown method : " + element.getTagName() + " : " + Text);
		Select select = new Select(element);
		select.selectByValue(Text);
	}

	public void selectDropDownIndex(WebElement element, int i) {
		Log.info("Inside Dropdown method : " + element.getTagName() + " : " + i);
		Select select = new Select(element);
		select.selectByIndex(i);
	}

	public void click(WebElement element) throws Exception {
		Log.info("Inside Click method : " + element.getTagName());
		
		Thread.sleep(800);
		element.click();

	}
}
