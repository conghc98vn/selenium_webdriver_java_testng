package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	JavascriptExecutor executor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		executor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accecpt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		alert.accept();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),
				"You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		alert.dismiss();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		assertEquals(alert.getText(), "I am a JS prompt");

		alert.sendKeys("hi");
		alert.accept();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: hi");
	}

	@Test
	public void TC_04_Alert() {
//		driver.get("https://netbanking.hdfcbank.com/netbanking/");
//		sleepInSecond(1);
//		
//		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='CONTINUE']")));
//		
//		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
//
//		assertEquals(alert.getText(), "Customer ID  cannot be left blank.");

		// Can't click "Continue" button
	}

	@Test
	public void TC_05_Authentication_Alert() {
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
	}

	@Test
	public void TC_05_Authentication_Alert_PassLink() {
		driver.get("https://the-internet.herokuapp.com/");
		sleepInSecond(2);

		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

		driver.get(getUrlByUserPass(url, "admin", "admin"));

		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Basic Auth']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getUrlByUserPass(String url, String username, String password) {
		String[] newUrl = url.split("//");
		return newUrl[0] + "//" + username + ":" + password + "@" + newUrl[1];
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}