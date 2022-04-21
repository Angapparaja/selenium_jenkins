package com.ba.democart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ba.democart.utils.Constants;
import com.ba.democart.utils.ExcelUtil;

public class RegistrationPageTest extends BastTest {

	@BeforeClass
	public void regSetup() {
		regPage=loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmail() {   //getrandom number is using elementUtil and email aa automatic aa generate panum
		Random random =new Random(); // this is available in java class
		String email ="testautomation"+random.nextInt(5000)+"@gmail.com";  // it generate 0-5000 randomnumber
		System.out.println(email);
		return email;
	}
	
//	@DataProvider
//	public Object[][] getRegTestData(){  //hardcode value aa remove panna DATA PROVIDER use pannuvanga
//		return new Object[][] {
//			{"ravisanjeevi", "kumar", "9876373799","ravik@123","yes"},  //it returns 2 column first one is main search and expected column
//			{"mathumari", "Mani", "9876373899","mathuk@123","yes"},
//			{"arivurani", "vinoth", "9876373789","arivuk@123","yes"}
//		};
//	}
	
	@DataProvider
	public Object[][] getRegTestData(){
		return ExcelUtil.getTestData(Constants.REGISTERS_SHEET_NAME);
	}
	
	@Test(dataProvider ="getRegTestData")
	public void registrationTest(String firstName,String lastName,String telephone,String password,String subscribe) {
		Assert.assertTrue(regPage.accountRegistration(firstName,lastName,getRandomEmail(),telephone,password,subscribe));
	}
}
