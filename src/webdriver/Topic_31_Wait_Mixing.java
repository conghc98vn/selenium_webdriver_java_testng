package webdriver;

<<<<<<< HEAD
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_31_Wait_Mixing {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	String hueCity = "hue.jpg";
	String nhatrangCity = "nhatrang.jpg";
	String quynhonCity = "quynhon.jpg";

	String hueCityPath = projectPath + File.separator + "uploadFiles" + File.separator + hueCity;
	String nhatrangCityPath = projectPath + File.separator + "uploadFiles" + File.separator + nhatrangCity;
	String quynhonCityPath = projectPath + File.separator + "uploadFiles" + File.separator + quynhonCity;

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

//	@Test
	public void TC_01_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for Helloworld text visible(xuất hiện)
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		Assert.assertEquals(helloText, "Hello World!");
	}

//	@Test
	public void TC_02_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for Loading icon invisible(xuất hiện)
		// Khi 1 cái này biến mất thì cái kia sẽ xuất hiện hoặc ngược lại
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		Assert.assertEquals(helloText, "Hello World!");
	}

//	@Test
	public void TC_03_Text() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for Loading icon invisible(xuất hiện)
		// Khi 1 cái này biến mất thì cái kia sẽ xuất hiện hoặc ngược lại
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), ("Hello World!")));

		String helloText = driver.findElement(By.cssSelector("div#finish>h4")).getText();

		Assert.assertEquals(helloText, "Hello World!");
	}

//	@Test
	public void TC_04_Telerik() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Wait cho Data Picker visible
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

		// Wait và verify cho Locator chứa text
		Assert.assertTrue(explicitWait.until(ExpectedConditions
				.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), ("No Selected Dates to display."))));

		// Wait cho ngày được click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='11']/parent::td"))).click();

		// Wait cho loading icon biến mất
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated());

		// Wait và verify cho Locator chứa text
		Assert.assertTrue(explicitWait.until(ExpectedConditions
				.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), ("Sunday, June 11, 2023"))));
	}

	@Test
	public void TC_05_Upload() {
		driver.get("https://gofile.io/?t=uploadFiles");

		By uploadFileBy = By.cssSelector("input#filesUploadInput");

		// Wait cho tất cả incon loading biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Upload Files']")))
				.click();

		// Wait cho tất cả các icon loading biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

		// Upload 3 file lên
		driver.findElement(uploadFileBy).sendKeys(hueCityPath + "\n" + nhatrangCityPath + "\n" + quynhonCityPath);

		// Wait cho tất cả upload biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

		// Wait for text
		Assert.assertTrue(explicitWait.until(
				ExpectedConditions.textToBe(By.xpath("//div[@class='row justify-content-center mainUploadSuccess']"),
						("Your files have been successfully uploaded"))));

		driver.findElement(By.xpath("//div[@class='row mb-2 mainUploadSuccessLink']//a")).click();

		// Wait cho tất cả icon loading biến mất
		Assert.assertTrue(explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

		Assert.assertTrue(explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='" + hueCity + "']")))
				.isDisplayed());
		Assert.assertTrue(
				explicitWait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//span[@class='contentName' and text()='" + nhatrangCity + "']")))
						.isDisplayed());
		Assert.assertTrue(
				explicitWait
						.until(ExpectedConditions.visibilityOfElementLocated(
								By.xpath("//span[@class='contentName' and text()='" + quynhonCity + "']")))
						.isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
=======
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

>>>>>>> branch 'master' of https://github.com/conghc98vn/selenium_webdriver_java_testng
}