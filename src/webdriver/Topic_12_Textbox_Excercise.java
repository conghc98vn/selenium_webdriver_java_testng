package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Textbox_Excercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	Random rand = new Random();

	String emailAddress = "automation" + rand.nextInt(9999) + "@gmail.com";
	String firstName = "John";
	String lastName = "Wick";
	String fullName = firstName + " " + lastName;
	String password = "123456789";

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

	@Test
	public void TC_01_Ornage_HRM() {
		String employeeID = "";
		String userNameAdmin = "Admin";
		String passwordAdmin = "admin123";

		String firstName = "FirstName";
		String lastName = "LastName";
		String username = "automation" + rand.nextInt(999);
		String password = "Abc123!@#";

		String passportID = "123456789";
		String comment = "aumation test";

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userNameAdmin);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordAdmin);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(lastName);
		sleepInSecond(2);

		employeeID = driver
				.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.getAttribute("value");

		sleepInSecond(2);

		driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//span")).click();

		sleepInSecond(2);

		driver.findElement(By.xpath("//label[contains(text(),'Username')]/parent::div//following-sibling::div//input"))
				.sendKeys(username);
		driver.findElement(
				By.xpath("(//label[contains(text(),'Password')]/parent::div//following-sibling::div//input)[1]"))
				.sendKeys(password);
		driver.findElement(
				By.xpath("(//label[contains(text(),'Password')]/parent::div//following-sibling::div//input)[2]"))
				.sendKeys(password);

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		sleepInSecond(8);

		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='firstName']")).getAttribute("value"),
				firstName);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='lastName']")).getAttribute("value"), lastName);
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
						.getAttribute("value"),
				employeeID);

		driver.findElement(By.xpath("//a[text()='Immigration']")).click();

		sleepInSecond(2);

		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();

		driver.findElement(By.xpath("//label[contains(text(), 'Number')]/parent::div/following-sibling::div//input"))
				.sendKeys(passportID);
		driver.findElement(
				By.xpath("//label[contains(text(), 'Comments')]/parent::div/following-sibling::div//textarea"))
				.sendKeys(comment);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSecond(2);

		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();

		sleepInSecond(2);

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[contains(text(),'Number')]/parent::div//following-sibling::div//input"))
				.getAttribute("value"), passportID);
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//label[contains(text(),'Comments')]/parent::div//following-sibling::div//textarea"))
				.getAttribute("value"), comment);

		sleepInSecond(2);

		driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).click();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();

		sleepInSecond(2);

		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		sleepInSecond(2);

		driver.findElement(By.xpath("//span[text()='My Info']")).click();

		sleepInSecond(2);

		driver.findElement(By.xpath("//a[text()='Immigration']")).click();

		sleepInSecond(2);

		driver.findElement(By.xpath("//i[@class='oxd-icon bi-pencil-fill']")).click();

		sleepInSecond(2);

		Assert.assertEquals(driver
				.findElement(By.xpath("//label[contains(text(),'Number')]/parent::div//following-sibling::div//input"))
				.getAttribute("value"), passportID);
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//label[contains(text(),'Comments')]/parent::div//following-sibling::div//textarea"))
				.getAttribute("value"), comment);
	}

	@Test
	public void TC_02_() {
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