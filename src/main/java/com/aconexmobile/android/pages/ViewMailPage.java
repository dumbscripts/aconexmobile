package com.aconexmobile.android.pages;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.aconexmobile.frame.AcxElement;

public class ViewMailPage {

	static final Logger logger = Logger.getLogger(ViewMailPage.class);

	AcxElement lblTitle = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_mail_result");
	AcxElement btnReply = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_imgbtn_rply");
	AcxElement btnMarkAsUnread = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_imgbtn_unread");
	AcxElement btnFavorite = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_imgbtn_favorite");
	AcxElement btnNewMail = new AcxElement(
			"id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_imgbtn_compose_mail");

	AcxElement lblMailType = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_mail_type");
	AcxElement lblSubject = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_subject");
	AcxElement lblMailNo = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_mail_no");
	AcxElement btnMailThread = new AcxElement(
			"id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_view_thread");

	AcxElement lblFrom = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_from");
	AcxElement lblTo = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_to");
	AcxElement lblSentDate = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_sent_date");
	AcxElement lblRespondBy = new AcxElement(
			"id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_response_by");
	AcxElement lblStatus = new AcxElement("id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_status");

	// Attributes
	AcxElement lblAttribute2 = new AcxElement(
			"id::com.aconex.aconexmobileandroid:id/mail_detail_fragment_tv_arritubute_2");

	public ViewMailPage waitForPagetoLoad(){
		btnMarkAsUnread.waitForElementToBeVisible();
		return this;
	}
	
	public void verifyRecievedMail(Map<String, String> params) {
		logger.info("Verifying recieved mail");
		// Sometimes app takes more than 40 sec to open the mail, Need to find the root cause.
		lblMailType.waitForElementToBeVisible(60);
		
		for (Entry<String, String> item : params.entrySet()) {
			switch (item.getKey().toLowerCase()) {
			case "mail types":
				if (lblMailType.getText().equals(item.getValue())) {
					logger.info("Mail Types match");
				} else {
					logger.error("Mail Types don't match, (Excepted - "+item.getValue()+"), (Actual - "+lblMailType.getText()+")");
				}
				break;
			case "subject":
				if (lblSubject.getText().equals(NewMailPage.getMailSubject())) {
					logger.info("Subjects match");
				} else {
					throw new RuntimeException("Subjects don't match, (Excepted - "+item.getValue()+"), (Actual - "+lblSubject.getText()+")");
				}
				break;
			case "to":
				if (lblTo.getText().contains(item.getValue())) {
					logger.info("To address's match,");
				} else {
					throw new RuntimeException("To address's don't match, (Excepted - "+item.getValue()+"), (Actual - "+lblTo.getText()+")");
				}
				break;
			case "attribute 2":
				if (lblAttribute2.getText().equals(item.getValue())) {
					logger.info("Attribute2 match");
				} else {
					throw new RuntimeException("Attribute2 don't match, (Excepted - "+item.getValue()+"), (Actual - "+lblAttribute2.getText()+")");
				}
				break;
			case "status":
				if (lblStatus.getText().equals(item.getValue())) {
					logger.info("Status match");
				} else {
					throw new RuntimeException("Status don't match, (Excepted - "+item.getValue()+"), (Actual - "+lblStatus.getText()+")");
				}
				break;
			}
		}
	}

}
