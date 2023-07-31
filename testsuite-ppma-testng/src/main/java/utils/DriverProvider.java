package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverProvider {

    private static DriverProvider ourInstance = new DriverProvider();

    private DriverProvider(){
    	System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
    	//WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.silentOutput", "true");
        Logger.getLogger("org.openqa.selenium.remote").setLevel(Level.OFF);
    }

    public WebDriver getDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.setBinary("/opt/google/chrome-beta/google-chrome-beta");
       //chromeOptions.setBinary("/opt/googleold/chrome/google-chrome");
        
        //if(Boolean.valueOf(Properties.getInstance().getProperty("headless_browser"))){
            chromeOptions.addArguments("--no-sandbox", "enable-automation", "--disable-gpu", "--headless", "--window-size=1200x600");
        //}
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        return driver;
    }
    
    public WebDriver getRemoteWebDriver(String port) {
    	return getDriver();
    	/*ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox", "--headless", "--deterministic-mode", "--disable-gpu", "--window-size=1920x1080");
        
        try {
        	return new RemoteWebDriver(new URL("http://localhost:"+port+"/wd/hub"), chromeOptions);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
        //*/
    }
    
    

    public static DriverProvider getInstance(){
        return ourInstance;
    }
}
