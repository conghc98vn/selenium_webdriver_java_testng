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

public class Topic_20_Popup_Random {
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
	public void TC_01_In_Dom_Auto_Show_JVCGeeks() {
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

//	@Test
	public void TC_02_Popup_Random_KmPlayer() {
		driver.get("http://www.kmplayer.com/");

		sleepInSecond(10);

		By popup = By.xpath("//div[@class='pop-container']");

		List<WebElement> listElement = driver.findElements(popup);

		if (driver.findElements(popup).size() > 0 && listElement.get(0).isDisplayed()) {
			System.out.println("ok");
//			driver.findElement(By.xpath("//area[@alt='close']")).click();
		}

		driver.findElement(By.xpath("//a[text()='HOME']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "https://www.kmplayer.com/home");
	}

	@Test
	public void TC_03_Popup_Randon() {
		driver.get("https://dehieu.vn/");
		
		By popup = By.xpath("//div[@class='popup-content']");
		List<WebElement> listpopup = driver.findElements(popup);
		
		sleepInSecond(2);
		
		if (listpopup.size() > 0 && listpopup.get(0).isDisplayed()) {
			driver.findElement(By.xpath("//button[@id='close-popup']")).click();
			sleepInSecond(3);
		}

		Assert.assertTrue(driver.findElements(popup).size() == 0);
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