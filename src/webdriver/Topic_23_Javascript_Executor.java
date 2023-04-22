package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Javascript_Executor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;

	Random rand = new Random();

	String emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
	String firstName = "John";
	String lastName = "Wick";
	String fullName = firstName + " " + lastName;
	String password = "123456789";

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Techpanda() {
		navigateToUrlByJS("http://live.techpanda.org/");

		sleepInSecond(3);

		Assert.assertEquals((String) executeForBrowser("return document.domain"), "live.techpanda.org");

		Assert.assertEquals((String) executeForBrowser("return document.URL"), "http://live.techpanda.org/");

		sleepInSecond(3);

		clickToElementByJS("//a[text()='Mobile']");

		sleepInSecond(3);

		clickToElementByJS(
				"//a[text()='Samsung Galaxy']//ancestor::div[@class='product-info']//a[text()='Add to Compare']");

		Assert.assertTrue(areExpectedTextInInnerText("The product Samsung Galaxy has been added to comparison list."));

		clickToElementByJS("//a[text()='Customer Service']");

		sleepInSecond(3);

		Assert.assertEquals(executeForBrowser("return document.title"), "Customer Service");

		scrollToElementOnDown("//input[@id='newsletter']");

		sendkeyToElementByJS("//input[@id='newsletter']", "tesst@gmail.com.vn");

		clickToElementByJS("//button[@title='Subscribe']");

		sleepInSecond(3);

		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");

		sleepInSecond(5);

		Assert.assertEquals(executeForBrowser("return document.domain"), "demo.guru99.com");
	}

	@Test
	public void TC_02_Verify_HTML5_Validation_Message() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");

		clickToElementByJS("//input[@name='submit-btn']");

		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), ("Please fill out this field."));

		sendkeyToElementByJS("//input[@id='fname']", "Name Test");

		clickToElementByJS("//input[@name='submit-btn']");

		sleepInSecond(2);

		Assert.assertTrue(getElementValidationMessage("//input[@id='pass']").contains("Please fill out this field."));

		sendkeyToElementByJS("//input[@id='pass']", "Name Test");

		clickToElementByJS("//input[@name='submit-btn']");

		sleepInSecond(2);

		Assert.assertTrue(getElementValidationMessage("//input[@name='em']").contains("Please fill out this field"));

		sendkeyToElementByJS("//input[@name='em']", "Name Test");

		clickToElementByJS("//input[@name='submit-btn']");

		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//input[@name='em']"), ("Please enter an email address."));

		sendkeyToElementByJS("//input[@name='em']", "test@gmail.com");

		clickToElementByJS("//input[@name='submit-btn']");

		sleepInSecond(2);

		new Select(driver.findElement(By.xpath("//select"))).selectByVisibleText("HO CHI MINH");

		clickToElementByJS("//input[@name='submit-btn']");
	}

	@Test
	public void TC_03_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4");

		sendkeyToElementByJS("//input[@name='uid']", "mngr495070");
		sendkeyToElementByJS("//input[@name='password']", "erYbudU");

		clickToElementByJS("//input[@name='btnLogin']");

		sleepInSecond(2);

		clickToElementByJS("//a[text()='New Customer']");

		sleepInSecond(2);

		sendkeyToElementByJS("//input[@name='name']", "Name Test");
		removeAttributeInDOM("//input[@name='dob']", "type");
		sendkeyToElementByJS("//input[@name='dob']", "10/10/2020");
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("Address test");
		sendkeyToElementByJS("//input[@name='city']", "City test");
		sendkeyToElementByJS("//input[@name='state']", "State test");
		sendkeyToElementByJS("//input[@name='pinno']", "123123123");
		sendkeyToElementByJS("//input[@name='telephoneno']", "123123123");
		sendkeyToElementByJS("//input[@name='emailid']", "test@gmail.com");
		sendkeyToElementByJS("//input[@name='password']", "123123");

//		sleepInSecond(2);
//
//		clickToElementByJS("//input[@name='sub' ]");
//
//		sleepInSecond(2);
//
//		Assert.assertTrue(areExpectedTextInInnerText("Customer Registered Successfully!!!"));
	}

	@Test
	public void TC_04_Create_An_Account() {
		navigateToUrlByJS("http://live.techpanda.org/");

		clickToElementByJS("//div[@class='footer']//a[text()='My Account']");

		clickToElementByJS("//a[@title='Create an Account']/span");

		sendkeyToElementByJS("//input[@id='firstname']", firstName);
		sendkeyToElementByJS("//input[@id='lastname']", lastName);
		sendkeyToElementByJS("//input[@id='email_address']", emailAddress);
		sendkeyToElementByJS("//input[@id='password']", password);
		sendkeyToElementByJS("//input[@id='confirmation']", password);

		clickToElementByJS("//button[@title='Register']");

		Assert.assertTrue(areExpectedTextInInnerText("Thank you for registering with Main Website Store."));

		clickToElementByJS("//a[text()='Log Out']");

		Assert.assertEquals(driver.getTitle(), "Magento Commerce");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");// a[text()='Mobile']
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
		sleepInSecond(3);
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
		sleepInSecond(3);
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
		jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue + "');",
				getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getAttributeInDOM(String locator, String attributeName) {
		return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');",
				getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}