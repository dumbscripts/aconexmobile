package com.aconexmobile.android.helpers;


import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.aconexmobile.frame.AcxElement;

public class Commons {
	
	static final Logger logger = Logger.getLogger(Commons.class);
	
	static AcxElement btnClear = new AcxElement("id::com.aconex.aconexmobileandroid:id/common_list_btn_cancel");
	static AcxElement btnDone = new AcxElement("id::com.aconex.aconexmobileandroid:id/common_list_btn_done"); 
	
	public static void selectItem(AcxElement ele, String item){
		ele.click();
		new AcxElement("xpath:://*[@text=\""+item+"\"]").click();
		// Handle case for select dialogs, which has a Done button
		try{
			if(btnDone.isElementPresent()){
				btnDone.click();
		}}catch(NoSuchElementException ex){
				logger.info("Couldn't find done button - Moving on");
		}
	}

	public static void clickClear(){
		btnClear.click();
	}
	
	public static List<WebElement> getListItems(AcxElement ele){
		List<WebElement> items = ele.findElements();  
		if (items.isEmpty()){
			throw new RuntimeException("List is empty!");
		}
		return items;
	}
	
}
