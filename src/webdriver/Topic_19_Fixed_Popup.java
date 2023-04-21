package webdriver;

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

public class Topic_19_Fixed_Popup {
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

//	@Test
	public void TC_01_In_Dom_NgoaiNgu24h() {
		driver.get("https://ngoaingu24h.vn/");

		By popupLogin = By.xpath("//div[@id='modal-login-v1' and @style]//div[@class='modal-content']");

		driver.findElement(By.xpath("//div[@id='button-login-dialog']/button[text()='Đăng nhập']")).click();

		Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());

		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@id='account-input']"))
				.sendKeys("admin");
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//input[@id='password-input']"))
				.sendKeys("admin");
		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//button[text()='Đăng nhập']")).click();

		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//div[@class='row error-login-panel']"))
				.getText(), "Mật khẩu sai!");

		driver.findElement(By.xpath("//div[@id='modal-login-v1' and @style]//button[@class='close']")).click();

		Assert.assertFalse(driver.findElement(popupLogin).isDisplayed());
	}

//	@Test
	public void TC_02_In_Dom_KyNa() {
		driver.get("https://skills.kynaenglish.vn/");

		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@id='k-popup-account-login']//div[@class='right']")).isDisplayed());

		driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("admin");
		driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();

		sleepInSecond(2);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(),
				"Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.xpath("//div[@id='k-popup-account-login-mb']//button[@aria-label]")).click();

		Assert.assertFalse(
				driver.findElement(By.xpath("//div[@id='k-popup-account-login']//div[@class='right']")).isDisplayed());
	}

//	@Test
	public void TC_03_Not_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");

		By popupLogin = By.xpath("//div[@role='dialog']/div");

		driver.findElement(By.xpath("//header[@id='main-header']//span[text()='Tài khoản']")).click();

		Assert.assertTrue(driver.findElement(popupLogin).isDisplayed());

		driver.findElement(By.xpath("//button[text()='Tiếp Tục']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess']")).getText(),
				"Số điện thoại không được để trống");

		driver.findElement(By.xpath("//img[@class='close-img']")).click();

		List<WebElement> listelementPopup = driver.findElements(popupLogin);

		Assert.assertEquals(listelementPopup.size(), 0);
	}

//	@Test
	public void TC_04_Not_In_Dom_Facebook() {
		driver.get("https://www.facebook.com/");

		By popupSignup = By.xpath("//div[@class='_8ien']");

		driver.findElement(By.xpath("//a[@rel='async']")).click();

		Assert.assertTrue(driver.findElement(popupSignup).isDisplayed());

		driver.findElement(By.xpath("//div[@class='_8ien']/img")).click();

		Assert.assertEquals(driver.findElements(popupSignup).size(), 0);
	}

	@Test
	public void TC_05_Not_In_Dom_Auto_Show_JVCGeeks() {
//		driver.get("https://www.javacodegeeks.com/");
//
//		sleepInSecond(30);
//
//		By popup = By.xpath("//div[@id='lepopup-popup-125']/div[@data-page='1']");
//		List<WebElement> listPopup = driver.findElements(popup);
//
//		if (listPopup.size() > 0 && listPopup.get(0).isDisplayed()) {
//			driver.findElement(By.xpath("//input[@name='lepopup-10']")).sendKeys("test@gmail.com");
//			driver.findElement(By.xpath("//a[@data-loading='Loading...']")).click();
//			sleepInSecond(10);
//		}
//
//		driver.findElement(By.xpath("//input[@id='search-input']")).sendKeys("agile testing explained");
//		sleepInSecond(2);
//		driver.findElement(By.xpath("//button[@id='search-submit']")).click();
//		Assert.assertEquals(driver.findElements(By.xpath("//h1[@class='page-title']/span")), "agile testing explained");
		// 
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