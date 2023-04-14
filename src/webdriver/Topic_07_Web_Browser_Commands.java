package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Browser_Commands {
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
		driver.get("");
	}

	@Test
	public void TC_01_Browser() {
		// Các command/ hàm để tương tác với Browser thì nó thông qua biến driver
		
		// Dùng để đông cái tab hiện tại
		// Đóng luôn browser nếu chỉ có 1 tab
		// Handle Windows/ Tab
		driver.close();
		
		// Không đón tab -> Đóng browser
		driver.quit();
		
		//  Tìm 1 element với 1 locator nào đó (id/ class/ name/ css/ xpath/...)
		driver.findElement(By.id(""));
		
		// Tìm nhiều element với 1 locator nào đó
		// Tìm ra all các đường link của page hiện tại
		driver.findElements(By.xpath("//a[@href]"));
		
		// Tìm ra all các checkbox/ radio
		driver.findElements(By.xpath("//input[@type='checkbox']"));
	
		// Mở ra 1 page/ link Url nào đó
		driver.get("http://live.techpanda.org/index.php/customer/account/create");
		
		// Lấy ra Url hiện tại của page
		// Đang đúng tại page nào lấy của page đó
		// 1 - Dùng duy nhất 1 step
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create");
		// 2- Dùng cho nhiều hơn 1 step thì mới khai báo biến
		// Code rườm rà - tốn bộ nhớ
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.techpanda.org/index.php/customer/account/create");
		
		// Lấy ra code HTML/ CSS/...
		Assert.assertTrue(driver.getPageSource().contains("home"));
		
		// Lấy ra title của page hiện tại
		Assert.assertEquals(driver.getTitle(), "Mobile");
		
		// Window/ Tab
		// Lấy ra ID của tab/ window hiện tại
		driver.getWindowHandle();
		
		// Lấy ra tất cả các id của các tab/ window
		driver.getWindowHandles();
		
		// Cookies
		driver.manage().deleteAllCookies();
		
		Set<Cookie> cookies = driver.manage().getCookies();
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		driver.navigate().refresh(); // Kết hợp với Cookie
		
		// Log của browser
		driver.manage().logs().get(LogType.BROWSER);
		
		// Để chờ cho element xuất hiện trong vòng bao lâu
		// 2 hàm: findelement/ findelements
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.DAYS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MINUTES);
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
		
		// Để chờ cho page được load xong trong vòng bao lâu
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
		
		// Để chờ cho 1 đoạn script được thực thi xong trong vòng bao lâu
		// JavascriotExecutor
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// Windows
		// Fullscreen
		driver.manage().window().fullscreen();
		// Maximize
		driver.manage().window().maximize();
		
		// Lấy vị trí ra
		Point point = driver.manage().window().getPosition();
		point.getX();
		point.getY();
		// Set tại một vị trí nào đó
		driver.manage().window().setPosition(new Point(0, 0)); 
		Dimension dimension = driver.manage().window().getSize();
		dimension.getWidth();
		dimension.getHeight();
		
		// Sét chiều rộng/ cao cho browser
		driver.manage().window().setSize(new Dimension(1366, 768));
		
		// Navigate
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("google.com");
		
		
		// Window/ Tab
		// Frame/ Iframe
		// Alert
		driver.switchTo().alert();
		driver.switchTo().frame(1);
		driver.switchTo().window("");
	}

	@Test
	public void TC_02_Element() {
		// Các command/ hàm để tương tác với Element thì nó thông qua việc findElement
		// driver.findElement(By.xpath(""));
	}

	@Test
	public void TC_03_Tips() {
		// Chia ra gồm 3 nhóm chính

		// Nhóm 1 - hàm để tương tác/ action
		// Tên hàm sẽ thể hiện rõ chức năng hàm đó
		// Không trả về (return) dữ liệu gì hết
		// click/ sendkeys/ select/...

		// Nhóm 2 - lấy ra dữ liệu cho mục đích nào đó (step tiếp theo/ hiện tại)
		// Nó sẽ bắt đầu bằng tiền tố là getXXX
		// Sẽ trả về (return) dữ liệu -? String
		// getText()/ getCurrentUrl/ getTitle/ getCssValue/ getAttribute/ getLocation/
		// getPosition/...
		// Dùng để kiểm tra dữ liệu thực tês (actual result) bằng với dữ liệu mong muốn/
		// mong đợi (expected result)
		// Assert (JUnit/ TestNG/ AsserJ/ Hamcrest/...)

		// Nhóm 3 - Kiểm tra dữ liệu
		// Dùng để kiểm tra tính đúng đắng của dữ liệu (true/ false) -> Hàm Assert
		// Nó sẽ bắt đầu bằng tiền tố là isXXX
		// Sẽ trả về dữ liệu -> boolean
		// isDisplay/ isEnabled/ isSelected/ isMultiple/...
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}