package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Iframe {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Iframe() {
		driver.get("https://skills.kynaenglish.vn/");

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']/iframe")).isDisplayed());

		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']/iframe")));

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='_1drq']")).getText(), "165K likes");

		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='cs_chat_iframe']")));

		driver.findElement(By.xpath("//div[@class='meshim_widget_Widget']")).click();

		driver.findElement(By.xpath("//div[@class='scrollable_inner']//input[@placeholder='Nhập tên của bạn']"))
				.sendKeys("abc");

		driver.findElement(
				By.xpath("//div[@class='scrollable_inner']//input[@placeholder='Nhập số điện thoại của bạn']"))
				.sendKeys("abc");

		new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")))
				.selectByVisibleText("TƯ VẤN TUYỂN SINH");

		driver.findElement(By.xpath("//textarea[@placeholder]")).sendKeys("abc");

		driver.switchTo().defaultContent();

		driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("excel");

		driver.findElement(By.xpath("//div[@id='live-search-result']//div[@class='live-search-result-full-search']/a"))
				.click();

		List<WebElement> listElement = driver.findElements(By.xpath("//ul[@class='k-box-card-list']/li/a"));

		for (WebElement webElement : listElement) {
			String linkElement = webElement.getAttribute("href");
			Assert.assertTrue(linkElement.contains("excel"));
		}
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");

		driver.switchTo().frame(driver.findElement(By.xpath("//frame")));

		driver.findElement(By.xpath("//div[contains(@class, 'loginData')]/input")).sendKeys("admin");

		driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();

		driver.switchTo().defaultContent();

		driver.switchTo().frame(driver.findElement(By.xpath("//frame")));

		Assert.assertTrue(driver.findElement(By.xpath("//div/input[@type='password']")).isDisplayed());
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