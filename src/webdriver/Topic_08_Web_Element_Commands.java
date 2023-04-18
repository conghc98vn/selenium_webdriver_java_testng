package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Web_Element_Commands {
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
	public void TC_01_WebElement() {
		// Element: textbox/ dropdown/ checkbox/ radio/ link/ text/...

		// 1 - Chỉ tương tác lên element này 1 lần (không khai báo biến)
		driver.findElement(By.id("send2"));

		// 2 - Element này dùng lại nhiều lần chỉ trong trang hiện tại (khai báo biến)
		WebElement loginButton = driver.findElement(By.id("send2"));
		loginButton.isDisplayed();
		loginButton.getCssValue("");

		loginButton.click();

		List<WebElement> textboxes = driver.findElements(By.xpath("//input[@type='text']"));

		// Xoá dữ liệu trong 1 textbox/ textarea/ dropdown (edit table)
		driver.findElement(By.id("")).clear();

		// Nhập dữ liệu vào 1 textbox/ textarea/ dropdown (edit table)
		driver.findElement(By.id("")).sendKeys("test");

		driver.findElement(By.xpath("//div")).findElement(By.xpath("//a"));

		driver.findElement(By.xpath("//div//a"));

		driver.findElement(By.xpath("//a")).getAttribute("placeholder");

		driver.findElement(By.xpath("//a")).getAttribute("value");

		// GUI: Font/ size/ color/ position/ location/...
		driver.findElement(By.id("search")).getCssValue("background-color");
		driver.findElement(By.id("search")).getCssValue("font-size");
		driver.findElement(By.id("search")).getCssValue("font-family");

		// Kích thước của element cao/ rộng
		Dimension loginButtonSize = driver.findElement(By.xpath("")).getSize();

		// Lấy ra toạ độ bên ngoài so với độ phân giải màn hình
		Point loginButtonLocation = driver.findElement(By.xpath("")).getLocation();

		Rectangle loginButtonRect = driver.findElement(By.id("")).getRect();
		loginButtonSize = loginButtonRect.getDimension();
		loginButtonLocation = loginButtonRect.getPoint();

		// Report HTML + Take Screenshow
		File screenshotFile = driver.findElement(By.xpath("")).getScreenshotAs(OutputType.FILE);
		String screenshotBase64 = driver.findElement(By.xpath("")).getScreenshotAs(OutputType.BASE64);

		// Lấy ra tên thẻ khi dùng các loại locator mà không biết trước tên thẻ là gì
		String searchTextboxTagname = driver.findElement(By.xpath("")).getTagName();

		// Đầu ra của một element trên sẽ là đầu vào của một elemnt dưới
		driver.findElement(By.xpath("//" + searchTextboxTagname + "[@id='email']"));

		// Lấy ra text của chính nó và các thẻ con của nó
		String benifitText = driver.findElement(By.xpath("")).getText();

		// Áp dụng cho tất cả các element
		// 1 element có hiển thị trên màn hình hay không
		// Nhìn thầy được/ có kích thước cụ thể
		driver.findElement(By.xpath("")).isDisplayed();

		// Áp dụng cho tất cả element
		// 1 element có thể thao tác lên được không (không bị disable) = read only
		driver.findElement(By.xpath("")).isEnabled();

		// Áp dụng cho 3 loại element: checkbox/ radio/ dropdown (Select)
		// 1 element đã được chọn rồi hay chưa
		driver.findElement(By.xpath("")).isSelected();

		// Chỉ apple cho cái form/ element trong form
		// Thay thể cho hành vi click vào button Login/ Register/ Search
		driver.findElement(By.xpath("")).submit();
	}

	@Test
	public void TC_01_() {
	}

	@Test
	public void TC_02_() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}