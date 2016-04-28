package com.aconexmobile.android.pages;

import org.testng.log4testng.Logger;

import com.aconexmobile.frame.AcxElement;

public class BottomNavigationBar {
	
	static final Logger logger = Logger.getLogger(BottomNavigationBar.class);
	
	//Bottom Navigation bar
		AcxElement btnTasks = new AcxElement("id::com.aconex.aconexmobileandroid:id/menu_task");
		AcxElement btnMail = new AcxElement("id::com.aconex.aconexmobileandroid:id/menu_mail");
		AcxElement btnDocuments = new AcxElement("id::com.aconex.aconexmobileandroid:id/menu_docs");
		AcxElement btnCamera = new AcxElement("id::com.aconex.aconexmobileandroid:id/menu_capture");
		AcxElement btnUsers = new AcxElement("id::com.aconex.aconexmobileandroid:id/menu_directory");
		AcxElement btnSettings = new AcxElement("xpath:://android.widget.ImageButton[@content-desc='More options']");	
		//Settings Options
		AcxElement btnFavorites = new AcxElement("xpath:://*[@text='Favorites']");
		AcxElement btnSettings_Settings = new AcxElement("xpath:://*[@text='Settings']");
		AcxElement btnMap = new AcxElement("xpath:://*[@text='Map']");
		AcxElement btnSync = new AcxElement("xpath:://*[@text='Sync']");
		AcxElement btnLogout = new AcxElement("xpath:://*[@text='Logout']");
		
		public BottomNavigationBar waitForViewToLoad(){
			btnSettings.waitForElementToBeClickable();
			return this;
		}
		
		public void navigateToTasks(){
			btnTasks.click();
			logger.info("Navigating to Tasks page");
		}
		
		public void navigateToMails(){
			btnMail.click();
			logger.info("Navigating to mails page");
		}
		
		public void navigateToDocuments(){
			btnDocuments.click();
			logger.info("Navigating to Documents page");
		}
		
		public void navigateToCamera(){
			btnCamera.click();
			logger.info("Navigating to Camera page");
		}
		
		public void navigateToUsers(){
			btnUsers.click();
		}
		
		public BottomNavigationBar navigateToSettings(){
			btnSettings.click();
			return this;
		}
		
		public void clickFavorites(){
			btnFavorites.click();
		}
		
		public void clickSettings(){
			btnSettings_Settings.click();
		}
		
		public void clickMap(){
			btnMap.click();
		}
		
		public void clickSync(){
			btnSync.click();
		}
	
		public void clickLogout(){
			btnLogout.click();
		}
}
