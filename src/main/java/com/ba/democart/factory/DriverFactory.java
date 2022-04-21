package com.ba.democart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public static String highlight;
	private OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used initilize the driver
	 * @param browserName
	 * @return
	 */
//	public WebDriver initDriver(String browserName) { //change the parameter name is prop because prop la eruthu thaa config file aa get pannurom
	
	public WebDriver initDriver(Properties prop) {  //prop browser and highlight and url ku use aagum  prop reference aa use pannala na oru oru time um browsername ,url,highlight nu nu parameter aa pass pannura maaari erukum
			
        String browserName = prop.getProperty("browser");
        highlight =prop.getProperty("highlight"); //config file la erukura highlight property aa get pannurom
        
		System.out.println("browser name is:"+browserName);
		
		optionsManager =new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver =new ChromeDriver(optionsManager.getChromeOptions()); //optionsmanager initialize the chromedriver la pass
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver =new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else {
			System.out.println("please pass the right browserName" +browserName);
		}
		
//		driver.manage().deleteAllCookies();
//		driver.manage().window().maximize();
//		
//		driver.get(prop.getProperty("url"));
//		
//		return driver;
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	public WebDriver getDriver() {
		return tlDriver.get();
	}
	
	/**
	 * This method is used to initialize the properties on the basis of given env name
	 * @return
	 */
	public Properties initProperties() {
		
		Properties prop =null;
		FileInputStream ip=null;
		
		String env =System.getProperty("env");//mvn clean install //command line arguments
		
		try {
		if(env==null) {
			System.out.println("Running on Environment:PROD env...");
			ip = new FileInputStream("./src/test/resources/config/config.properties");
			
		}
		else {
			System.out.println("Running On Environment "+ env);
			switch(env) {
			case "ba":
				ip = new FileInputStream("./src/test/resources/config/ba.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
			break;	
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
			break;
			default:
				System.out.println("No env found....");
				throw new Exception("NOENVFOUNDEXCEPTION");
					
			}
		}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		
			prop =new Properties();
			prop.load(ip);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return prop;
		
	}
	
	/**
	 * TAKE A SCREENSHOT
	 */
	public String getScreenshot() {
		File srcFile =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path= System.getProperty("user.dir")+"/screenshot/"+ System.currentTimeMillis()+".png";
		
		File destination =new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
}
