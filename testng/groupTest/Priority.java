package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Priority {
	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
	}

	@Test
	public void TC_01_Create_New_User() {
		System.out.println("Test case 01");
	}

	@Test
	public void TC_02_View_Existing_User() {
		System.out.println("Test case 02");
	}

	@Test
	public void TC_03_Update_User() {
		System.out.println("Test case 03");
	}

	@Test
	public void TC_04_Move_User() {
		System.out.println("Test case 04");
	}

	@Test
	public void TC_05_Delete_User() {
		System.out.println("Test case 05");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class");
	}
}
