package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_LoginTest_001 extends BaseClass{
	  
 
  @Test
  public void loginTest() throws IOException
  {
	  logger.info("******Starting TC_LoginTest_001**********");
	  driver.get(configPropObj.getProperty("baseURL"));
	  
	  
	  logger.info("*********Providing login details**************");
	  LoginPage lp=new LoginPage(driver);
	  lp.setUserName(configPropObj.getProperty("useremail"));
	  lp.setPassword(configPropObj.getProperty("password"));
	  lp.clickLogin();
	  
	  logger.info("*********Validating Login**************");
	  String exp_title="Dashboard / nopCommerce administration";
	  String act_title=driver.getTitle();
	  
	  if(exp_title.equals(act_title)) //Validation
	  {
		  logger.info("******* Login is successful************");
		  Assert.assertTrue(true);
	  }
	  else
	  {
		  logger.error("******* Login is un-successful************");
		  captureScreen(driver,"loginTest");
				  
		  Assert.assertTrue(false);
	  }
	  
  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}