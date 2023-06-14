package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_Static {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Implicit_No_Set() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to Start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		// Get text
		String helloText = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();

		// Verify
		Assert.assertEquals(helloText, "Hello World!");
	}

	@Test
	public void TC_02_Implicit_Not_Enough() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to Start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		// Get text
		String helloText = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();

		// Verify
		Assert.assertEquals(helloText, "Hello World!");
	}

	@Test
	public void TC_03_Implicit_Equal() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to Start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		// Get text
		String helloText = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();

		// Verify
		Assert.assertEquals(helloText, "Hello World!");
	}
	
	@Test
	public void TC_03_Implicit_Greater() {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click to Start button
		driver.findElement(By.xpath("//div[@id='start']/button")).click();

		// Get text
		String helloText = driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();

		// Verify
		Assert.assertEquals(helloText, "Hello World!");
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