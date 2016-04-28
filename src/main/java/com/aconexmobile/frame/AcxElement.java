package com.aconexmobile.frame;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class AcxElement implements WebElement {

	static final int MAX_TIMEOUT = 10;
	static final Logger logger = Logger.getLogger(AcxElement.class);

	private String locator;
	private By by;
	private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), MAX_TIMEOUT);

	public AcxElement(String locator) {
		this.locator = locator;
	}

	private String getLocator() {
		if (!locator.contains("::")) {
			throw new RuntimeException("Unknown locator - " + locator);
		}
		int index = locator.indexOf("::");
		String actualLocator = locator.substring(index + 2);
		return actualLocator;
	}

	private By getBy() {
		String loc = getLocator();
		String byString = locator.substring(0, locator.indexOf("::"));
		switch (byString.toLowerCase()) {

		case "id":
			by = By.id(loc);
			break;
		case "css":
			by = By.cssSelector(loc);
			break;
		case "xpath":
			by = By.xpath(loc);
			break;
		case "classname":
			by = By.className(loc);
			break;
		default:
			throw new RuntimeException("Unknown by strategy - " + byString);
		}
		return by;
	}

	private WebElement getElement() {
		By by = getBy();
		return Driver.getDriver().findElement(by);
	}

	@Override
	public void clear() {
		getElement().clear();
	}

	@Override
	public void click() {
		getElement().click();
	}

	@Override
	public WebElement findElement(By arg) {
		throw new RuntimeException("Use overloaded findElement instead!");
	}

	public WebElement findElement() {
		return Driver.getDriver().findElement(getBy());
	}

	@Override
	public List<WebElement> findElements(By arg0) {
		throw new RuntimeException("Use overloaded findElements instead!");
	}

	public List<WebElement> findElements() {
		return Driver.getDriver().findElements(getBy());
	}

	@Override
	public String getAttribute(String arg0) {
		return getElement().getAttribute(arg0);
	}

	@Override
	public String getCssValue(String arg0) {
		return getElement().getCssValue(arg0);
	}

	@Override
	public Point getLocation() {
		return getElement().getLocation();
	}

	@Override
	public Dimension getSize() {
		return getElement().getSize();
	}

	@Override
	public String getTagName() {
		return getElement().getTagName();
	}

	@Override
	public String getText() {
		return getElement().getText();
	}

	@Override
	public boolean isDisplayed() {
		return getElement().isDisplayed();
	}

	@Override
	public boolean isEnabled() {
		return getElement().isEnabled();
	}

	@Override
	public boolean isSelected() {
		return getElement().isSelected();
	}

	@Override
	public void sendKeys(CharSequence... arg0) {
		scrollToElement();
		if (isDisplayed()) {
			clear();
			getElement().sendKeys(arg0);
			try {
				Driver.getDriver().hideKeyboard();
			} catch (Exception e) {
			}
			;
		}
	}

	@Override
	public void submit() {
		getElement().submit();
	}

	public boolean isElementPresent() {
		try{
			turnOffImplicitWaits();
			wait.until(ExpectedConditions.visibilityOf(getElement()));
			turnOnImplicitWaits();
			return true;
		}catch(NoSuchElementException e){
			turnOnImplicitWaits();
			return false;
		}
	}

	private void turnOffImplicitWaits() {
		Driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	private void turnOnImplicitWaits() {
		Driver.getDriver().manage().timeouts().implicitlyWait(Driver.MAX_TIMEOUT, TimeUnit.SECONDS);
	}

	// Scrolling
	public void scrollToElement() {
		Dimension dimensions = Driver.getDriver().manage().window().getSize();
		Double screenHeightStart = dimensions.getHeight() * 0.5;
		int scrollStart = screenHeightStart.intValue();
		Double screenHeightEnd = dimensions.getHeight() * 0.2;
		int scrollEnd = screenHeightEnd.intValue();
		for (int i = 0; i < dimensions.getHeight(); i++) {
			if (isElementPresent()) {
				break;
			} else {
				Driver.getDriver().swipe(0, scrollStart, 0, scrollEnd, 2000);
				logger.debug("Couldn't find element - Scrolling");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void waitForElementToBeVisible() {
		wait.until(ExpectedConditions.visibilityOf(getElement()));
	}

	public void waitForElementToBeVisible(int timeout) {
		//Added this to handle the case where the app takes more time to load the content of a mail
		Driver.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		WebDriverWait waitTime = new WebDriverWait(Driver.getDriver(), timeout);
		waitTime.until(ExpectedConditions.visibilityOf(getElement()));
		turnOnImplicitWaits();
		
	}
	
	public void waitForElementToBeClickable() {
		wait.until(ExpectedConditions.elementToBeClickable(getElement()));
	}

	public void waitForElementToBeInvisible() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy()));
	}


}
