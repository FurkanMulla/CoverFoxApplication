package coverFox_BaseClass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;

import coverFox_UtilityClass.Utility;

public class Base 
{
	protected static WebDriver driver;
	
	
	public void launchApplication() throws IOException, InterruptedException
	{
		ChromeOptions co = new ChromeOptions();
		co.addArguments("-disable-notifications");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get(Utility.ReadDataFromPropertyFile("url"));
		Reporter.log("Launching coverfox application", true);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		Reporter.log("Waiting...", true);
	}
	public void closeBrowser()
	{
		Reporter.log("Closing appication",true);
		driver.quit();
		
	}
	

}
