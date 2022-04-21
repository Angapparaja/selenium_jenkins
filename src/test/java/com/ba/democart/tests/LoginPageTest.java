package com.ba.democart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ba.democart.pages.AccountsPage;
import com.ba.democart.utils.Constants;
import com.ba.democart.utils.Errors;
import com.ba.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC 100:Design Login page for opencart application....")
@Story("US 101: Login page with different features...")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BastTest{

	@Description("login page title test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=1)
	public void loginpageTitleTest() throws InterruptedException {
		String pageTitle =loginPage.getLoginPageTitle();
		System.out.println("the loginpage title is"+pageTitle);
		Assert.assertEquals(pageTitle,Constants.LOGIN_PAGE_TITLE,Errors.TITLE_ERROR_MESSAGE);
	}
	
	@Description("login page Header test...")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority=2)
	public void loginPageHeaderTest() {
		String header =loginPage.getPageHeaderText();
		System.out.println("lp header is :" +header);
		Assert.assertEquals(header,Constants.PAGE_HEADERTEXT,Errors.HEADER_ERROR_MESSAGE);
	}
	
	@Description("forgotpassword link test...")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void forgotPwdLinkText() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("loginpage test...")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority=4)
	public void loginTest() {
			AccountsPage accPage= loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
			Assert.assertTrue(accPage.isLogoutLinkExist());
			}
}
