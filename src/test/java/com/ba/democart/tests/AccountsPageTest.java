package com.ba.democart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ba.democart.utils.Constants;
import com.ba.democart.utils.ExcelUtil;
import com.ba.opencart.listeners.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("EPIC 101:Design Accounts page for opencart application....")
@Story("US 102: Accounts page with different features...")
@Listeners(TestAllureListener.class)
public class AccountsPageTest extends BastTest {

	@BeforeClass
	public void accPageSetup() { // this class only used for accountspage Test and inherits the login method
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
    
	@Description("AccountpageTitleTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =1)
	public void accPageTitleTest() {
		String title = accPage.getAccPageTitle();
		System.out.println("acc page title is" + title);
		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Description("AccountpageHeaderTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =2)
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		System.out.println("acc page Header is" + header);
		Assert.assertEquals(header, Constants.PAGE_HEADERTEXT);
	}

	@Description("accSectionListTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =3)
	public void accSectionListTest() {
		List<String> actAccSecList = accPage.getAccountSectionList();
		System.out.println("actual section :" + actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACC_SEC_LIST);

	}
	@Description("logoutLinkExistTest")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =4)
	public void logoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist()); 
	}
	
	@DataProvider
	public Object[][] getSearchData() { //object[][] is 2 deminsion object array
		return new Object[][] {
			{"Macbook Pro"},   //each row has seperate curly braces
			{"Macbook Air"},
			{"Apple"}
			};   //it is 3 row and 1 column 
	}
	
	@Description("searchproductTest with {0}")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority =5,dataProvider ="getSearchData")
	public void searchTest(String productName) {  //String productName is holding parameter
		resultsPage =accPage.doSearch(productName);
		String resultsHeader = resultsPage.getSearchPageHeader();
		System.out.println("result header is :"+resultsHeader);
		Assert.assertTrue(resultsHeader.contains(productName));//data va  hardcode la koodukka koodathu athuku thaa dataprovider aa use pannaum
	}
	
//	@DataProvider
//	public Object[][] getProductSelectData(){  //hardcode value aa remove panna DATA PROVIDER use pannuvanga
//		return new Object[][] {
//			{"Mac Book","MacBook Air"},  //it returns 2 column first one is main search and expected column
//			{"Mac Book","MacBook Pro"},
//			{"Apple","Apple Cinema 30\""}
//		};
//	}
	
	@DataProvider
	public Object[][] getProductSelectData(){
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}
	@Description("selectProductTest with productname:{0} and mainproductname:{1}")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=6,dataProvider ="getProductSelectData")
	public void selectProductTest(String productName ,String mainProductName) {  //it is independant class 
		resultsPage =accPage.doSearch(productName);
		productInfoPage = resultsPage.selectProduct(mainProductName);
		String header =productInfoPage.getProductHeaderText();
		System.out.println("product header"+header);
		Assert.assertEquals(header, mainProductName);
	}
	
	
}
