package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor executor;

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
		executor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Defaul_Checkbox_Radio() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By dualzoneCheckbox = By.xpath("//input[@id='eq5']");

		driver.findElement(dualzoneCheckbox).click();

		Assert.assertTrue(driver.findElement(dualzoneCheckbox).isSelected());

		driver.findElement(dualzoneCheckbox).click();

		Assert.assertFalse(driver.findElement(dualzoneCheckbox).isSelected());

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

		By twoPetrol147kW = By.xpath("//input[@id='engine3']");

		if (!driver.findElement(twoPetrol147kW).isSelected()) {
			driver.findElement(twoPetrol147kW).click();
		}
	}

	@Test
	public void TC_02_Defaul_Checkbox_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");

		By summerRadio = By.xpath("//input[@id='mat-radio-4-input']");

		if (!driver.findElement(summerRadio).isSelected()) {
			driver.findElement(summerRadio).click();
		}

		driver.get("https://material.angular.io/components/checkbox/examples");

		By checkedCheckbox = By.xpath("//input[@id='mat-mdc-checkbox-1-input']");
		By indeterminateCheckbox = By.xpath("//input[@id='mat-mdc-checkbox-2-input']");

		driver.findElement(checkedCheckbox).click();
		driver.findElement(indeterminateCheckbox).click();

		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

		driver.findElement(checkedCheckbox).click();
		driver.findElement(indeterminateCheckbox).click();

		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
	}

	@Test
	public void TC_03_Custom_Checkbox_Radio_WebTiemchung() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");

		By dangKyChoNguoiThanRadioButton = By.xpath("//input[@id='mat-radio-3-input']");

		executor.executeScript("arguments[0].click();", driver.findElement(dangKyChoNguoiThanRadioButton));

		Assert.assertTrue(driver.findElement(dangKyChoNguoiThanRadioButton).isSelected());

	}

	@Test
	public void TC_04_Custom_Checkbox_Radio_GoogleDocs() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		By canThoRadioButton = By.xpath("//div[@data-value='Cần Thơ']");

		Assert.assertTrue(driver.findElement(canThoRadioButton).isDisplayed());

		if (driver.findElement(canThoRadioButton).getAttribute("aria-checked").contains("false")) {
			executor.executeScript("arguments[0].click();", driver.findElement(canThoRadioButton));
		}

		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@data-value='Cần Thơ'][@aria-checked='true']")).isDisplayed());

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