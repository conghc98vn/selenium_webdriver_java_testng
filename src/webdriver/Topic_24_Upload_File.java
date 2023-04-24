package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Upload_Sigle_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFileBy = By.xpath("//input[@type='file']");

		driver.findElement(uploadFileBy).sendKeys(hueCityPath);
		sleepInSecond(2);
		driver.findElement(uploadFileBy).sendKeys(nhatrangCityPath);
		sleepInSecond(2);
		driver.findElement(uploadFileBy).sendKeys(quynhonCityPath);
		sleepInSecond(2);

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hueCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + nhatrangCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + quynhonCity + "']")).isDisplayed());

		List<WebElement> startElements = driver.findElements(By.xpath("//span[text()='Start']"));

		for (WebElement startButton : startElements) {
			startButton.click();
			sleepInSecond(1);
		}

		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hueCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + nhatrangCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + quynhonCity + "']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_Multiple_Files() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		By uploadFileBy = By.xpath("//input[@type='file']");

		driver.findElement(uploadFileBy).sendKeys(hueCityPath + "\n" + nhatrangCityPath + "\n" + quynhonCityPath);
		sleepInSecond(2);

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + hueCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + nhatrangCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + quynhonCity + "']")).isDisplayed());

		List<WebElement> startElements = driver.findElements(By.xpath("//span[text()='Start']"));

		for (WebElement startButton : startElements) {
			startButton.click();
			sleepInSecond(1);
		}

		sleepInSecond(3);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hueCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + nhatrangCity + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + quynhonCity + "']")).isDisplayed());
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