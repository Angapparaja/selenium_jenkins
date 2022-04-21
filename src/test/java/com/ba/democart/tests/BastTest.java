package com.ba.democart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ba.democart.factory.DriverFactory;
import com.ba.democart.pages.AccountsPage;
import com.ba.democart.pages.Loginpage;
import com.ba.democart.pages.ProductInfoPage;
import com.ba.democart.pages.RegistrationPage;
import com.ba.democart.pages.ResultsPage;

public class BastTest {

	WebDriver driver;
	Properties prop;
	DriverFactory df;
	
	Loginpage loginPage;
	
	AccountsPage accPage;
	ResultsPage resultsPage;
	
	ProductInfoPage productInfoPage;
	RegistrationPage regPage;
	
	@BeforeTest
	public void setup() {
		
		df =new DriverFactory();
		prop = df.initProperties();//it return prop
		driver = df.initDriver(prop);
		loginPage =new Loginpage(driver);
	}
	
	@AfterTest
	public void tearDown() {
//		driver.quit();
	}
	
}
