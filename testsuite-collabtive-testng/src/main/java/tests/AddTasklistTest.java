package tests;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DriverProvider;
import utils.Properties;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddTasklistTest {

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
    @Parameters({"port", "browserPort", "host"})
	public void setUp(String port, String browserPort, String host) throws Exception {
		driver = DriverProvider.getInstance().getRemoteWebDriver(browserPort);
		wait = new WebDriverWait(driver, 10);
		/*driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);*/
		driver.get("http://"+host+":" + port + "/collabtive/");
	}

	@Test
	public void testCollabtiveAddTasklist() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.id("addtasklists")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Tasklist001");
		Thread.sleep(3000);
		new Select(driver.findElement(By.id("milestone"))).selectByVisibleText("Milestone001");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		assertEquals("Tasklist001",driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[4]/h2/a")).getText());
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.linkText("Milestone001")).click();
		assertEquals("Tasklist001", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/ul/li/div/table/tbody/tr[2]/td/span/a")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

}
