package com.aconexmobile.frame;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class Driver {

	public static final int MAX_TIMEOUT = 20;
	private static MobileDriver driver;
	
	static final Logger logger = Logger.getLogger(Driver.class);
	
	public static MobileDriver initialize(String url, DesiredCapabilities cap){
		
		try{
			
			if (cap.getCapability("platformName").toString().toLowerCase().equals("android")){
				driver = new AndroidDriver<AndroidElement>(new URL(url), cap);
				logger.info("Found Android platform");
			}else {
				driver = new IOSDriver<IOSElement>(new URL(url), cap);
				logger.info("Found iOS platform");
			}
			}catch (MalformedURLException e){
				e.printStackTrace();
				}
		driver.manage().timeouts().implicitlyWait(MAX_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
	
	
	public static MobileDriver getDriver(){
		return driver;
	}
}
