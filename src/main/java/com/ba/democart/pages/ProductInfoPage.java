package com.ba.democart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ba.democart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	private By productHeader  =By.cssSelector("div#content h1");
	private By productImages =By.cssSelector("ul.thumbnails img");
	private By productMetaData =By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData =By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity =By.id("input-quanity");
	private By  addToCartBtn=By.id("button-cart");
	
	private Map<String,String> productInfoMap ;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public String getProductHeaderText() {
		return elementUtil.doGetText(productHeader);
	}
	
	public int getProductImagesCount() {
		return elementUtil.getElements(productImages).size();
	}
	
	public Map<String,String> getProductInfo() {  //hashmap is useful for assertion ,, example) key aa pass panna value get panniakalam
		productInfoMap =new HashMap<String,String>();
		productInfoMap.put("name",getProductHeaderText()); //key and value pair but key are own key
		
		
		List<WebElement>metaDataList =elementUtil.getElements(productMetaData);
		System.out.println("total product meta data list :" + metaDataList.size());
		
		for(WebElement e: metaDataList) {
			String meta[] =e.getText().split(":");
			String metaKey =meta[0].trim();  //meta[0] is a key
			String metaValue =meta[1].trim();//meta[1] is a index value
			productInfoMap.put(metaKey, metaValue);
		}
		
		
		//pricedata:
		List<WebElement> PriceList =elementUtil.getElements(productPriceData);
		System.out.println("total product Price list :" + PriceList.size());
		
		String price =PriceList.get(0).getText().trim();  //FIRST index value
		String exTaxPrice =PriceList.get(1).getText().trim(); //second index value
		
		productInfoMap.put("price", price);//key and value pair but key are own key
		productInfoMap.put("ExTaxPrice", exTaxPrice);
		
		return productInfoMap;
	}
	
}
