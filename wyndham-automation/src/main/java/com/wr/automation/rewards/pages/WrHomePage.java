package com.wr.automation.rewards.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.wyn.automation.base.BasePage;
import com.wyn.automation.utils.AjaxHandler;
import com.wyn.automation.utils.AutomationUtils;
import com.wyn.automation.utils.Browsers;
import com.wyn.automation.utils.ExcelUtils;



public class WrHomePage extends BasePage<WrHomePage> {
	ExtentReports extent;
	ExtentTest test;

	
	@FindBy(id = "wrUsername")
	WebElement Username;
	@FindBy(id = "wrPassword")
	WebElement password;
	
	@FindBy(xpath = "//p[@id='securityQuestion']")
	WebElement AskSecQuesion;
	@FindBy(id = "securityAnswer")
	WebElement Answer;
	@FindBy(id = "submit-button")
	WebElement Submitbutton;

	@FindBy(css = ".destination.ui-autocomplete-input")
	static
	 WebElement destination;

	@FindBy(xpath = "//button[contains(@class,'search-btn btn-primary')]")
	static
	
	WebElement Search;

	@FindBy(css = "span[class='number rooms']+button")
	WebElement Rooms;

	@FindBy(css = "span[class='number guests adults']+button")
	WebElement Adults;

	@FindBy(css = "span[class='number guests children']+button")
	WebElement Childrens;

	@FindBy(css = "button[class*='has-caret mask rooms-and-guests-button rooms-and-guest']")
	static WebElement RoomsAndGuestButton;

	@FindBy(css = ".btn.check-in-button.check-in.calendar-button")
	static
	WebElement Checkindate;

	@FindBy(xpath = "//a[@class='wr-login-links login-display']")
	WebElement Signin;

	@FindBy(id = "wrUsername")
	WebElement UserName;

	@FindBy(id = "wrPassword")
	WebElement Password;

	@FindBy(xpath = "//div[@class='login-btn-text']")
	WebElement LoginBtn;

	@FindBy(id = "securityQuestion")
	WebElement SecurityQest;

	@FindBy(id = "securityAnswer")
	WebElement SecurityAns;

	@FindBy(id = "submit-button")
	WebElement SecuritySubmit;

	@FindBy(xpath = "//a[@class='wr-logged-user-name']")
	WebElement SininClick;

	@FindBy(xpath = "//select[@name='sqPrompt1']")
	WebElement SecurityQuestion1;

	@FindBy(xpath = "//select[@name='sqPrompt2']")
	WebElement SecurityQuestion2;

	@FindBy(xpath = "//select[@name='sqPrompt3']")
	WebElement SecurityQuestion3;

	@FindBy(xpath = "//input[@name='sqAnswer1']")
	WebElement SecurityAnswer1;

	@FindBy(xpath = "//input[@name='sqAnswer2']")
	WebElement SecurityAnswer2;

	@FindBy(xpath = "//input[@name='sqAnswer3']")
	WebElement SecurityAnswer3;

	@FindBy(xpath = "//button[@id='set-questions']")
	WebElement SubmitAnswer;

	@FindBy(xpath = "//button[@id='confirmAnswers']")
	WebElement confirmAnswers;

	@FindBy(xpath = "//a[@class='sign-out-link']")
	WebElement SininOut;

	@FindBy(xpath = "//a[contains(text(),'Customer Care')]")
	WebElement ContactUs_Click;

	@FindBy(xpath = "//button[@data-label='Special Rates']")
	WebElement SpecialRate_Click;

	@FindBy(xpath = "//a[contains(text(),'Locations')]")
	WebElement Locations_Click;

	@FindBy(xpath = "//a[text()='Offers']")
	WebElement Offers_Click;

	static WebDriver driver;

