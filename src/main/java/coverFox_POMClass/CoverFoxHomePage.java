package coverFox_POMClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxHomePage 
{
	//variable declaration
	@FindBy(xpath = "//div[text()='Male']") private WebElement gender;
		
	//variable initialization using constructor
	public CoverFoxHomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//methods
	public void clickOnGender()
	{
		Reporter.log("Clicking on gender", true);
		gender.click();
	}
	

}
