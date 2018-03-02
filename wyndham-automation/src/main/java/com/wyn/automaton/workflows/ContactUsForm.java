package com.wyn.automaton.workflows;

import org.testng.annotations.Test;

import com.wyn.automation.base.BasePageTest;
import com.wyn.automation.pages.WyndhamHomePage;
import com.wyn.automation.pages.ContactUs;
public class ContactUsForm extends BasePageTest{

	@Test
	public void ContactUs() throws Exception {
		test = extent.startTest("ContactUs");
		int rowNumber = 1;
		ContactUs.Brand="Ramada";
		WyndhamHomePage wyndhamhomepage = new WyndhamHomePage().openPage(WyndhamHomePage.class);
		super.captureScreenShot("1.Click contactUsForm");
		wyndhamhomepage.Contactus();
		ContactUs contactUs=new ContactUs().openPage(ContactUs.class);
		super.captureScreenShot("2.Enter General form details");
		contactUs.General_form("General contact message",rowNumber);
		super.captureScreenShot("3.Enter Best Rate Claim form details");
		contactUs.BestRate_Forms("Best Rate Claim", rowNumber);
		super.captureScreenShot("4.Enter CorporateAccount Request form details");
		contactUs.Coorporate_Forms("Corporate Account Request", rowNumber);
	}

}
