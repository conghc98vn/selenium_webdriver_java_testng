package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Wait_FindElement {
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
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com/");

		// 1 - Tìm thấy có đúng duy nhất 1 element
		// Nó sẽ không cần phải chờ hết time
		driver.findElement(By.xpath("//input[@id='email']"));

		// 2 - Không tim thấy element nào hết
		// Nó sẽ có cơ chế tìm lại (mỗi nửa giây 0.5s tìm lại 1 lần)
		// 1- Tìm lại mà thấy element thì trả về element đó - không cần tìm tiếp nữa
		// 2 - Tìm lại vẫn không thấy và hết timeout
		// Sẽ đánh fail testcase tại chính step đó
		// Ném ra (throw) 1 ngoại lệ: NoSuchElementException (không tìm thấy element)
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']"));

		// 3- Tìm thấy nhiều hơn 1 element
		// Lấy ra element đầu tiên nếu như có nhiều hơn 1 element
		// Khi mình thao tác với 1 element nào đó -> Tối ưu ở phần locator trước
		driver.findElement(By.xpath("//input[@id='email' or @id='pass']")).sendKeys("Testing");
	}

	@Test
	public void TC_02_FindElements() {
		driver.get("https://facebook.com/");

		List<WebElement> elements;

		// 1 - Tìm thấy có đúng duy nhất 1 element
		// Nó sẽ không cần phải chờ hết time
		elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Duy nhất 1 element: " + elements.size());

		// 2 - Không tim thấy element nào hết
		// Nó sẽ có cơ chế tìm lại (mỗi nửa giây 0.5s tìm lại 1 lần)
		// 1- Tìm lại mà thấy element thì trả về element đó - không cần tìm tiếp nữa
		// 2 - Tìm lại vẫn không thấy và hết timeout
		// Sẽ không đánh fail testcase (vẫn chạy tiếp step tiếp theo)
		// Không ném ra ngoại lệ
		// Trả về 1 List rỗng (0 element)
		elements = driver.findElements(By.xpath("//a[@data-testid='open-registration-form-button']"));
		System.out.println("Không có element: " + elements.size());
		
		// 3- Tìm thấy nhiều hơn 1 element
		// Nó sẽ không cần phải chờ hết thời gian
		// Trả về 1 list chứa tất cả các element được tìm thấy
		elements = driver.findElements(By.xpath("//input[@id='email' or @id='pass']"));
		System.out.println("Nhiều element" + elements.size());
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