package SeleniumProject.SeleniumAutoDemo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import resources.BaseTest;

public class SelectProductTC extends BaseTest {
	public WebDriver driver;

	@BeforeClass
	public void setup() throws InterruptedException, IOException {
		driver = initializers();
	}

	@Test
	public void test001_select_all_the_filter() throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		Thread.sleep(2000);
		WebElement staticddfilter = objHomePage.ddfilter();
		Select ddfilter = new Select(staticddfilter);
		ddfilter.selectByVisibleText("Name (A to Z)");
		Thread.sleep(2000);

		ddfilter.selectByVisibleText("Name (Z to A)");
		Thread.sleep(2000);
	}

	@Test
	public void test002_click_on_the_any_product() throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		objHomePage.divproduct().click();
		Thread.sleep(2000);
		Assert.assertEquals(objHomePage.btnaddtocart().getText(), "Add to cart");
	}

	@Test
	public void test003_click_on_the_add_to_cart_button() throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		objHomePage.btnaddtocart().click();
		Thread.sleep(2000);
		Assert.assertEquals(objHomePage.btnremove().getText(), "Remove");
	}

	@Test
	public void test004_click_on_the_cart_button() throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		objHomePage.btncart().click();
		Thread.sleep(2000);
		Assert.assertEquals(objHomePage.btncheckout().getText(), "Checkout");
	}

	@Test
	public void test005_click_on_the_checkout_button() throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		objHomePage.btncheckout().click();
		Thread.sleep(3000);
		Assert.assertEquals(objHomePage.checklblcheckout().getText(), "Checkout: Your Information");
	}

	@Test
	public void test006_enter_the_firstname_lastname_and_zipcode_and_click_on_the_continue_button()
			throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		objHomePage.txtfirstname().sendKeys("Nitesh");
		objHomePage.txtlastname().sendKeys("Kumar");
		objHomePage.txtpostalcode().sendKeys("563728");
		Thread.sleep(2000);
		objHomePage.btncontinue().click();
		Thread.sleep(2000);
		Assert.assertEquals(objHomePage.btnfinish().getText(), "Finish");
	}

	@Test
	public void test007_click_on_the_finish_button() throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		objHomePage.btnfinish().click();
		Thread.sleep(2000);
		Assert.assertEquals(objHomePage.btnbackhome().getText(), "Back Home");
	}

	@Test
	public void test008_click_on_the_back_home_button() throws InterruptedException {
		HomePage objHomePage = new HomePage(driver);
		objHomePage.btnbackhome().click();
		Thread.sleep(2000);
		Assert.assertEquals(objHomePage.checklblhome().getText(), "Products");
	}
}
