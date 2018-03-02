package com.wyn.automation.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AjaxHandler {

	private static Logger Log = Logger.getLogger(AjaxHandler.class.getName());

	public static boolean handle(WebDriver driver, By by) {
		Log.info("Inside handle method");
		List<WebElement> elements;
		try {
			elements = driver.findElements(By.xpath(
					"//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]//div[@class='ui-datepicker-title']/span"));

			for (WebElement element : elements) {
				Log.info("Element is displayed" + element.getText());
				element.isDisplayed();
			}
			return true;
		} catch (StaleElementReferenceException ste) {

			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]//div[@class='ui-datepicker-title']/span")));
			return handle(driver, by);
		} catch (Exception ex) {
			Log.info("Exception occured" + ex.getMessage());
			return false;
		}
		
	}

	
	public static boolean handleSignInClick(WebDriver driver) throws ElementNotVisibleException {
		Log.info("Inside handle method");
		List<WebElement> elements;
		try {
			elements = driver.findElements(By.xpath(
					"//div[contains(@class,'signed-in-container recognized-container signed-in')]/i"));

			for (WebElement element : elements) {
				Log.info("Element is displayed" + element.getText());
				element.isDisplayed();
			}
			return true;
		} catch (StaleElementReferenceException ste) {

			WebDriverWait wait = new WebDriverWait(driver, 35);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
					"//div[contains(@class,'signed-in-container recognized-container signed-in')]/i")));
			return handleSignInClick(driver);
		} catch (Exception ex) {
			Log.info("Exception occured" + ex.getMessage());
			return false;
		}
		
		
	}
}
