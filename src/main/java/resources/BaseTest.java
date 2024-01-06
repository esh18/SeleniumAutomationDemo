package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;

	public WebDriver initializers() throws IOException, InterruptedException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver",
			// System.getProperty("user.dir") + "\\WebDriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// System.setProperty("webdriver.firefox.driver",
			// System.getProperty("user.dir") + "\\WebDriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			// System.setProperty("webdriver.edge.driver",
			// System.getProperty("user.dir") + "\\WebDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(prop.getProperty("Url"));
		LoginPage objLoginPage = new LoginPage(driver);
		objLoginPage.txtusername().sendKeys("standard_user");
		objLoginPage.txtpassword().sendKeys("secret_sauce");
		Thread.sleep(1000);
		objLoginPage.btnlogin().click();
		return driver;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		String t = java.time.LocalTime.now().toString().replaceAll("[^a-zA-Z0-9]", " ");
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationfile = System.getProperty("user.dir") + "\\reports\\screenshot" + testCaseName + " " + t
				+ ".png";
		FileUtils.copyFile(source, new File(destinationfile));
		return destinationfile;
	}

	@AfterClass
	public void cleanup() {
		driver.close();
		driver.quit();
	}

}