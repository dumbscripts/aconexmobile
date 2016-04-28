
package com.aconexmobile.frame;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;

public class PropertiesUtil {

	private static Properties urlProperty;
	private static DesiredCapabilities caps;
	private static Properties property;

	static final Logger logger = Logger.getLogger(PropertiesUtil.class);

	public static String getUrlFromProperties() {
		if (urlProperty == null) {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("url.properties");
			urlProperty = new Properties();
			try {
				urlProperty.load(is);
				is.close();
			} catch (IOException e) {
				logger.error("Error while opening file - url.properties");
				e.printStackTrace();
			}
		}
		return urlProperty.getProperty("url");
	}

	public static DesiredCapabilities getCapabilites() throws IOException {
		if (property == null) {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("caps.properties");
			property = new Properties();
			try {
				property.load(is);
				is.close();
			} catch (IOException e) {
				logger.error("Error while opening file - caps.properties");
				e.printStackTrace();
			}
		}
		File appDir = new File(property.getProperty("appDir"));
		File app = new File(appDir, property.getProperty("appName"));

		caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, property.getProperty("platformName"));
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, property.getProperty("deviceName"));
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, property.getProperty("platformVersion"));
		caps.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		if (property.getProperty("platformName").equals("Android")) {
			caps.setCapability("appPackage", property.getProperty("appPackage"));
			caps.setCapability("appActivity", property.getProperty("appActivity"));
		} else {
			// Add capability uuid for ios
		}
		return caps;
	}
/*
	public static DesiredCapabilities getAndroidCapabilites() throws IOException {
		if (androidProperty == null) {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("android.properties");
			androidProperty = new Properties();
			try {
				androidProperty.load(is);
				is.close();
			} catch (IOException e) {
				logger.error("Error while opening file - android.properties");
				e.printStackTrace();
			}
		}
		File appDir = new File(androidProperty.getProperty("appDir"));
		File app = new File(appDir, androidProperty.getProperty("appName"));

		androidCap = new DesiredCapabilities();
		androidCap.setCapability(MobileCapabilityType.PLATFORM_NAME, androidProperty.getProperty("platformName"));
		androidCap.setCapability(MobileCapabilityType.DEVICE_NAME, androidProperty.getProperty("deviceName"));
		androidCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, androidProperty.getProperty("platformVersion"));
		androidCap.setCapability("appPackage", androidProperty.getProperty("appPackage"));
		androidCap.setCapability("appActivity", androidProperty.getProperty("appActivity"));
		androidCap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		return androidCap;
	}

	public static DesiredCapabilities getIosCapabilites() {
		if (iosProperty == null) {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("ios.properties");
			iosProperty = new Properties();
			try {
				iosProperty.load(is);
				is.close();
			} catch (IOException e) {
				logger.error("Error while opening file - ios.properties");
				e.printStackTrace();
			}
		}
		File appDir = new File(iosProperty.getProperty("appDir"));
		File app = new File(appDir, iosProperty.getProperty("appName"));

		iosCap = new DesiredCapabilities();
		iosCap.setCapability(MobileCapabilityType.PLATFORM_NAME, iosProperty.getProperty("platformName"));
		iosCap.setCapability(MobileCapabilityType.DEVICE_NAME, iosProperty.getProperty("deviceName"));
		iosCap.setCapability(MobileCapabilityType.PLATFORM_VERSION, iosProperty.getProperty("platformVersion"));
		iosCap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		return iosCap;
	}
*/
}
