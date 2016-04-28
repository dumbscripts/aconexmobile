package com.aconexmobile.android.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.log4testng.Logger;

import com.aconexmobile.android.helpers.Commons;
import com.aconexmobile.frame.AcxElement;

public class DocumentsPage {

	static final Logger logger = Logger.getLogger(DocumentsPage.class);
	
	AcxElement btnSelect = new AcxElement("id::com.aconex.aconexmobileandroid:id/docs_list_fragment_btn_edit");
	AcxElement txtSearch = new AcxElement("id::com.aconex.aconexmobileandroid:id/docs_list_fragment_et_super_search");
	AcxElement btnRefine = new AcxElement("id::com.aconex.aconexmobileandroid:id/docs_list_fragment_tv_refine_search");
	AcxElement listDocumentNo = new AcxElement("id::com.aconex.aconexmobileandroid:id/row_doc_list_tv_doc_no");
	
	//Refine Locators
	AcxElement txtDocumentNo = new AcxElement("id::com.aconex.aconexmobileandroid:id/docs_option_dialog_fragment_et_doc_no");
	AcxElement Title = new AcxElement("id::com.aconex.aconexmobileandroid:id/docs_option_dialog_fragment_et_title");
	AcxElement btnSearch = new AcxElement("id::com.aconex.aconexmobileandroid:id/docs_option_dialog_fragment_btn_search");
	
	public DocumentsPage waitForViewToLoad(){
		btnRefine.waitForElementToBeVisible();
		return this;
	}
	
	public void searchForDocument(String docNo){
		btnRefine.click();
		txtDocumentNo.sendKeys(docNo);
		btnSearch.click();
	}

	public boolean isDocumentAvailable(String doc){
		List<WebElement> listDocs = Commons.getListItems(listDocumentNo);
		for(WebElement ele : listDocs){
			if (ele.getText().equals(doc)) return true;	
		}
		return false;
	}
	
	
	
}
