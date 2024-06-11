package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_ShadowDom {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/shadow-dom");
		sleepInSecond(1);
		
		WebElement shadowHostElement = driver.findElement(By.cssSelector("#shadow_host"));
		
		SearchContext shadownRootContext = shadowHostElement.getShadowRoot();
		
		shadownRootContext.
	}

	@Test
	public void TC_02_() {

	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}