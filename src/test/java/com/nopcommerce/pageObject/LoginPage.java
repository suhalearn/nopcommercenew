package com.nopcommerce.pageObject;


	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.CacheLookup;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class LoginPage {

		public WebDriver driver;

		public LoginPage(WebDriver driver) { //This driver in this constructor is assigned to local driver(WebDriver driver)
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(id = "Email") //CacheLook improves the performance of locating elements, automation scripts which is optional(not mandatory statement)
		@CacheLookup
		WebElement txtEmail;

		@FindBy(id = "Password")
		@CacheLookup
		WebElement txtPassword;

		@FindBy(xpath = "//input[@value='Log in']")
		@CacheLookup
		WebElement btnLogin;

		@FindBy(linkText = "Logout")
		@CacheLookup
		WebElement lnkLogout;

		public void setUserName(String uname) {
			txtEmail.clear();
			txtEmail.sendKeys(uname);
		}

		public void setPassword(String pwd) {

			txtPassword.clear();
			txtPassword.sendKeys(pwd);
		}

		public void clickLogin() {

			btnLogin.click();
		}

		public void clickLogout() {

			lnkLogout.click();
		}

	}

