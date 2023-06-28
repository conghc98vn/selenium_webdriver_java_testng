package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_31_Wait_Mixing {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWait;

	long durationTime = 30;
	long pollingTime = 1;

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

		explicitWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//div[@id='start']/button"));

		fluentWait = new FluentWait<WebDriver>(driver);

		// Set timeout tổng time bằng bao nhiêu
		fluentWait.withTimeout(Duration.ofSeconds(5))

				// Polling/ interval time: lặp lại
				.pollingEvery(Duration.ofMillis(200))

				// Ignoring exception nếu không tìm thấy element
				.ignoring(NoSuchElementException.class);

		// ĐIều kiện để wait
		// Wait cho element có locator này isDisplayed()
		// //div[@id='finish']/h4[text()='Hello World!']
		boolean textStatus = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
			}

		});

		Assert.assertTrue(textStatus);

		// Wait cho element có cái text -> getText()
		// //div[@id='finish']/h4
		String helloText = fluentWait.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id='finish']/h4")).getText();
			}

		});

		Assert.assertEquals(helloText, "Hello World!");

	}

	@Test
	public void TC_02_() {
		driver.get("https://automationfc.github.io/fluent-wait/");

		fluentWait = new FluentWait<WebDriver>(driver);

		fluentWait.withTimeout(Duration.ofSeconds(15)).pollingEvery(Duration.ofMillis(300))
				.ignoring(NoSuchElementException.class);

		boolean textStatus = fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				String text = driver.findElement(By.cssSelector("div#javascript_countdown_time")).getText();
				System.out.println(text);
				return text.equals("01:01:00");
			}
		});

		Assert.assertTrue(textStatus);
	}

	public WebElement findWebElement(By by) {
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class);

		return fluentWait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
	}
	
	public String getElement(By by) {
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class);

		return fluentWait.until(new Function<WebDriver, String>() {

			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(by).getText();
			}
		});
	}
	
	public Boolean isElementDisplayed(By by) {
		fluentWait = new FluentWait<WebDriver>(driver);
		fluentWait.withTimeout(Duration.ofSeconds(durationTime)).pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class);

		return fluentWait.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(by).isDisplayed();
			}
		});
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}