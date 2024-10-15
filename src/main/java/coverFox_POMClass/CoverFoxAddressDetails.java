package coverFox_POMClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class CoverFoxAddressDetails 
{
	@FindBy(className = "mp-input-text") private WebElement pincode;
	@FindBy(id = "want-expert") private WebElement mobNum;
	@FindBy(className = "next-btn") private WebElement continueButton;
	@FindBy(xpath = "//div[text()=' Please enter a valid pincode ']")
	private WebElement errorPin;
	@FindBy(xpath = "//div[text()=' Please enter a valid mobile no. ']")
	private WebElement errorMobile;
	
	public CoverFoxAddressDetails(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enterPincode(String pin) 
	{
		Reporter.log("Entering Pincode", true);
		pincode.sendKeys(pin);
	}
	public String errorMsgPincode()
	{
		String errorMsg = errorPin.getText();
		return errorMsg;
	}
	
	public void enterMobNum(String mno) 
	{
		Reporter.log("Entering Mobile No.", true);
		mobNum.sendKeys(mno);
	}
	public String errorMsgMobile()
	{
		String errorMsg = errorMobile.getText();
		return errorMsg;
	}
	
	public void clickOnContinueButton()
	{
		Reporter.log("Clicking on Continue Button", true);
		continueButton.click();
	}

}
