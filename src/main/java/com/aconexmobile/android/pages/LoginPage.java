package com.aconexmobile.android.pages;

import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.aconexmobile.android.helpers.Commons;
import com.aconexmobile.frame.AcxElement;

public class LoginPage {
	
	static final String OTHER_LOCATION = "apidev.aconex.com";
	static final Logger logger = Logger.getLogger(LoginPage.class);
	
	// Login Elements
	AcxElement txtUsername = new AcxElement("id::com.aconex.aconexmobileandroid:id/login_activity_et_user_name");
	AcxElement txtPassword = new AcxElement("id::com.aconex.aconexmobileandroid:id/login_activity_et_pass");
	AcxElement selectLocation = new AcxElement("id::com.aconex.aconexmobileandroid:id/login_activity_tv_select_location");
	AcxElement btnLogin = new AcxElement("id::com.aconex.aconexmobileandroid:id/login_activity_btn_login");
	
	// Other Location
	AcxElement txtLocation = new AcxElement("classname::android.widget.EditText");
	AcxElement btnLocationCancel = new AcxElement("id::android:id/button2");
	AcxElement btnLocationOk = new AcxElement("id::android:id/button1");
	
	// dialogs
	AcxElement lblTitle = new AcxElement("id::android:id/alertTitle");
	AcxElement lblMessage = new AcxElement("id::android:id/message");
	AcxElement btnOk = new AcxElement("xpath:://*[@text='OK']");
	
	public void waitForViewToLoad(){
		btnLogin.waitForElementToBeVisible();
	}
	
	public void enterValidCredentials(String username, String password){
		waitForViewToLoad();
		txtUsername.sendKeys(username);
		txtPassword.sendKeys(password);
	}

	public void clickLogin(){
		btnLogin.click();
	}
	
	public void setLocation(String location){
		selectLocation.click();
		
		if (location.toLowerCase().contains("other")){
			new AcxElement("xpath:://*[@text='Other']").click();
			txtLocation.waitForElementToBeVisible();
			txtLocation.sendKeys(OTHER_LOCATION);
			btnLocationOk.click();
		}else {
			Commons.selectItem(selectLocation,location);
		}
	}
	
	public void login(String username, String password, String location){
		enterValidCredentials(username, password);
		setLocation(location);
		clickLogin();
		logger.info("Logging in as - "+username);
	}
	
	// Assertions
	
	public void assertSelectLocationError(){
		String title = "Aconex";
		String message = "Please select location.";
		String ok = "OK";
		
		Assert.assertEquals(title, lblTitle.getText());
		Assert.assertEquals(message, lblMessage.getText());
		Assert.assertEquals(ok, btnOk.getText());
		
	}

	public void assertLoginNameError(){
		String title = "Aconex";
		String message = "Please enter LoginName and Password.";
		String ok = "OK";
		
		Assert.assertEquals(title, lblTitle.getText());
		Assert.assertEquals(message, lblMessage.getText());
		Assert.assertEquals(ok, btnOk.getText());
	}
	
	public void assertUnableToConnectError(){
		String title = "Aconex";
		String message = "Unable to connect to server. Please try again.";
		String ok = "OK";
		
		Assert.assertEquals(title, lblTitle.getText());
		Assert.assertEquals(message, lblMessage.getText());
		Assert.assertEquals(ok, btnOk.getText());
	}
}

