package com.aconexmobile.android.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.aconexmobile.android.helpers.Commons;
import com.aconexmobile.frame.AcxElement;

public class MailInboxPage {

	static final Logger logger = Logger.getLogger(MailInboxPage.class);
	AcxElement listSubject = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_mail_list_tv_mail_subject");
	
	public MailInboxPage waitForViewToLoad(){
		listSubject.waitForElementToBeVisible();
		return this;
	}
	
	public void openMail(String mailNo){
	
		List<WebElement> inboxItems = Commons.getListItems(listSubject); 
		for(int i = 0; i< inboxItems.size(); i++){
			if (inboxItems.get(i).getText().contains(mailNo)){
				logger.info("Found mail - "+mailNo+", opening mail");
				inboxItems.get(i).click();
				break;
			}else{
				logger.error("Couldn't find mail - "+mailNo+" in the Inbox");
			}
		}
	}
	
}
