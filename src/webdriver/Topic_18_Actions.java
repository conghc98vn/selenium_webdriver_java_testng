package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Actions {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	WebDriverWait explicitWait;
	JavascriptExecutor executor;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		action = new Actions(driver);
		executor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Hover_Tooltips() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();

		// setTimeout(() => {debugger;},3000)

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
	}

	@Test
	public void TC_02_Hover_To_Element_Myntra() {
//		driver.get("http://www.myntra.com/");
//		
//		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
//		
//		sleepInSecond(1);
//		
//		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
//		
//		sleepInSecond(1);
//		
//		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());

		// banned automation
	}

	@Test
	public void TC_03_Hover_To_Element_Fahasa() {
		driver.get("https://www.fahasa.com/");
		// skip because overlap by pop-up
	}

	@Test
	public void TC_04_Click_And_Hold_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> listElement = driver.findElements(By.xpath("//ol/li"));

		action.clickAndHold(listElement.get(0)).moveToElement(listElement.get(3)).release().perform();

		sleepInSecond(1);

		List<WebElement> listElementSelected = driver
				.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(listElementSelected.size(), 4);
	}

	@Test
	public void TC_05_Click_And_Hold_Element_Random_Items() {

		Keys key = null;
		if (osName.contains("Windows")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}

		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> listElement = driver.findElements(By.xpath("//ol/li"));

		action.keyDown(key).click(listElement.get(1)).click(listElement.get(6)).click(listElement.get(8)).perform();

		sleepInSecond(1);

		action.keyDown(key);

		sleepInSecond(1);

		List<WebElement> listElementSelected = driver
				.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(listElementSelected.size(), 3);
	}

	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By doubeClickButton = By.xpath("//button[text()='Double click me']");
		executor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(doubeClickButton));

		action.doubleClick(driver.findElement(doubeClickButton)).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		By doubeClickButton = By.xpath("//span[text()='right click me']");
		By menuForm = By.xpath("//ul[contains(@class, 'context-menu-root')]");

		action.contextClick(driver.findElement(doubeClickButton)).perform();
		Assert.assertTrue(driver.findElement(menuForm).isDisplayed());
		action.moveToElement(driver.findElement(By.xpath("//li/span[contains(text(), 'Quit')]"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class, 'hover')]")).isDisplayed());
	}
	
	@Test
	public void TC_07_Drap_And_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		By drapable = By.xpath("//div[@id='draggable']");
		By dropTarget = By.xpath("//div[@id='droptarget']");
		
		action.dragAndDrop(driver.findElement(drapable), driver.findElement(dropTarget)).perform();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(dropTarget).getText(), "You did great!");
		Assert.assertEquals(driver.findElement(dropTarget).getCssValue("background-color"), "rgb(3, 169, 244)");
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