	public WrHomePage() {
		super(Browsers.getBrowser());
		driver = Browsers.getBrowser();
		currentPageUrl("/wyndham-rewards");
		try {
			ExcelUtils.load("WrBooking");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public WrHomePage openPage() throws Exception {
		return super.openPage(WrHomePage.class);
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isLoaded() throws Error {
		super.waitForPageLoad();

	}

	public String Click(int number) throws Exception {
		XSSFRow row = ExcelUtils.getRowData("Screen1", number);

		Log.info("Last row in Excel:" + ExcelUtils.getLastRow("Screen1"));

		Checkindate.click();

		String rooms = ExcelUtils.getStringCellValue(row, 5);
		String adult = ExcelUtils.getStringCellValue(row, 6);
		String childrens = ExcelUtils.getStringCellValue(row, 7);
		checkInDate(ExcelUtils.getStringCellValue(row, 1), ExcelUtils.getStringCellValue(row, 2));

		checkOutDate(ExcelUtils.getStringCellValue(row, 3), ExcelUtils.getStringCellValue(row, 4));

		RoomsAndGuestButton.click();

		Rooms_add(Rooms, Adults, Childrens, rooms, adult, childrens);

		String sp = AutomationUtils.captureScreenShot(driver, ExcelUtils.getStringCellValue(row, 9));
		Log.info("Clicking Search Button:" + Search);
		Search.click();
		return sp;
	}

	public String specialRateClick(String ScreenName,int TestNo) throws Exception {
		
		XSSFRow row = ExcelUtils.getRowData(ScreenName, TestNo);

		Log.info("Last row in Excel:" + ExcelUtils.getLastRow("Screen1"));

		String rooms = ExcelUtils.getStringCellValue(row, 5);
		String adult = ExcelUtils.getStringCellValue(row, 6);
		String childrens = ExcelUtils.getStringCellValue(row, 7);
		// selectdate(ExcelUtils.getStringCellValue(row, 1),
		// ExcelUtils.getStringCellValue(row, 2));

		// selectdate(ExcelUtils.getStringCellValue(row, 3),
		// ExcelUtils.getStringCellValue(row, 4));

		RoomsAndGuestButton.click();
//        Thread.sleep(2500);
		Rooms_add(Rooms, Adults, Childrens, rooms, adult, childrens);
//		 Thread.sleep(2500);
		SpecialRate_Click.click();

		Log.info("Clicking Search Button:" + Search);
		String Special_Rate = ExcelUtils.getStringCellValue(row, 8);
//		Thread.sleep(2000);
		Special_Rate_Select(Special_Rate);
		Search.click();
		return "";
	}

	public static  void changeCheckIn(String ScreenName,int TestNo) throws Exception {
		XSSFRow row = ExcelUtils.getRowData(ScreenName, TestNo);
		AutomationUtils.scrollToElment(driver,"//button[@class='btn check-in-button check-in calendar-button']");
//		super.click(Checkindate);
		Checkindate.click();
		checkInDate(ExcelUtils.getStringCellValue(row, 1), ExcelUtils.getStringCellValue(row, 2));
	}

	public static void changeCheckOut(String ScreenName,int TestNo) throws InterruptedException {

		XSSFRow row = ExcelUtils.getRowData(ScreenName, TestNo);
		checkOutDate(ExcelUtils.getStringCellValue(row, 3), ExcelUtils.getStringCellValue(row, 4));
	}

	public static void clickSearch() {

		try {
//			super.click(Search);
			Search.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String promocode_Click(int number, String promocode) throws Exception {
		 XSSFRow row = ExcelUtils.getRowData("BrandsBooking1", 1);
		String rooms = ExcelUtils.getStringCellValue(row, 5);
		String adult = ExcelUtils.getStringCellValue(row, 6);
		String childrens = ExcelUtils.getStringCellValue(row, 7);

		RoomsAndGuestButton.click();

		Rooms_add(Rooms, Adults, Childrens, rooms, adult, childrens);

		String sp = AutomationUtils.captureScreenShot(driver, ExcelUtils.getStringCellValue(row, 9));
		SpecialRate_Click.click();

		Log.info("Clicking Search Button:" + Search);
		promocode_Select("Promotional Code", promocode);
		Search.click();
		return sp;
	}

	public void Special_Rate_Select(String rate) {
		String Special_Rate = "";
		List<WebElement> element = driver
				.findElements(By.xpath("//div[@class='specialty-rates-radio clearfix']//following::span/span"));
		for (int i = 0; i < element.size(); i++) {

			Special_Rate = element.get(i).getText();
			if (Special_Rate.equals(rate)) {
				element.get(i).click();
				break;
			}
		}
	}

	public void promocode_Select(String rate, String code) throws Exception {
		String Special_Rate = "";
		List<WebElement> element = driver
				.findElements(By.xpath("//div[@class='specialty-rates-radio clearfix']//following::span/span"));
		for (int i = 0; i < element.size(); i++) {

			Special_Rate = element.get(i).getText();
			if (Special_Rate.equals(rate)) {
				element.get(i).click();

				driver.findElement(By
						.xpath("//div[@class='specialty-rates-radio clearfix']/div[3]/div[1]//input[@placeholder='Enter Code']"))
						.sendKeys(code);
				break;
			}
		}
	}

	public String login(String Username, String password) throws Exception {
		Signin.click();

		UserName.sendKeys(Username);
		Password.sendKeys(password);
		String sp = AutomationUtils.captureScreenShot(driver, "Login");
		LoginBtn.click();
		AutomationUtils.Fluentwait(driver, "//button[@id='submit-button']", 30);

		System.out.println(SecurityQest.getText());
		// wrong answer for the question
		SecurityAns.sendKeys("city1");
		SecuritySubmit.click();

		// right answer for the question

		if (SecurityQest.getText().contains("street")) {
			SecurityAns.clear();
			SecurityAns.sendKeys("street");
		} else if (SecurityQest.getText().contains("city or town")) {
			SecurityAns.clear();
			SecurityAns.sendKeys("city");
		} else if (SecurityQest.getText().contains("born")) {
			// SecurityAns.clear();
			SecurityAns.clear();
			SecurityAns.sendKeys("city");
		} else {
			SecurityAns.clear();
			SecurityAns.sendKeys("school");
		}

		SecuritySubmit.click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return sp;
	}

	


	public void login_Locking_Account_with_SecAns(String Username, String password, int login_no) throws Exception {
		Signin.click();

		UserName.sendKeys(Username);
		Password.sendKeys(password);
		String sp = AutomationUtils.captureScreenShot(driver, "Login");
		LoginBtn.click();
		AutomationUtils.Fluentwait(driver, "//button[@id='submit-button']", 30);

		System.out.println(SecurityQest.getText());
	
		UserName.sendKeys(Username);
		Password.sendKeys(password);
		LoginBtn.click();
		Boolean ispresent = driver.findElements(By.xpath("//span[contains(text(),'Your account has been locked.')]"))
				.size() > 0;
		String Atual_Error = driver.findElement(By.xpath("//span[contains(text(),'Your account has been locked.')]"))
				.getText();
		 XSSFRow row = ExcelUtils.getRowData("BrandsBooking1", 1);
		String Expected_Error = ExcelUtils.getStringCellValue(row, 2);
		if (!Atual_Error.contains(Expected_Error)) {
			Assert.assertTrue(false);
		}

	}


	public void loginPerformOperation(String ScreenName,int TestNo) throws Exception {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		Thread.sleep(3500);
		Signin.click();
		XSSFRow row = ExcelUtils.getRowData(ScreenName, TestNo);
		String userName = ExcelUtils.getStringCellValue(row, 9);
		String password = ExcelUtils.getStringCellValue(row, 10);
		Thread.sleep(2000);
		UserName.sendKeys(userName);
		Password.sendKeys(password);
		LoginBtn.click();
		try {
			AutomationUtils.Fluentwait(driver, "//div[@class='login-page'][@style='display: none;']", 15);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(SecurityQest.getText());

		// right answer for the question

		if (SecurityQest.getText().contains("school")) {
			SecurityAns.clear();
			SecurityAns.sendKeys("school");
		} else if (SecurityQest.getText().contains("mother")) {
			SecurityAns.clear();
			SecurityAns.sendKeys("mother");
		} else if (SecurityQest.getText().contains("city")) {
			// SecurityAns.clear();
			SecurityAns.clear();
			SecurityAns.sendKeys("city");
		} else if (SecurityQest.getText().contains("job")){
			SecurityAns.clear();
			SecurityAns.sendKeys("job");
		}else if (SecurityQest.getText().contains("town")){
			SecurityAns.clear();
			SecurityAns.sendKeys("town");
		}

		SecuritySubmit.click();

//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		Thread.sleep(8000);
		
		AutomationUtils.Fluentwait(driver, "//div[@class='wyndham-rewards-user-details dropdown']", 15);
	}


	public static void enterDestination(String ScreenName,int TestNo) throws Exception {
		AutomationUtils.Fluentwait(driver, ".destination.ui-autocomplete-input");
		destination.clear();
		XSSFRow row = ExcelUtils.getRowData(ScreenName, TestNo);
		String destinationPoint = ExcelUtils.getStringCellValue(row, 0);
		Log.info("Entering destination in homepage :" + destinationPoint);
//		super.sendKeys(destination, destinationPoint);
		destination.sendKeys(destinationPoint);
	}

	public static void checkInDate(String checkin_month, String Checkin_days) {
		String s = new String("");
		Log.info("Selecting CheckIn and CheckOut date Homepage URL:" + driver.getCurrentUrl());

		List<WebElement> elements = driver.findElements(By.xpath(
				"//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]//div[@class='ui-datepicker-title']/span"));

		for (int i = 0; i < elements.size(); i++) {

			if (i == 2) {
				s = s + ":" + elements.get(i).getText();

			} else {
				s = s + elements.get(i).getText();

			}
		}
		String split[] = s.split(":");
		for (int j = 0; j < split.length; j++) {
			System.out.println(split[j]);
			if (split[j].equals(checkin_month)) {
				System.out.println("-------------------");
				List<WebElement> d = driver
						.findElements(By.xpath("//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]["
								+ (j + 1) + "]/table/tbody/tr/td/*"));
				for (int k = 0; k < d.size(); k++) {
					System.out.println(d.get(k).getText());
					if (d.get(k).getText().equals(Checkin_days)) {
						d.get(k).click();
						break;
					}

				}
			}
		}

	}

	public static void checkOutDate(String checkin_month, String Checkin_days) {
		String s = new String("");
		Log.info("Selecting CheckIn and CheckOut date Homepage URL:" + driver.getCurrentUrl());

		Boolean flag = AjaxHandler.handle(driver, By.xpath(
				"//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]//div[@class='ui-datepicker-title']/span"));
		if (flag == true) {
			List<WebElement> elements = driver.findElements(By.xpath(
					"//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]//div[@class='ui-datepicker-title']/span"));

			for (int i = 0; i < elements.size(); i++) {

				if (i == 2) {
					s = s + ":" + elements.get(i).getText();

				} else {
					s = s + elements.get(i).getText();

				}
			}
			String split[] = s.split(":");
			for (int j = 0; j < split.length; j++) {
				System.out.println(split[j]);
				if (split[j].equals(checkin_month)) {
					System.out.println("-------------------");
					List<WebElement> d = driver
							.findElements(By.xpath("//div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]["
									+ (j + 1) + "]/table/tbody/tr/td/*"));
					for (int k = 0; k < d.size(); k++) {
						System.out.println(d.get(k).getText());
						if (d.get(k).getText().equals(Checkin_days)) {
							d.get(k).click();
							break;
						}

					}
				}
			}
		}
	}

	public static void Rooms_add(WebElement element_Rooms, WebElement element_Adult, WebElement element_Children,
			String Rooms, String Adult, String Children) throws InterruptedException {

		Log.info("Selecting Number of Rooms,Adults,Childrens" + "Rooms:" + Rooms + "Adults:" + Adult + "Childrens:"
				+ Children);
		int rooms = Integer.parseInt(Rooms);
		int adults = Integer.parseInt(Adult);
		int childrens = Integer.parseInt(Children);
		for (int i = 1; i < rooms; i++) {

			element_Rooms.click();
		}
		for (int j = 1; j < adults; j++) {

			element_Adult.click();
		}
		for (int k = 1; k <= childrens; k++) {

			element_Children.click();
		}
	}

	public void Contact_us() throws Exception {
		AutomationUtils.scrollToElment(driver, "//a[contains(text(),'Customer Care')]");

		ContactUs_Click.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//a[contains(text(),'About My Stay')]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void error_validation() throws Exception {

		sendKeys(destination, "");

		AutomationUtils.scrollToElment(driver,
				"//button[contains(@class,'has-caret mask rooms-and-guests-button rooms-and-guest')]");

		RoomsAndGuestButton.click();

		Rooms_add(Rooms, Adults, Childrens, "1", "1", "1");
		Search.click();
		String Error = driver.findElement(By.xpath("//li[@class='parsley-required']")).getText();
		if (!Error.contains("This field is required.")) {
			Assert.assertTrue(false);
		}

		sendKeys(destination, "Nogales, Arizona");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Search.click();

	}

	public void Our_Locations() throws Exception {

		AutomationUtils.scrollToElment(driver, "//a[contains(text(),'Locations')]");

		Locations_Click.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public String Deallanding() throws Exception {

		Offers_Click.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return null;
	}

	public void clickResultsinPoints() {
		SpecialRate_Click.click();
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		driver.findElement(By.xpath("//input[@name='wyndham-rewards']/following-sibling::span/span")).click();
		
	}

	public void clickLogin() {
		try {
			super.click(Signin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void logintoAccount(int TestNo) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFRow row = ExcelUtils.getRowData("Screen1", TestNo);
		Username.sendKeys(ExcelUtils.getStringCellValue(row, 9));
		password.sendKeys(ExcelUtils.getStringCellValue(row, 10));
		LoginBtn.click();
		try {
			AutomationUtils.Fluentwait(driver, "//p[@id='securityQuestion']", 40);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	public void wrongSecAnswer() {
	try {
		AutomationUtils.Fluentwait(driver, "//div[@class='login-page'][@style='display: none;']", 15);
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Answer.sendKeys("wrongAnswer");
		Submitbutton.click();
	}

	public void correctSecAnswer() {
	
		try {
			Thread.sleep(6500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Answer.clear();
		String Question = AskSecQuesion.getText();
		if (Question.equals("What elementary school did you attend?")) {
			Answer.sendKeys("school");
		}
		if (Question.equals("What is your mother's maiden name?")) {
			Answer.sendKeys("mother");
		}
		if (Question.equals("In what city were you born?")) {
			Answer.sendKeys("city");
		}
		if (Question.equals("In what city or town was your first job?")) {
			Answer.sendKeys("job");
		}
		if (Question.equals("In what city or town did you meet your spouse/partner?")) {
			Answer.sendKeys("city");
		}
		if (Question.equals("In what city or town does your nearest sibling live?")) {
			Answer.sendKeys("town");
		}
		Submitbutton.click();
//		try {
//			Thread.sleep(8200);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			AutomationUtils.Fluentwait(driver, "//div[@class='wyndham-rewards-links'][@style='display: none;']", 30);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectSameQusTwice(int TestNo) {
		try {
			AutomationUtils.Fluentwait(driver, "//div[@class='sec-q-set'][@style='display: block;']", 15);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		XSSFRow row = ExcelUtils.getRowData("Screen1", TestNo);
		System.out.println(ExcelUtils.getStringCellValue(row, 11));
				selectDropDownText(SecurityQuestion1,ExcelUtils.getStringCellValue(row, 11));
				SecurityAnswer1.sendKeys(ExcelUtils.getStringCellValue(row, 14));
				System.out.println(ExcelUtils.getStringCellValue(row, 12));
				selectDropDownText(SecurityQuestion2, ExcelUtils.getStringCellValue(row, 11));
				SecurityAnswer2.sendKeys(ExcelUtils.getStringCellValue(row, 15));

				selectDropDownText(SecurityQuestion3, ExcelUtils.getStringCellValue(row, 12));
				SecurityAnswer3.sendKeys(ExcelUtils.getStringCellValue(row, 16));

				SubmitAnswer.click();
				String error = "";
				List<WebElement> element = driver.findElements(By.xpath("//li[@class='parsley-threeWayCompare']"));

				for (int i = 0; i < element.size(); i++) {
					error = element.get(i).getText();
					if (!error.contains("different question")) {
						Assert.assertTrue(false);
					}
				}
		
	}

	public void enterThreeUniqueAnswers(int TestNo) {
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFRow row = ExcelUtils.getRowData("Screen1", TestNo);
		selectDropDownText(SecurityQuestion1, ExcelUtils.getStringCellValue(row, 11));
		SecurityAnswer1.clear();
		SecurityAnswer1.sendKeys(ExcelUtils.getStringCellValue(row, 14));

		selectDropDownText(SecurityQuestion2, ExcelUtils.getStringCellValue(row, 12));
		SecurityAnswer2.clear();
		SecurityAnswer2.sendKeys(ExcelUtils.getStringCellValue(row, 15));

		selectDropDownText(SecurityQuestion3, ExcelUtils.getStringCellValue(row, 13));
		SecurityAnswer3.clear();
		SecurityAnswer3.sendKeys(ExcelUtils.getStringCellValue(row, 16));

		SubmitAnswer.click();
		
	}

	public void reviewSecQue() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//a[@aria-label='Edit SECURITY QUESTION 1']")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SubmitAnswer.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//a[@aria-label='Edit SECURITY QUESTION 2']")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SubmitAnswer.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//a[@aria-label='Edit SECURITY QUESTION 3']")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SubmitAnswer.click();
		
	}

	public void submitAnsers() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AutomationUtils.scrollToElment(driver, "//button[@id='confirmAnswers']");
		
		confirmAnswers.click();
		
	}

	public void randomCredentails(int TestNo) {
		XSSFRow row = ExcelUtils.getRowData("Screen1", TestNo);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UserName.sendKeys("WrongUsername");
		Password.sendKeys("12345");
		LoginBtn.click();
        try {
			AutomationUtils.Fluentwait(driver,"//div[@class='login-invalid-credentials error-msg-container error-msg'][@style='display: block;']", 20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean ispresent = driver.findElements(By.xpath("//span[contains(text(),'Username or password')]")).size() > 0;
		String Atual_Error = driver.findElement(By.xpath("//span[contains(text(),'Username or password')]")).getText();
		String Expected_Error = ExcelUtils.getStringCellValue(row, 17);
		if(!Expected_Error.contains(Atual_Error)) {
			Assert.assertTrue(false);
		}
		
	}

	public void suspendedCredentails(int TestNo) {
	
		XSSFRow row = ExcelUtils.getRowData("Screen1", TestNo);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserName.sendKeys("190406526E");
		Password.sendKeys("Password1");
		LoginBtn.click();
		
		try {
			AutomationUtils.Fluentwait(driver,"//div[@class='login-suspended-account error-msg-container'][@style='display: block;']", 15);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Boolean ispresent = driver
				.findElements(By.xpath("//span[contains(text(),'Your account has been suspended/closed.')]"))
				.size() > 0;
		String Atual_Error = driver
				.findElement(By.xpath("//span[contains(text(),'Your account has been suspended/closed.')]")).getText();
		String Expected_Error = ExcelUtils.getStringCellValue(row, 17);
		if (!Atual_Error.contains(Expected_Error)) {
			Assert.assertTrue(false);
		}
	}

	public void enterWrongAnswerFiveTimes(int TestNo) {
	

		XSSFRow row = ExcelUtils.getRowData("Screen1", TestNo);
		Username.sendKeys(ExcelUtils.getStringCellValue(row, 9));
	    String password1=ExcelUtils.getStringCellValue(row, 10);
		password.sendKeys(password1);
		LoginBtn.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i < 4; i++) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Password.clear();
			Password.sendKeys(password1 + " " + i);
			LoginBtn.click();
		}
	}

	public void enterLockedCredentails() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Boolean ispresent = driver.findElements(By.xpath("//span[contains(text(),'Your account has been locked.')]"))
				.size() > 0;
		String Atual_Error = driver.findElement(By.xpath("//span[contains(text(),'Your account has been locked.')]"))
				.getText();
		 XSSFRow row = ExcelUtils.getRowData("BrandsBooking1", 1);
		String Expected_Error = ExcelUtils.getStringCellValue(row, 17);
		if (!Atual_Error.contains(Expected_Error)) {
			Assert.assertTrue(false);
		}
		
	}

	public void wrongSecAnswerFiveTimes() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 1; i < 6; i++) {

			SecurityAns.clear();
			SecurityAns.sendKeys("wrongAnswer" + " " + i);
			SecuritySubmit.click();
		}

		
	}

	public void enterSameValidCredentails(int TestNo) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SininClick.click();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		SininOut.click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clickLogin();
		logintoAccount(TestNo);

//		Boolean ispresent = driver.findElements(By.xpath("//div[@style='display: block;'][@class='sec-q-prompt']"))
//				.size() > 0;
//		if (ispresent) {
//			Assert.assertTrue(false);
//		}
		
	}

	public void enterSameValidCredentailsWithDeletingCache(int TestNo) {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		driver.manage().deleteAllCookies();
//		try {
//			Thread.sleep(8000);
//		} catch (InterruptedException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		SininClick.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SininOut.click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickLogin();
		logintoAccount(TestNo);
		Boolean ispresent = driver.findElements(By.xpath("//div[@style='display: block;'][@class='sec-q-prompt']"))
				.size() > 0;
		if (!ispresent) {
			Assert.assertTrue(false);
		}
		
		
	}

	public void leavingFifteenMinIdle() {

		try {
			Thread.sleep((15*60*1000)+40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();

		String Name = driver.findElement(By.xpath("//a[@class='wr-logged-user-name']")).getText();
		System.out.println(Name);
		if (!Name.equals("")) {
			Assert.assertTrue(false);
		}
		
	}

	public void leavingFiveMinIdle() {
	try {
		Thread.sleep(5*60*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	public void checkUserLoggedOutAfterTenMin() {
		try {
			Thread.sleep(15*60*1000+45);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.navigate().refresh();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Name=driver.findElement(By.xpath("//a[@class='wr-logged-user-name']")).getText();
		if(!Name.equals("")){
			Assert.assertTrue(false);
		}
		
	}

}
