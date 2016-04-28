package com.aconexmobile.android.pages;

import org.apache.log4j.Logger;

import com.aconexmobile.android.helpers.Commons;
import com.aconexmobile.frame.AcxElement;

public class TaskPage {
	
	static final Logger logger = Logger.getLogger(TaskPage.class); 
	
	AcxElement lblLoading = new AcxElement("id::android:id/body");
	static AcxElement listProjects = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_project_list_tv_project");
	AcxElement btnNewMail = new AcxElement("id::com.aconex.aconexmobileandroid:id/tasks_options_fragment_ib_new_mail");
	//Mail locators
	AcxElement btnMailUnread = new AcxElement("id::com.aconex.aconexmobileandroid:id/tasks_options_fragment_tv_mail_unread");
	AcxElement btnMailOutstanding = new AcxElement("id::com.aconex.aconexmobileandroid:id/tasks_options_fragment_tv_mail_unread");
	AcxElement btnOverdue = new AcxElement("id::com.aconex.aconexmobileandroid:id/tasks_options_fragment_tv_mail_overdue");
	// Transmittal locators
	AcxElement btnUnreadTransmittal = new AcxElement("id::com.aconex.aconexmobileandroid:id/tasks_options_fragment_tv_register_unread");
	AcxElement btnOutstandingTransmittals = new AcxElement("id::com.aconex.aconexmobileandroid:id/tasks_options_fragment_txt_register_outstanding");
	AcxElement btnOverdueTransmittals = new AcxElement("id::com.aconex.aconexmobileandroid:id/tasks_options_fragment_tv_register_overdue");
	
	
	public TaskPage waitForPagetoLoad(){
		btnNewMail.waitForElementToBeVisible();
		return this;
	} 
	
	public static void clickProjects(){
		listProjects.click();
	}
	
	public static String getProject(){
		return listProjects.getText().trim();
	}
	
	public void clickNewMail(){
		btnNewMail.click();
	}
		
	public void clickUnreadMail(){
		btnMailUnread.click();
	}
	
	public void switchProject(String project){
		if (getProject().equals(project)){
			logger.info("Project already set - No action taken");
		}else{
			Commons.selectItem(listProjects, project);
			logger.info("Selected project - "+project);
		}	
	}
}
