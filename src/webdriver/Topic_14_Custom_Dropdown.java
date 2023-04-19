package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Custom_Dropdown {
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown("//span[@id='speed-button']", "//li[@class='ui-menu-item']/div", "Medium");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(),
				"Medium");
	}

	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdown("//div[@id='root']", "//div[@id='root']//span[@class='text']", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");
	}

	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdown("//li[@class='dropdown-toggle']", "//li/a", "Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}

	@Test
	public void TC_04_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown("//div/input", "//div[@role='listbox']/div", "Bangladesh");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Bangladesh");
	}

	public void selectItemInCustomDropdown(String xpathParent, String xpathChild, String expectedText) {
		// Click vào một thẻ để xổ ra hết các item
		driver.findElement(By.xpath(xpathParent)).click();
		sleepInSecond(1);

		// Chờ cho tất cả các imtem được load ra hết -> trong vòng 30s
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));

		// Lấy hết tất cả các item trong dropdown lưu vào List
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));

		// Duyệt qua từng item
		for (WebElement tempElement : allItems) {
			String itemText = tempElement.getText();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
			sleepInSecond(1);

			if (itemText.equals(expectedText)) {
				tempElement.click();
				sleepInSecond(1);

				// Thoát khỏi vòng lặp
				break;
			}
		}
	}

	public void selectItemInEditableDropdown(String xpathTextbox, String xpathChild, String expectedText) {
		// Click vào một thẻ để xổ ra hết các item
		driver.findElement(By.xpath(xpathTextbox)).sendKeys(expectedText);
		sleepInSecond(1);

		// Chờ cho tất cả các imtem được load ra hết -> trong vòng 30s
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));

		// Lấy hết tất cả các item trong dropdown lưu vào List
		List<WebElement> allItems = driver.findElements(By.xpath(xpathChild));

		// Duyệt qua từng item
		for (WebElement tempElement : allItems) {
			String itemText = tempElement.getText();
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tempElement);
			sleepInSecond(1);

			if (itemText.equals(expectedText)) {
				tempElement.click();
				sleepInSecond(1);

				// Thoát khỏi vòng lặp
				break;
			}
		}
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