package tests;

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

public class AddressBookPrintMultipleAddressBookTest {

	private WebDriver driver;

	@BeforeMethod
    @Parameters("port")
	public void setUp(String port) {
        driver = DriverProvider.getInstance().getDriver();
		//driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://localhost:"+port+"/addressbook/index.php");
	}

	@Test
	public void testAddressBookPrintMultipleAddressBook() throws Exception {
		driver.findElement(By.name("user")).sendKeys("admin");
		driver.findElement(By.name("pass")).sendKeys("secret");
		driver.findElement(By.xpath(".//*[@id='content']/form/input[3]")).click();
		driver.findElement(By.linkText("print all")).click();
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*firstname1[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*lastname1[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*address1[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*01056321[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*mail1@mail\\.it[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*11[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*June[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[1]")).getText()
				.matches("^[\\s\\S]*1981[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*firstname2[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*lastname2[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*address2[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*01056322[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*mail2@mail\\.it[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*12[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*June[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[2]")).getText()
				.matches("^[\\s\\S]*1982[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*firstname3[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*lastname3[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*address3[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*01056323[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*mail3@mail\\.it[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*13[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*June[\\s\\S]*$"));
		assertTrue(driver.findElement(By.xpath(".//*[@id='view']/tbody/tr/td[3]")).getText()
				.matches("^[\\s\\S]*1983[\\s\\S]*$"));
	}

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

}
