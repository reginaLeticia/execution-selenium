package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverProvider;

import static org.testng.AssertJUnit.*;

public class AAllSortedTests {

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
    //@Parameters({"port", "browserPort", "host"})
	public void setUp() throws Exception {
		String browserPort = "3000";
		driver = DriverProvider.getInstance().getRemoteWebDriver(browserPort);
		wait = new WebDriverWait(driver, 10);
		/*driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);*/
		driver.get("http://localhost:3000/collabtive/");
	}

	@Test(priority = 0)
	public void testCollabtiveAddProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[@id='contentwrapper']/div[1]/ul/li/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("add_butn_myprojects")).click();
		Thread.sleep(500);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Project001");
		driver.findElement(By.tagName("body")).click();
		Thread.sleep(500);
		driver.findElement(By.tagName("body")).sendKeys("Description for "+"Project001");
		driver.findElement(By.id("budget")).clear();
		driver.findElement(By.id("budget")).sendKeys("10000");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(1000);
		assertEquals("Project001", driver.findElement(By.linkText("Project001")).getText());
		driver.findElement(By.linkText("Project001")).click();
		Thread.sleep(1000);
		assertEquals("Budget: 10000",driver.findElement(By.xpath( "html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/ul/li[3]/a")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test(priority = 1)
	public void testCollabtiveEditProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//a[@title='"+"Project001"+"']/../../../td[5]/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("end")).clear();
		driver.findElement(By.id("end")).sendKeys("24.12.2019");
		driver.findElement(By.linkText("Close")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"form_addmyproject\"]/div/form/fieldset/div[6]/button[1]")).click();
		driver.findElement(By.id("edit_butn")).click();
		assertEquals("24.12.2019", driver.findElement(By.id("end")).getAttribute("value"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test(priority = 3)
	public void testCollabtiveSearchProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.id("query")).clear();
		driver.findElement(By.id("query")).sendKeys("Project001");
		driver.findElement(By.cssSelector("fieldset > button[type=\"submit\"]")).click();
		assertEquals("Results (1)", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[1]/h2")).getText());
		assertEquals("Project001", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/ul/li/div/table/tbody/tr[2]/td/span/a")).getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveCloseProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//a[@title='"+"Project001"+"']/../../../td[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.id("donebutn")).click();
		Thread.sleep(1000);
		assertEquals("Project001", driver.findElement(By.linkText("Project001")).getText());
		assertEquals("Open", driver.findElement(By.xpath(".//a[@title='"+"Project001"+"']/../../../td[1]/a")).getAttribute("title"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveSearchClosedProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.id("query")).clear();
		driver.findElement(By.id("query")).sendKeys("Project001");
		driver.findElement(By.cssSelector("fieldset > button[type=\"submit\"]")).click();
		assertEquals("Results (0)", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[1]/h2")).getText());
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*"+"Project001"+"[\\s\\S]*$"));
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveOpenProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.navigate().refresh();
		driver.findElement(By.id("donebutn")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//a[@title='"+"Project001"+"']/../../../td[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		assertEquals("Project001",driver.findElement(By.xpath(".//a[@title='"+"Project001"+"']/../../../td[2]/div/a")).getText());
		assertEquals("Close", driver.findElement(By.xpath(".//a[@title='"+"Project001"+"']/../../../td[1]/a")).getAttribute("title"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
		//}
	}

	@Test
	public void testCollabtiveAddRole() throws Exception {
		Thread.sleep(1000);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("add_butn_myprojects")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
		driver.findElement(By.xpath("(//input[@id='name'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@id='name'])[2]")).sendKeys("Project001");
		driver.findElement(By.name("permissions_projects[add]")).click();
		driver.findElement(By.name("permissions_projects[edit]")).click();
		driver.findElement(By.name("permissions_projects[del]")).click();
		driver.findElement(By.name("permissions_projects[close]")).click();
		driver.findElement(By.cssSelector("div.row-butn-bottom > button[type=\"submit\"]")).click();
		assertEquals("Project001", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[1]/td[2]/div/a")).getText());
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
		//}
	}

	@Test
	public void testCollabtiveEditRole() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[1]/td[2]/div/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("rolename")).clear();
		driver.findElement(By.id("rolename")).sendKeys("NewRole");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[3]/div[3]/input")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[3]/div[5]/input")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[2]/td[2]/div[2]/form/fieldset/div[5]/button[1]")).click();
		assertEquals("NewRole",driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[1]/td[2]/div/a")).getText());
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[6]/table/tbody[1]/tr[1]/td[2]/div/a")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
		//}
	}

	@Test
	public void testCollabtiveAddUser() throws Exception {
		int i =0;
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("add_butn_member")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("username001");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("username001@username.it");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("password001");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("roleselect"))).selectByIndex(1);
		driver.findElement(By.xpath("//*[@id='form_member']/div/form/fieldset/div[11]/div/button")).click();
		new Actions(driver).moveToElement(driver.findElement(By.xpath("(//table/tbody/tr[1]/td[2]/a/img)[1]"))).build().perform();
		driver.findElement(By.cssSelector("a.edit")).click();
		Thread.sleep(1000);
		assertEquals("username001", driver.findElement(By.id("name")).getAttribute("value"));
		assertEquals("username001@username.it", driver.findElement(By.id("email")).getAttribute("value"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
		//}
	}

	@Test
	public void testCollabtiveEditUserRole() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		new Actions(driver).moveToElement(driver.findElement(By.xpath("(//table/tbody/tr[1]/td[2]/a/img)[1]"))).build().perform();
		driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div/div/ul/li[1]/div/table/tbody/tr[1]/td[3]/div/a[1]")).click();
		new Select(driver.findElement(By.name("role"))).selectByVisibleText("NewRole");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		new Actions(driver).moveToElement(driver.findElement(By.partialLinkText("username001"))).build().perform();
		driver.findElement(By.cssSelector("a.edit")).click();
		assertEquals("NewRole", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[1]/form/fieldset/table/tbody/tr/td[2]/div/div/table/tbody[23]/tr/td[2]/select/option[1]")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveLoginUser() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("username001");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("password001");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath("//*[@id=\"mainmenue\"]/li[2]/a")).click();
		assertTrue(driver.findElement(By.cssSelector("body")).getText().matches("^[\\s\\S]*"+"username001"+"[\\s\\S]*$"));
		driver.findElement(By.xpath("//*[@id='mainmenue']/li[3]/a")).click();
	}

	@Test
	public void testCollabtiveAssignUserToProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[6]/a")).click();
		driver.findElement(By.id("add_butn_member")).click();
		Thread.sleep(3000);
		new Select(driver.findElement(By.id("addtheuser"))).selectByVisibleText("username001");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		assertEquals("username001", driver.findElement(By.partialLinkText("username001")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
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

	@Test
	public void testCollabtiveEditMilestone() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.cssSelector("a.tool_edit")).click();
		driver.findElement(By.id("end")).clear();
		driver.findElement(By.id("end")).sendKeys("20.03.2024");
		driver.findElement(By.linkText("Close")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
		assertEquals("20.03.2024", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]/td[3]")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveCloseMilestone() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.cssSelector("a.butn_check")).click();
		driver.findElement(By.id("donebutn")).click();
		Thread.sleep(1000);
		assertEquals("Open", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/a")).getAttribute("title"));
		assertEquals("Milestone001", driver.findElement(By.linkText("Milestone001")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveOpenMilestone() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("donebutn")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a.butn_checked")).click();
		assertEquals("Close", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]/td[1]/a")).getAttribute("title"));
		assertEquals("Milestone001", driver.findElement(By.linkText("Milestone001")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
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

	@Test
	public void testCollabtiveCloseTasklist() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='content-left-in']/div/div[4]/div/a[1]")).click();
		assertEquals("Open", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div/table/tbody/tr[1]/td[1]/a")).getAttribute("title"));
		assertEquals("Tasklist001", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div/table/tbody/tr[1]/td[2]/div/a")).getText());
		/* preconditions for RemoveMilestoneTest. */
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("donebutn")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a.butn_checked")).click();
		assertEquals("Close", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[1]/table/tbody/tr[1]/td[1]/a")).getAttribute("title"));
		assertEquals("Milestone001", driver.findElement(By.linkText("Milestone001")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveOpenTasklist() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.cssSelector("a.butn_check")).click();
		assertEquals("Tasklist001", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[4]/h2/a")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveAddAndRemoveMultipleTasks() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("mytask1");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("mytask2");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("mytask3");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*mytask1[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*mytask2[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*mytask3[\\s\\S]*$"));
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody[1]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody[2]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody[3]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*mytask1[\\s\\S]*$"));
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*mytask2[\\s\\S]*$"));
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*mytask3[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveAddTaskDesktopPresent() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("desktopTask");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[1]/a")).click();
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*desktopTask[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveTaskDesktopNotPresent() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.cssSelector("a.tool_del")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[1]/a")).click();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*desktopTask[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveAddTasks() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("Task001");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("Task002");
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[1]/div/form/fieldset/div[6]/button[1]")).click();
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Task001[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Task002[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}


	@Test
	public void testCollabtiveCloseTasks() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody[1]/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody[2]/tr[1]/td[1]/a")).click();
		driver.navigate().refresh();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[2]")).click();
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.partialLinkText("Task001")).getText().matches("^[\\s\\S]*Task001[\\s\\S]*$"));
		assertTrue(driver.findElement(By.partialLinkText("Task002")).getText().matches("^[\\s\\S]*Task002[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveOpenTasks() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/div/div/table/tbody[1]/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/div/div/table/tbody[2]/tr[1]/td[1]/a")).click();
		driver.navigate().refresh();
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Task001[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Task002[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveCloseTasksProjectPercentage() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("a.butn_check")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[1]/a")).click();
		assertEquals("50%", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div")).getText());
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.cssSelector("a.butn_check")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[1]/a")).click();
		assertEquals("100%", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveOpenTasksProjectPercentage() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/div/div/table/tbody[1]/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[1]/a")).click();
		assertEquals("50%",driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div")).getText());
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[3]/div/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/div/div/table/tbody[1]/tr[1]/td[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[1]/a")).click();
		assertEquals("0%",driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[2]/div")).getText());
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveAddUserAlreadyPresent() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("add_butn_member")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("username001");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("username001@username.it");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("password001");
		Thread.sleep(1000);
		new Select(driver.findElement(By.id("roleselect"))).selectByIndex(1);
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*The email address username001@username\\.it or the username username001 already exists in the user database\\.[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveAddUserEmptyValues() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("add_butn_member")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("username999");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().contains("Please enter valid values for"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*username999[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveAddAndRemoveLateMilestone() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.id("add_butn")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Milestone002");
		driver.findElement(By.id("end")).clear();
		driver.findElement(By.id("end")).sendKeys("20.03.2012");
		driver.findElement(By.linkText("Close")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		assertEquals("Milestone002",driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[2]/table/tbody/tr[1]/td[2]/div/a")).getText());
		assertEquals("20.03.2012", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div[1]/div[3]/div[2]/div[2]/table/tbody/tr[1]/td[3]")).getText());
		driver.findElement(By.cssSelector("tr.marker-late > td.tools > a.tool_del")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Milestone002[\\s\\S]*$"));
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*20\\.03\\.2012[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveAddMultipleProjects() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.id("add_butn_myprojects")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("myProject1");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		driver.findElement(By.id("add_butn_myprojects")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("myProject2");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		driver.findElement(By.id("add_butn_myprojects")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("myProject3");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject1[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject2[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject3[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}
	@Test
	public void testCollabtiveSearchMultipleProjects() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.id("query")).clear();
		driver.findElement(By.id("query")).sendKeys("myProject");
		driver.findElement(By.cssSelector("fieldset > button[type=\"submit\"]")).click();
		assertEquals("Results (3)", driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[1]/h2")).getText());
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject1[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject2[\\s\\S]*$"));
		assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject3[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveMultipleProjects() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody[2]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody[3]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody[4]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		Thread.sleep(1000);
		driver.navigate().refresh();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject1[\\s\\S]*$"));
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject2[\\s\\S]*$"));
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*myProject3[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveUserFromProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath("//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath("//*[@id='contentwrapper']/div[1]/ul/li[6]/a")).click();
		new Actions(driver).moveToElement(driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/div[2]/div/div/ul/li[2]/div/table/tbody/tr[1]/td[2]/a/img"))).build().perform();
		driver.findElement(By.xpath("(//table/tbody/tr[1]/td[3]/div/a[1])[2]")).click();
		new Select(driver.findElement(By.id("assignto"))).selectByVisibleText("admin");
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveTasks() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody[1]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div/div/div[5]/div[2]/table/tbody[2]/tr[1]/td[5]/a[2]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Task001[\\s\\S]*$"));
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Task002[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveTasklist() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='content-left-in']/div/div[4]/div/a[3]")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		assertTrue(driver.findElement(By.xpath("//*[@id=\"content-left-in\"]/div")).getText().contains("No tasklist found"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveMilestone() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.linkText("Project001")).click();
		driver.findElement(By.xpath("//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("a.tool_del")).click();
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
	}

	@Test
	public void testCollabtiveRemoveRole() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.cssSelector("a.tool_del")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*NewRole[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveUser() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.xpath(".//*[@id='contentwrapper']/div[1]/ul/li[2]/a")).click();
		new Actions(driver).moveToElement(driver.findElement(By.xpath("(//table/tbody/tr[1]/td[2]/a/img)[1]"))).build().perform();
		driver.findElement(By.cssSelector("a.del")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}

	@Test
	public void testCollabtiveRemoveProject() throws Exception {
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("admin");
		driver.findElement(By.cssSelector("button.loginbutn")).click();
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[3]/a")).click();
		driver.findElement(By.cssSelector("a.tool_del")).click();
		Thread.sleep(200);
		assertTrue(driver.switchTo().alert().getText().matches("^Really delete this item[\\s\\S]\nDeleting cannot be undone\\.$"));
		driver.switchTo().alert().accept();
		driver.navigate().refresh();
		assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*Project001[\\s\\S]*$"));
		driver.findElement(By.xpath(".//*[@id='mainmenue']/li[4]/a")).click();
	}


	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}

}
