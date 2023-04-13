package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Xpath_Css {
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

	@Test
	public void TC_01_Register_With_Empty_Data() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("");

		driver.findElement(By.xpath("//div[@id='leftcontent']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),
				"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),
				"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),
				"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),
				"Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Register_With_Invalid_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Name Test");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("emailinvalid");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("email.test@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");

		driver.findElement(By.xpath("//div[@id='leftcontent']//button[text()='ĐĂNG KÝ']")).click();


		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),
				"Vui lòng nhập email hợp lệ");
	}

	@Test
	public void TC_03_Register_With_Incoreect_Confirm_Email() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Name Test");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("email.test@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("email@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");

		driver.findElement(By.xpath("//div[@id='leftcontent']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				"Email nhập lại không đúng");
	}

	@Test
	public void TC_04_Register_With_Password_Less_Than_6_Chars() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Name Test");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("email.test@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("email.test@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");

		driver.findElement(By.xpath("//div[@id='leftcontent']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Register_With_Incoreect_Confirm_Password() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Name Test");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("email.test@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("email.test@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123123");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0987654321");

		driver.findElement(By.xpath("//div[@id='leftcontent']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				"Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_06_Register_With_Invalid_PhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Name Test");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("email.test@gmai.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("email.test@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123456789");

		driver.findElement(By.xpath("//div[@id='leftcontent']//button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}