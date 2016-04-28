package com.aconexmobile.android.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.aconexmobile.android.helpers.Commons;
import com.aconexmobile.frame.AcxElement;

public class ProjectDirectory {

	AcxElement selectProjectDir = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_simple_spinner_item_tv_title");
	AcxElement btnDone = new AcxElement("id::com.aconex.aconexmobileandroid:id/directory_list_fragment_btn_done");
	AcxElement txtSearch = new AcxElement("id::com.aconex.aconexmobileandroid:id/directory_list_fragment_et_super_search");
	AcxElement btnClearSearch = new AcxElement("id::com.aconex.aconexmobileandroid:id/directory_list_fragment_ibtn_search_clear");
	
	AcxElement listUsers = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_directory_list_tv_contact_name");
	public AcxElement btnTo = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_directory_list_btn_to");
	public AcxElement btnCc = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_directory_list_btn_cc");
	public AcxElement btnBcc = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_directory_list_btn_bcc");
			
	public static ProjectDirectory getInstance(){
		return new ProjectDirectory();
	}
	
	public ProjectDirectory waitForViewToLoad(){
		txtSearch.waitForElementToBeVisible();
		return this;
	}
	
	
	public void AddUserTo(String field, String user){
		List<WebElement> list = new ArrayList<>();
		txtSearch.sendKeys(user);
		//List<WebElement> userList = listUsers.findElements();
		//if (userList.isEmpty()){
			//throw new RuntimeException("Couldn't find the searched user - "+user);
		//}
		List<WebElement> userList = Commons.getListItems(listUsers);
		
		for(int i = 0; i< userList.size(); i++){
			if (userList.get(i).getText().trim().equals(user)){
				switch(field){
				case "to":
					list = btnTo.findElements();
					break;
				case "cc":
					list = btnCc.findElements();
					break;
				case "bcc":
					list = btnBcc.findElements();
					break;
				}
				list.get(i).click();
			}else{
				throw new RuntimeException("Couldn't find user - "+user);
			}
		}
		clickDone();
	}

	public void clickDone(){
		btnDone.click();
	}

}
