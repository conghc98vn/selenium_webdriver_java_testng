package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Windows_Tabs {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor executor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		executor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String githubPageID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

		switchToWindowByID(githubPageID);

		sleepInSecond(1);

		Assert.assertEquals(driver.getTitle(), "Google");

		String googlePageID = driver.getWindowHandle();

		switchToWindowByID(googlePageID);

		sleepInSecond(1);

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

		sleepInSecond(1);

		switchToWindowByTitle("Facebook – log in or sign up");

		sleepInSecond(1);

		Assert.assertTrue(driver.getTitle().equals("Facebook – log in or sign up"));

		switchToWindowByTitle("Selenium WebDriver");

		sleepInSecond(1);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();

		sleepInSecond(1);

		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		sleepInSecond(1);

		Assert.assertTrue(driver.getTitle().equals("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh"));

		closeAllWindowWithoutExpectedID(githubPageID);
	}

	@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");

		String parentIDWindow = driver.getWindowHandle();

		sleepInSecond(2);

		executor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")));

		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();

		sleepInSecond(5);

		switchToWindowByTitle("Kyna.vn - YouTube");

		sleepInSecond(5);

		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");

		sleepInSecond(2);

		switchToWindowByTitle("Kyna.vn - Home | Facebook");

		Assert.assertTrue(driver.getTitle().contains("Kyna.vn - Home | Facebook"));

		sleepInSecond(2);

		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

		Assert.assertTrue(driver.getTitle().contains("Kyna.vn - Học online cùng chuyên gia"));

		closeAllWindowWithoutExpectedID(parentIDWindow);
	}

	@Test
	public void TC_03_Techpanda() {
		driver.get("http://live.techpanda.org/");

		String parentIDWindow = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		sleepInSecond(2);

		driver.findElement(
				By.xpath("//a[text()='Sony Xperia']//ancestor::div[@class='product-info']//a[text()='Add to Compare']"))
				.click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath(
				"//a[text()='Samsung Galaxy']//ancestor::div[@class='product-info']//a[text()='Add to Compare']"))
				.click();

		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//span[text()='Compare']")).click();

		sleepInSecond(2);

		switchToWindowByID(parentIDWindow);

		sleepInSecond(2);

		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

		driver.close();

		driver.switchTo().window(parentIDWindow);

		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
	}

	@Test
	public void TC_04_Cambridge() {
		driver.get("https://dictionary.cambridge.org/vi/");

		String parentIDWindow = driver.getWindowHandle();

		driver.findElement(By.xpath("//span[text()='Đăng nhập' and @class='tb']")).click();

		sleepInSecond(2);

		switchToWindowByID(parentIDWindow);

		sleepInSecond(3);

		driver.findElement(By.xpath("//input[@value='Log in']")).click();

		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@data-bound-to='loginID' and @role='alert']")).getText(),
				"This field is required");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@data-bound-to='password' and @role='alert']")).getText(),
				"This field is required");

		driver.close();

		driver.switchTo().window(parentIDWindow);

		sleepInSecond(2);

		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("automation");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@data-id='cald4']//span[@class='hw dhw']")).getText(),
				"automation");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Dùng cho 2 windows/tabs
	public void switchToWindowByID(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	// Có thể chạy cho 2 hoặc nhiều hơn 2 windows/ tabs
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			driver.switchTo().window(id);
			if (driver.getTitle().equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutExpectedID(String expectedID) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}

		driver.switchTo().window(expectedID);
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}