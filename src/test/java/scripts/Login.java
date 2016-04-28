package scripts;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aconexmobile.android.pages.BottomNavigationBar;
import com.aconexmobile.android.pages.LoginPage;
import com.aconexmobile.android.pages.MailInboxPage;
import com.aconexmobile.android.pages.NewMailPage;
import com.aconexmobile.android.pages.TaskPage;
import com.aconexmobile.android.pages.ViewMailPage;
import com.aconexmobile.frame.Driver;
import com.aconexmobile.frame.PropertiesUtil;
import com.aconexmobile.frame.Utils;

import io.appium.java_client.MobileDriver;


public class Login {

	static final String USERNAME = "jlowder";
	static final String PASSWORD = "Auth3nt1c";
	static final String LOCATION = "Other";
	static final String PROJECT = "Emerald Mine";
	
	static final String USERNAME2 = "lmiller";
	
	public MobileDriver driver;
	public LoginPage login;
	public TaskPage task;
	public NewMailPage newMail;
	public BottomNavigationBar bnavBar;
	public MailInboxPage mailInboxPage;
	public ViewMailPage viewMail;
	
	Map<String, String> params = new LinkedHashMap<String, String>();
	
	public Login(){
		params.put("Mail Types", "Variation");
		params.put("To", "Lewis Miller");
		params.put("Subject", "Automation Test");
		params.put("Attribute 2", "Design");
		params.put("Message", "Automation Test");
	}
	
	@BeforeTest
	public void setup() throws IOException{
		try{
			driver = Driver.initialize(PropertiesUtil.getUrlFromProperties(), PropertiesUtil.getCapabilites());
		}catch(Exception e){
			driver = Driver.initialize(PropertiesUtil.getUrlFromProperties(), PropertiesUtil.getCapabilites());
		}
		Utils.verifyAppServerIsUp();
		
		login = new LoginPage();
		task = new TaskPage();
		newMail = new NewMailPage();
		bnavBar = new BottomNavigationBar();
		mailInboxPage = new MailInboxPage();
		viewMail = new ViewMailPage();
	}	
	
	
	//Send a mail to a recipient and validate the mail parameters set at the recipient end
	@Test
	public void SendMailTestAndValidate(){
		// Login as a user and send a mail
		login.login(USERNAME, PASSWORD, LOCATION);
		task.switchProject(PROJECT);
		task.waitForPagetoLoad().clickNewMail();
		newMail.enterMailParams(params);
		newMail.clickSend();
		newMail.assertMailSent();
		newMail.clickOkOnDialog();

		// Login as a different user
		bnavBar.waitForViewToLoad().navigateToSettings().clickLogout(); //  Fix this
		login.login(USERNAME2, PASSWORD, LOCATION);
		task.waitForPagetoLoad().switchProject(PROJECT);
		
		//Click unread mail and validate mail params
		bnavBar.navigateToMails();
		
		mailInboxPage.waitForViewToLoad().openMail(NewMailPage.getMailSubject());
		viewMail.verifyRecievedMail(params);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
 
}
