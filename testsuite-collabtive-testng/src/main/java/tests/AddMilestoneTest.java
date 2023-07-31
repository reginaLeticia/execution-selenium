package tests;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.DriverProvider;
import utils.Properties;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddMilestoneTest {

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
	public void testCollabtiveAddMilestone() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("add_butn")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Milestone001");
		driver.switchTo().frame("desc_ifr");
		driver.findElement(By.tagName("body")).click();
		driver.findElement(By.tagName("body")).sendKeys("Description of Milestone001");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
		assertEquals("Milestone001",driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]/td[2]/div/a")).getText());
		driver.findElement(By.linkText("Milestone001")).click();
		assertTrue(driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div")).getText().matches("^[\\s\\S]*Description of Milestone001$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

}
