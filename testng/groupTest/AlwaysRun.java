package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlwaysRun {
	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
	}

	@Test(enabled = false) // enable bằng false thì Testcase này sẽ skip
	public void TC_01_Create_New_User() {
		System.out.println("Test case 01");
	}

	@Test
	public void TC_02_View_Existing_User() {
		System.out.println("Test case 02");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("Alway run to quit browser");
	}
}
