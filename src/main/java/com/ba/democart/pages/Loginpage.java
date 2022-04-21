package com.ba.democart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ba.democart.utils.Constants;
import com.ba.democart.utils.ElementUtil;

import io.qameta.allure.Step;

public class Loginpage {

	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//private By locators
	private By emailId =By.id("input-email");
	private By password =By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	
	private By forgotPwdLink =By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	
	private By header =By.cssSelector("div#logo h1 a");
	
	
	private By registerLink =By.linkText("Register");
	
	//constructor:
	public Loginpage(WebDriver driver) {
		this.driver=driver; //this line aa remove panna nullpointer exception varum
		elementUtil =new ElementUtil(driver);
//		elementUtil =new ElementUtil(this);
	}
	
	//page actions  /page methods /functionality/behaviour /no assertion
	
	@Step("getting login page title")
	public String getLoginPageTitle() throws InterruptedException {
		Thread.sleep(10000);
		return elementUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, 5);

	}
	@Step("getting login page header text")
	public String getPageHeaderText() {
		return elementUtil.doGetText(header);
	}
	
	@Step("checking forgotpassword link is displayed on loginpage....")
	public boolean isForgotPwdLinkExist() {
		return elementUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("login to application with username {0} and password {1}")
	public AccountsPage doLogin(String un,String pwd) {   //page chaining method
//		driver.findElement(emailId).sendKeys(un);
elementUtil.doSendKeys(emailId, un);	
elementUtil.doSendKeys(password, pwd);
elementUtil.doClick(loginBtn);
return new AccountsPage(driver);
	}
	@Step("navigating to registration page...")
	public RegistrationPage navigateToRegisterPage() {
		elementUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
}
