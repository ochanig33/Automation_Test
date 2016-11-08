	package com.test;

	import java.io.IOException;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	import com.ideator.common.Ideator_Setup;
	//import com.ideator.common.Ideator_Setup;
	import com.ideator.page.AdminPage;
	import com.ideator.page.Homepage;
	import com.ideator.page.LoginPage;
	import com.ideator.page.SuperAdminPage;

	public class Admin extends Ideator_Setup {

		Homepage homepage;
		LoginPage loginPage;
		AdminPage adminPage;
		SuperAdminPage superAdminPage;

		@BeforeClass
		public void setup() throws IOException, InterruptedException {
			super.setup();
			loginPage = new LoginPage(driver);
			adminPage = new AdminPage(driver);
			superAdminPage = new SuperAdminPage(driver);
		}

		@Test(priority = 1)
		// Add user to community and verify that on mailinator site.
		public void Login() throws InterruptedException {
			loginPage.submitLoginCredential();
		}


}
