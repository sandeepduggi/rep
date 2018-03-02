package com.wr.automation.rewards.pages;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.wyn.automation.base.BasePage;
import com.wyn.automation.constants.Constants;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;

public class EnrollPageThree extends  BasePage<EnrollPageThree> {

	@FindBy(id = "question0")
	WebElement question0;

	@FindBy(name = "answer0")
	WebElement answer0;

	@FindBy(id = "question1")
	WebElement question1;

	@FindBy(name = "answer1")
	WebElement answer1;

	@FindBy(id = "question2")
	WebElement question2;

	@FindBy(name = "answer2")
	WebElement answer2;

	@FindBy(xpath = "//*[@id='securitySetup']/button")
	WebElement submit;

	WebDriver driver;

	public EnrollPageThree() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("wyndham");
	}

	public String getStepNumber() {

		return TextElement.getText();
	}
	public EnrollPageThree openPage() {
		return super.openPage(EnrollPageThree.class);
	}
	Select question0Dropdown;
	Select question1Dropdown;
	Select question2Dropdown;

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();
		question0Dropdown = new Select(question0);

		question1Dropdown = new Select(question1);
		question2Dropdown = new Select(question2);

		// Assert.assertEquals("testuser_14", str);
	}

	static void waitForPageLoad(WebDriver wdriver) {
		WebDriverWait wait = new WebDriverWait(wdriver, 80);

		Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

			@Override
			public boolean apply(WebDriver input) {
				return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
			}

		};
		wait.until(pageLoaded);

	}

	WebElement TextElement;

	@Override
	protected void load() {

	}

	public void submit(int number) {
		popuateData(number);
		submit.click();
	}

	private void popuateData(int number) {
		XSSFRow row = ExcelUtils.getRowData("Enrollment", number);

		question0Dropdown.selectByVisibleText(row.getCell(ExcelUtils.getColumnNumber(Constants.q1)).getStringCellValue());
		question1Dropdown.selectByVisibleText(row.getCell(ExcelUtils.getColumnNumber(Constants.q2)).getStringCellValue());
		question2Dropdown.selectByVisibleText(row.getCell(ExcelUtils.getColumnNumber(Constants.q3)).getStringCellValue());
		answer0.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a1)).getStringCellValue());
		answer1.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a2)).getStringCellValue());
		answer2.sendKeys(row.getCell(ExcelUtils.getColumnNumber(Constants.a3)).getStringCellValue());

	}

	public void clear() {
		answer0.clear();
		answer1.clear();
		answer2.clear();

	}

}
