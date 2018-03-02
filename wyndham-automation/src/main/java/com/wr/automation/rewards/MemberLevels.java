package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.WrMemberLevelPage;
import com.wyn.automation.base.BasePageTest;

public class MemberLevels extends BasePageTest{

@Test
public void memberLevelComponentsValidation() throws Exception{
	test = extent.startTest("memberLevelComponentsValidation");
	WrMemberLevelPage wrMemberLevelPage=new WrMemberLevelPage().openPage(WrMemberLevelPage.class);
	super.captureScreenShot("1.Validate components in MemberLevels page");
	wrMemberLevelPage.validateComponents();
}
}
