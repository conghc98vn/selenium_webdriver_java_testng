package webdriver;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_32_Wait_Fluent {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	String hueCity = "hue.jpg";
	String nhatrangCity = "nhatrang.jpg";
	String quynhonCity = "quynhon.jpg";

	String hueCityPath = projectPath + File.separator + "uploadFiles" + File.separator + hueCity;
	String nhatrangCityPath = projectPath + File.separator + "uploadFiles" + File.separator + nhatrangCity;
	String quynhonCityPath = projectPath + File.separator + "uploadFiles" + File.separator + quynhonCity;

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
	public void TC_01_Element_Found() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		explicitWait = new WebDriverWait(driver, 3);

		// Dùng cả 2 loại nhưng element được tìm thấy
		// Không có ngoại lệ xảy ra - happy case
		driver.get("https://www.facebook.com/");

		// Implicit
		System.out.println("1 - Start: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("2 - End: " + getDateTimeNow());

		// Explicit
		System.out.println("1 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email")));
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_02_Element_Not_Found_Only_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");

		// Implicit
		// Không tim Element
		// Đánh fail testcase tại step findElement
		// Chờ hết time của implicit
		System.out.println("1 - Start: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#automation"));
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_03_Element_Not_Found_Only_Explicit() {
		explicitWait = new WebDriverWait(driver, 3);

		driver.get("https://www.facebook.com/");

		// Explicit
		// Chờ hết 10s rồi đánh fail testcase tại step dòng code số 75
		// Show ra exception
		System.out.println("1 - Start: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#automation")));
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_04_Element_Not_Found_Implicit_Explicit_Equal() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");

		// Mấu chốt: Luôn luôn ưu tiên implicit trước
		// Tìm được element rồi mới làm gì thì làm

		// Explicit
		// Bị áp dụng cả 2 laoij wait trong step này
		// 10s của implicit cho findElement
		// 10s của explicit cho điều kiện
		System.out.println("1 - Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_05_Element_Not_Found_Implicit_Less_Than_Explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");

		// Mấu chốt: Luôn luôn ưu tiên implicit trước
		// Tìm được element rồi mới làm gì thì làm

		// Explicit
		// Bị áp dụng cả 2 laoij wait trong step này
		// 10s của implicit cho findElement
		// 10s của explicit cho điều kiện
		System.out.println("1 - Start: " + getDateTimeNow());
		try {
			// Việc findElement (apply implicit timeout nó nằm trong hàm cua
			// explicit(visiblilityOfElementLocated))
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_06_Element_Not_Found_Implicit_Greater_Than_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");

		// Mấu chốt: Luôn luôn ưu tiên implicit trước
		// Tìm được element rồi mới làm gì thì làm

		// Explicit
		// Bị áp dụng cả 2 laoij wait trong step này
		// 10s của implicit cho findElement
		// 10s của explicit cho điều kiện
		System.out.println("1 - Start: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input#email")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@Test
	public void TC_07_() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 10);
		driver.get("https://www.facebook.com/");

		// Mấu chốt: Luôn luôn ưu tiên implicit trước
		// Tìm được element rồi mới làm gì thì làm

		// Explicit
		// Bị áp dụng cả 2 laoij wait trong step này
		// 10s của implicit cho findElement
		// 10s của explicit cho điều kiện
		System.out.println("1 - Start: " + getDateTimeNow());
		try {
			// Explicit có những hàm có tham số là By/ WebElement (cùng 1 chức năng như nhau)
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#email"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("2 - End: " + getDateTimeNow());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

}