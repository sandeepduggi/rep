package com.wr.automation.rewards;

import org.testng.annotations.Test;

import com.wr.automation.rewards.pages.WrSiteMap;
import com.wyn.automation.base.BasePageTest;

public class SiteMap extends BasePageTest{
	
	@Test
	public void siteMapLinksVerify() throws Exception{
		test = extent.startTest("siteMapLinksVerify");
		WrSiteMap wrSiteMap=new WrSiteMap().openPage(WrSiteMap.class);
		super.captureScreenShot("1.Validate the links in SiteMap");
		wrSiteMap.linkNav();
	}

}
