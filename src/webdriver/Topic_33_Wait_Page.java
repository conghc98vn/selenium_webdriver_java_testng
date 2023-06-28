package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_33_Wait_Page {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentWait;
	Actions action;

	long durationTime = 30;
	long pollingTime = 1;

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
		action = new Actions(driver);

		explicitWait = new WebDriverWait(driver, 10);
	}

//	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://admin-demo.nopcommerce.com/login");

		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Assert.assertTrue(waitForAjaxIconInvisible());
		isPageLoadedSuccess();

		driver.findElement(By.xpath("//a[text()='Logout']")).click();

		isPageLoadedSuccess();

		Assert.assertEquals(driver.getTitle(), "Your store. Login");
	}

	@Test
	public void TC_02_TestProject() {
		driver.get("https://blog.testproject.io/");

		action.moveToElement(driver.findElement(By.xpath("//section[@id]//input[@type='search']"))).perform();

		driver.findElement(By.xpath("//section[@id]//input[@type='search']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id]//span[@class='glass']")).click();

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='page-title']")));
		isPageLoadedSuccess();

		List<WebElement> listTitle = driver.findElements(By.xpath("//h3[@class='post-title']/a"));

		for (WebElement webElement : listTitle) {
			Assert.assertTrue(webElement.getText().contains("Selenium"));
		}
	}

	public boolean waitForAjaxIconInvisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#jaxBusy")));
	}

	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
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