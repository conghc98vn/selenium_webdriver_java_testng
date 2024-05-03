package dataDriven;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTesting {

	@Test(dataProvider = "user")
	public void TC_01(String firstParanm, String secondParam) {
		System.out.println(firstParanm);
		System.out.println(secondParam);
	}

	@Test(dataProvider = "admin")
	public void TC_02(String firstParanm, String secondParam) {
		System.out.println(firstParanm);
		System.out.println(secondParam);
	}

	@DataProvider(name = "user")
	public Object[][] getUserData() {
		return new Object[][] { new Object[] { "1", "a" }, new Object[] { "2", "b" }, };
	}

	@DataProvider(name = "admin")
	public Object[][] getAdminData() {
		return new Object[][] { new Object[] { "3", "c" }, new Object[] { "4", "d" }, };
	}
}
