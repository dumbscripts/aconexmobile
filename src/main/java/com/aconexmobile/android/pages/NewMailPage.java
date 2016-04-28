package com.aconexmobile.android.pages;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.aconexmobile.android.helpers.Commons;
import com.aconexmobile.frame.AcxElement;
import com.aconexmobile.frame.Utils;

import junit.framework.Assert;

public class NewMailPage {
	
	static final Logger logger = Logger.getLogger(NewMailPage.class);
	public static String mailSubject;
	
	ProjectDirectory projectDirectory = ProjectDirectory.getInstance();
	
	AcxElement btnConfidential = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_imgbtn_confidetntial");
	AcxElement btnClip = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_imgbtn_attachment");
	AcxElement btnCamera = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_imgbtn_media");
	AcxElement btnSend = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_imgbtn_send");
	
	//Mail parameters locators
	AcxElement selectMailTypes = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_spnr_type");
	AcxElement txtTo = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_et_to");
	AcxElement txtCc = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_et_cc");
	AcxElement txtBcc = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_et_bcc");
	AcxElement btnProjectDir = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_ibtn_user_directory_to");
	AcxElement selectResponseRequired = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_spnr_response_required");
	AcxElement btnDate = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_imgbtn_calendar");
	AcxElement txtSubject = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_et_subject");

			//Attributes
	AcxElement selectAttribute1 = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_spnr_attribute1");
	AcxElement selectAttribute2 = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_spnr_attribute2");
	AcxElement selectAttribute3 = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_spnr_attribute3");
	AcxElement txtMessage = new AcxElement("id::com.aconex.aconexmobileandroid:id/new_mail_et_message");

	// dialogs
		AcxElement lblTitle = new AcxElement("id::android:id/alertTitle");
		AcxElement lblMessage = new AcxElement("id::android:id/message");
		AcxElement btnOk = new AcxElement("xpath:://*[@text='OK']");
	
	public void waitForViewToLoad(){
		selectMailTypes.waitForElementToBeVisible();
	}
	
	public void enterMailParams(Map<String, String> params){
		logger.info("Setting Email params");
		waitForViewToLoad();
		Iterator<Entry<String, String>> items = params.entrySet().iterator();
		while(items.hasNext()){
			Map.Entry<String, String> item = items.next();
			switch(item.getKey().toLowerCase()){
			case "mail types":
				logger.info("Setting mail type");
				Commons.selectItem(selectMailTypes, item.getValue());
				break;
			case "to":
				logger.info("Setting to address");
				btnProjectDir.click();
				projectDirectory.AddUserTo("to", item.getValue());
				break;
			case "cc":
				logger.info("Setting cc address");
				btnProjectDir.click();
				projectDirectory.AddUserTo("cc", item.getValue());
				break;
			case "response required":
				logger.info("Setting response required");
				Commons.selectItem(selectResponseRequired, item.getValue());
				break;
			case "date":
				logger.info("Setting date");
				throw new RuntimeException("Not implemented!");
			case "subject":
				logger.info("Setting subject");
				mailSubject = item.getValue()+"_"+Utils.getRandom();
				txtSubject.sendKeys(mailSubject);
				break;
			case "attribute 1":
				logger.info("Setting attribute 1");
				Commons.selectItem(selectAttribute1, item.getValue());
				break;
			case "attribute 2":
				logger.info("Setting attribute 2");
				Commons.selectItem(selectAttribute2, item.getValue());
				break;
			case "attribute 3":
				logger.info("Setting attribute 3");
				Commons.selectItem(selectAttribute3, item.getValue());
				break;
			case "message":
				logger.info("Setting message");
				txtMessage.sendKeys(item.getValue());
				break;
			}
		}
	}
	
	public static String getMailSubject(){
		return mailSubject;
	}
	
	public void clickSend(){
		btnSend.click();
	}
	
	public void assertMailSent(){
		String TITLE = "Aconex"; String MESSAGE = "Mail sent."; String OK = "OK";
		Assert.assertEquals(TITLE, lblTitle.getText());
		Assert.assertEquals(MESSAGE, lblMessage.getText());
		Assert.assertEquals(OK, btnOk.getText());
	}
	
	public void clickOkOnDialog(){
		btnOk.click();
	}
}
