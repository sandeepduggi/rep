package com.wyn.automation.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class AutomationUtils {

	public static WebDriver connect() {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("https://fqa.wyndhamhotels.com/wyndham-rewards/enroll");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;

	}
	public static void SelectItem(WebElement element, String text)
	{
		try
		{
	        Select state=new Select(element);
	        state.selectByVisibleText(text);			
			//Logger.EnterLog("Select Item: Element ->"+ element.toString() + ", Selected Text -> "+element.getText(), StepStatus.PASS);
		}
		catch (Exception e)
		{
			//Logger.EnterLog("Exception while selecting item in dropdown element: "+ element.toString()+ ". Exception details: "+e.getMessage(), StepStatus.FAIL);
		}
	}
	public static void loadExcelFile(String excelFileName) {
		File currentDirectory = new File(new File(".").getAbsolutePath());
		try {
			System.out.println(currentDirectory.getCanonicalPath());
			ExcelUtils.setExcelFile(currentDirectory.getCanonicalPath() + "\\inputdata\\" + excelFileName, "BrandsBooking1");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getRelativePath() {
		File directory = new File("./");
		return directory.getAbsolutePath();

	}

	public static int Fluentwait(WebDriver driver, final String css) {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.cssSelector(css));
				}
			});
			return 1;
		} catch (Exception e) {
			return 0;

		}
	}

	public static int Fluentwait(WebDriver driver, final String xpath, int i) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(i, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

			WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(xpath));
				}
			});
			return 1;
		} catch (Exception e) {

			return 0;
		}

	}
	public static void waitPresenceOfElementLocatedByXpath(WebDriver driver,String xpath){
		(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
	}
	
	public static void waitPresenceOfElementLocatedById(WebDriver driver,String id){
		(new WebDriverWait(driver, 15)).until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
	}
	public static void waitElementToBeClikable(WebDriver driver,WebElement element){
		(new WebDriverWait(driver, 15)).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitVisibilityOf(WebDriver driver,WebElement element){
		(new WebDriverWait(driver, 25)).until(ExpectedConditions.visibilityOf(element));
	}
	public static void waitVisibilityOf(WebDriver driver,String xpath){
		(new WebDriverWait(driver, 25)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
	}
	public static int YLocation(WebDriver driver, String xpath) {
		WebElement Location = driver.findElement(By.xpath(xpath));
		Point p = Location.getLocation();
		return p.getY();
	}

	public static void scroll(WebDriver driver, int x, int y) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(" + x + "," + y + ")", "");

	}
	public static void zoomOut() throws Exception{
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		Thread.sleep(800);
		r.keyPress(KeyEvent.VK_MINUS);
		Thread.sleep(800);
		r.keyRelease(KeyEvent.VK_MINUS);
		Thread.sleep(800);
		r.keyPress(KeyEvent.VK_MINUS);
		Thread.sleep(800);
		r.keyRelease(KeyEvent.VK_MINUS);
		Thread.sleep(800);
		r.keyPress(KeyEvent.VK_MINUS);
		Thread.sleep(800);
		r.keyRelease(KeyEvent.VK_MINUS);
		Thread.sleep(800);
		r.keyRelease(KeyEvent.VK_CONTROL);
		
	}

	public static void scrollToElment(WebDriver driver, String xpath) {
		WebElement scroll = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-220)");
	}

	public static String captureScreenShot(WebDriver driver, String name) throws Exception {

		Thread.sleep(1200);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "\\screenshots\\" + name + ".png";
		try {
			org.apache.commons.io.FileUtils.copyFile(source, new File(dest));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dest;
	}

}
