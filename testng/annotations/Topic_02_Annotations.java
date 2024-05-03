package annotations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Annotations {
	@Test(dataProvider = "dp")
	public void f(Integer n, String s) {
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println(" Method");

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before ");

	}

	@AfterClass
	public void afterClass() {
		System.out.println("After ");

	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before ");

	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test");

	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");

	}

}
