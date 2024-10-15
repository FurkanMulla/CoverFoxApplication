package coverFox_POMClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class CoverFoxMemberDetails 
{
	@FindBy(id = "Age-You") private WebElement ageDropdown;
	@FindBy(css = "div.next-btn") private WebElement nextButton;
	@FindBy(xpath = "//div[text()=' Required ']") private WebElement ageError;
	@FindBy(id = "Age-Spouse")private WebElement spouceAgeDropdown;
	
	public CoverFoxMemberDetails(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void selectAge(String age) 
	{
		Reporter.log("Selecting Age", true);
		Select s = new Select(ageDropdown);
		s.selectByValue(age+"y");
	}
	
	public void clickOnNextButton()
	{
		Reporter.log("Clicking on Next button", true);
		nextButton.click();
	}
	
	public String ageErrorMsg()
	{
		String ageErrMsg = ageError.getText();
		return ageErrMsg;	
	}
	public void selectSpouceAge(String age)
	{
		Select s = new Select(spouceAgeDropdown);
		s.selectByValue(age+"y");
	}

}
