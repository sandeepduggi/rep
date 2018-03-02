package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.WrHowItWork;
import com.wyn.automation.base.BasePageTest;

public class HowItWork extends BasePageTest{
	@Test
	public void howItWork() throws Exception{
		test = extent.startTest("howItWork");
		WrHowItWork wrHowItWork=new WrHowItWork().openPage(WrHowItWork.class);
		super.captureScreenShot("1.Validate components in HowItWorkPage");
		wrHowItWork.validateComponents();
	}

}
