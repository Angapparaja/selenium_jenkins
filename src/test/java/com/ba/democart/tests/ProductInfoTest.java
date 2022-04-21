package com.ba.democart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ba.democart.utils.Constants;
import com.ba.democart.utils.ExcelUtil;

public class ProductInfoTest extends BastTest{

	
	@BeforeClass
	public void productInfoPageSetUp() {
		accPage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void productImagesTest() {
		resultsPage =accPage.doSearch("iMac");
		productInfoPage =resultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImagesCount(), 3);
	}
	@DataProvider
	public Object[][] getProductMetaData(){
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_META);
	}
	
	@Test(priority=2,dataProvider ="getProductMetaData")
	public void productInfoTest(String productName ,String mainProductName,String Name,String Brand,String ProductCode,String Price) {
		resultsPage = accPage.doSearch(productName);
		productInfoPage =resultsPage.selectProduct(mainProductName);
		Map<String,String> accProductInfoMap =  productInfoPage.getProductInfo();
		
		Assert.assertEquals(accProductInfoMap.get("name"), Name);
		Assert.assertEquals(accProductInfoMap.get("Brand"), Brand);
		Assert.assertEquals(accProductInfoMap.get("Product Code"), ProductCode);
		Assert.assertEquals(accProductInfoMap.get("price"), Price);
		
	}
}
