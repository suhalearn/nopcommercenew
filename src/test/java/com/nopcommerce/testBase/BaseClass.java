package com.nopcommerce.testBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public Properties configPropObj;
	public WebDriver driver;
	
	public Logger logger=LogManager.getLogger("nopcommerce"); // Log4j2

	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws IOException {

				// Load config.properties file
		configPropObj = new Properties();
		FileInputStream configfile = new FileInputStream(".\\resources\\config.properties");
		configPropObj.load(configfile);
		// end of loading config.properties file
		
		//Opens browser
		if(br.equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		}
		
		else if(br.equals("ie"))
		{   //WebDriverManager.ie.driver().setup(); // Not working
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ "\\drivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		}
		
		else if(br.equals("edge"))
		{   //WebDriverManager.edgedriver().setup(); // Not working
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+ "\\drivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		}
		
		//Global implicit Wait
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
	    }

	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public int randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(generatedString2));
	}
}