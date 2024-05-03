package groupTest;

import org.testng.annotations.Test;

public class Payment {
	@Test(groups = { "user", "other" })
	public void TC_01() {

	}

	@Test(groups = "user")
	public void TC_02() {

	}

	@Test(groups = "user")
	public void TC_03() {

	}

	@Test(groups = "user")
	public void TC_04() {

	}
}
