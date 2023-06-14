package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Wait_Element_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_Displayed() {
		driver.get("https://www.facebook.com/");

		// Điều kiện 1: Element có trên giao diện (UI) và có trong HTML
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='login']")));
	}

	@Test
	public void TC_02_Undisplayed_In_HTML() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"));

		// Điều kiện 2: Element không có trên UI nhưng vẫn có trong HTML
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_03_Undisplayed_Not_In_HTML() {
		driver.get("https://www.facebook.com/");

		// Điều kiện 2: Element không có trên UI nhưng vẫn có trong HTML
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	}

	@Test
	public void TC_04_Presence() {
		driver.get("https://www.facebook.com/");

		// Điều kiện bắt buộc của Presence: Phải có trong HTML

		// Điều kiện 1: Element có trên giao diện (UI) và có trong HTML
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='login']")));
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"));

		// Điều kiện 2: Element không có trên UI nhưng vẫn có trong HTML
		explicitWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

	}

	@Test
	public void TC_05_Staless() {
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"));

		// Tại thời điểm này nó đang có trong HTML
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"));

		// Wait cho Confirm Email textbox không còn trong HTML nữa
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("\"//input[@name='reg_email_confirmation__']\"")));
		explicitWait.until(ExpectedConditions
				.stalenessOf(driver.findElement(By.xpath("\"//input[@name='reg_email_confirmation__']\""))));

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
}