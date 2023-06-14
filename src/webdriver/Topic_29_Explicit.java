package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Explicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

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

		explicitWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_() {
		driver.get("https://www.facebook.com/");

		// Chờ cho 1 attribute có value
		// Dùng trước hàm getAtrribute()
		explicitWait
				.until(ExpectedConditions.attributeContains(By.id("login_username"), "placeholder", "số điện thoại"));
		explicitWait.until(
				ExpectedConditions.attributeContains(By.id("login_username"), "placeholder", "Nhập số điện thoại"));

		// Chờ cho 1 element có thể được click hay không: button/ checkbox/ radio/ link/
		// image
		// Dùng trước hàm click()
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fhs-btn-login")));

		// Chờ cho 1 element đã được chọn hay chưa: checkbox/ radio
		// Dùng trước khi apply isSelected()
		explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("input[name='sex']")));

		// Chờ cho frame xuất hiện và swtich vào frame đó
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));

		// Chờ cho 1 element không còn visible nữa
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

		// Chờ cho nhiều element không còn visible nữa
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));

		// Var Arguments
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector(""))));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElement(By.cssSelector("")),
				driver.findElement(By.cssSelector(""))));

		// Chờ cho các element nó có tổng số lượng là bao nhiều
		// Bằng
		explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 3));

		// Ít hơn
		explicitWait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(""), 3));

		// Nhiều hơn
		explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(""), 3));

		// Lấy ra số lượng element bằng bao nhiêu
		int radioNumber = driver.findElements(By.cssSelector("")).size();

		// Thao tác và nó bật ra các tab/ window
		// Chờ cho bao nhiều cửa sổ/ tab được xuất hiện
		boolean windowActive = explicitWait.until(ExpectedConditions.numberOfWindowsToBe(4));

		// Chờ cho element nó có trong HTML (không cần quan tâm visible hay không)
		// Dropdown (item)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

		WebElement loginUsernameTextbox = explicitWait.until(
				ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("parent"), By.cssSelector("child")));

		List<WebElement> loginTextboxes = explicitWait.until(ExpectedConditions
				.presenceOfNestedElementsLocatedBy(By.cssSelector("parent"), By.cssSelector("child")));

		// Chờ cho 1 element không còn trong HTML nữa
		explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

		// Chờ và verify cho 1 element không còn trong HTML nữa
		Assert.assertTrue(explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("")))));

		// Trước hàm getText()
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "value"));
		explicitWait.until(ExpectedConditions.textToBePresentInElementValue(By.cssSelector(""), "value"));
		explicitWait.until(ExpectedConditions.textToBePresentInElementValue(By.cssSelector(""), "value"));

		// Dùng trước hàm getTitle()
		explicitWait.until(ExpectedConditions.titleContains("Fahasa.com"));
		explicitWait.until(ExpectedConditions.titleIs("https://fahasa.com"));

		// Dùng trước hàm getCurrentUrl()
		explicitWait.until(ExpectedConditions.urlContains("hasa.com"));
		explicitWait.until(ExpectedConditions.urlToBe("https://fahasa.com"));

		// Chờ cho 1 element được hiển thị
		WebElement element = driver.findElement(By.cssSelector(""));
		explicitWait.until(ExpectedConditions.visibilityOf(element));

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

		// Chờ cho nhiều element được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath(""))));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("")));

		// Chờ cho 1 element được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(By.cssSelector("parent"),
				By.cssSelector("child")));
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