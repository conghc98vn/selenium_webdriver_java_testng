package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Web_Element_Excercise_I {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Check_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(driver.findElement(By.xpath("//label[@for='email']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Age:']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@for='edu']")).isDisplayed());

		Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());
	}

//	@Test
	public void TC_02_Check_Enable_Disable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Assert.assertTrue(driver.findElement(By.xpath("//label[@for='email']//following-sibling::input[@id='email']"))
				.isEnabled());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[@for='password']//following-sibling::input[@id='under_18']"))
						.isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@for='edu']//following-sibling::textarea[@id='edu']"))
				.isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@for='job1']//following-sibling::select[@id='job1']"))
				.isEnabled());
		Assert.assertTrue(driver
				.findElement(By.xpath("//label[text()='Interests:']//following-sibling::input[@id='development']"))
				.isEnabled());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[@for='slider-1']//following-sibling::input[@id='slider-1']"))
						.isEnabled());

		Assert.assertFalse(driver
				.findElement(By.xpath("//label[@for='password']//following-sibling::input[@id='disable_password']"))
				.isEnabled());
		Assert.assertFalse(
				driver.findElement(By.xpath("//label[@for='password']//following-sibling::input[@id='radio-disabled']"))
						.isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//label[@for='edu']//following-sibling::textarea[@id='bio']"))
				.isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//label[@for='job3']//following-sibling::select[@id='job3']"))
				.isEnabled());
		Assert.assertFalse(driver
				.findElement(By.xpath("//label[text()='Interests:']//following-sibling::input[@id='check-disbaled']"))
				.isEnabled());
		Assert.assertFalse(
				driver.findElement(By.xpath("//label[@for='slider-2']//following-sibling::input[@id='slider-2']"))
						.isEnabled());
	}

//	@Test
	public void TC_03_Check_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//label[@for='password']//following-sibling::input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@id='java']")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[@for='password']//following-sibling::input[@id='under_18']"))
						.isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
		driver.findElement(By.xpath("//input[@id='java']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
	}

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("test113@gmail.com");

		// Numberic
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("1234567");

		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		sleepInSecond(2);

		// Lowercase
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("abcd");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		sleepInSecond(2);

		// Uppercase
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("ABCD");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		sleepInSecond(2);

		// SpecialChar
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("!@$");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

		sleepInSecond(2);

		// 8 char
		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("12345678");
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
//		driver.quit();
	}

	private void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}