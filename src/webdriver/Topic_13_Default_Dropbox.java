package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Default_Dropbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	String firstName, lastName, email, companyName, password;
	String day, month, year;
	Random rand = new Random();

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

		firstName = "test";
		lastName = "auto";
		email = "autotest" + rand.nextInt(999) + "@gmail.com";
		companyName = "test company";
		password = "Abc123!@#";
		day = "10";
		month = "May";
		year = "2012";
	}

	@Test
	public void TC_01_Defualt_Dropbox_nopCommerce() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).selectByVisibleText(day);
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText(month);
		new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText(year);
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys(companyName);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='register-button']")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(@class, 'login-button')]")).click();
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);
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