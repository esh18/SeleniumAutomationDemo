package SeleniumProject.SeleniumAutoDemo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import resources.BaseTest;

public class LoginTC extends BaseTest {
	public WebDriver driver;

	@BeforeClass
	public void setup() throws InterruptedException, IOException {
		driver = initializers();
	}

	@Test
	public void test001_check_that_The_login_succesfully() throws InterruptedException {
		LoginPage objLoginPage = new LoginPage(driver);
		Thread.sleep(3000);
		Assert.assertEquals("Products", objLoginPage.checktxt().getText());
	}

	@Test
	public void test002_click_on_the_sandwitch_menu_and_logout_button() throws InterruptedException {
		LoginPage objLoginPage = new LoginPage(driver);
		objLoginPage.btnmenu().click();
		Thread.sleep(1000);
		objLoginPage.optlogout().click();
		Thread.sleep(1000);
	}

	@Test
	public void test003_check_the_logout_is_successfully_happened() {
		LoginPage objLoginPage = new LoginPage(driver);
		Assert.assertEquals("Swag Labs", objLoginPage.checklbl().getText());
	}

	@Test(dataProvider = "logindata")
	public void test004_enter_the_username_and_password_and_click_on_the_login_button(String username, String password)
			throws InterruptedException {
		LoginPage objLoginPage = new LoginPage(driver);
		objLoginPage.txtusername().sendKeys(username);
		objLoginPage.txtpassword().sendKeys(password);
		Thread.sleep(1000);
		objLoginPage.btnlogin().click();
		Thread.sleep(3000);
		Assert.assertEquals("Products", objLoginPage.checktxt().getText());
		objLoginPage.btnmenu().click();
		Thread.sleep(1000);
		objLoginPage.optlogout().click();
		Thread.sleep(1000);
		Assert.assertEquals("Swag Labs", objLoginPage.checklbl().getText());
	}

	@DataProvider
	public Object[][] logindata() {
		Object[][] logindata = new Object[5][2];

		logindata[0][0] = "standard_user";
		logindata[0][1] = "secret_sauce";

		logindata[1][0] = "problem_user";
		logindata[1][1] = "secret_sauce";

		logindata[2][0] = "performance_glitch_user";
		logindata[2][1] = "secret_sauce";

		logindata[3][0] = "error_user";
		logindata[3][1] = "secret_sauce";

		logindata[4][0] = "visual_user";
		logindata[4][1] = "secret_sauce";

		return logindata;
	}
}
