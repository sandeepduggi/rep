package com.wyn.automation.utils;

import java.io.File;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browsers {

	static WebDriver driver;

	public static void clean_driver() {
		driver = null;
	}

	public static WebDriver getBrowser() {
		File currentDirectory = new File(new File(".").getAbsolutePath());
		String driverPath = currentDirectory.getAbsolutePath().replace(".", "");

		String key = PropertiesUtil.getPropertyValue("driver");
		if (driver == null) {

			switch (key) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "inputdata/chromedriver.exe");
				 driver = new ChromeDriver();
				 driver.manage().window().maximize();
				break;
			case "IE":
				System.setProperty("webdriver.ie.driver", "drivers/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			    driver.manage().window().maximize();
				break;
			case "FireFox":
				System.setProperty("webdriver.firefox.marionette", "drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			    driver.manage().window().maximize();
				break;
			default:
				break;
			}

		}
		return driver;
	}
	public static void nullDriver(){
		driver=null;
	}

}