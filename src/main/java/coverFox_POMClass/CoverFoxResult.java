package coverFox_POMClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxResult 
{
	@FindBy(xpath = "//div[contains(text(),'matching Health')]") private WebElement matchingText;
	@FindAll({@FindBy(id = "plans-list")})  private List<WebElement> totalResult;
	@FindBy( xpath = "//div[text()=' View member details ']") private WebElement viewMemberDetails;
	@FindBy(xpath = "//button[text()=' Edit Details ']") private WebElement editDetails;
	@FindBy(xpath = "(//div[contains(text(),' You')])[1]") private WebElement members;
	public CoverFoxResult(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public int matchingHealthPlans()
	{
		String t1 = matchingText.getText();
		String t2 = t1.substring(0, 2);
		int matchingData = Integer.parseInt(t2);
		Reporter.log("Getting count from result text", true);
		return matchingData;
	}
	
	public int totalDivision()
	{
		int division=totalResult.size();
		Reporter.log("Getting count from banner", true);
		return division;
	}
	
	public void viewMemberDetails()
	{
		viewMemberDetails.click();
	}
	public void editMemberDetails()
	{
		editDetails.click();
	}
	public String members()
	{
		String memberNames = members.getText();
		return memberNames;
	}

}
