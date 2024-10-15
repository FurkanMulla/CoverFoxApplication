package coverFox_POMClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxHealthPlan 
{
	@FindBy(className = "next-btn") private WebElement nextButton;
	@FindBy(xpath = "//div[text()='Wife']") private WebElement spouse;
	
	public CoverFoxHealthPlan(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnNextButton()
	{
		Reporter.log("Clicking on Next button", true);
		nextButton.click();
	}
	public void clickOnWifeButton()
	{
		spouse.click();
	}
	

}
