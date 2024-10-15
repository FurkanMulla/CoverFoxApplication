package coverFox_TestClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import coverFox_BaseClass.Base;
import coverFox_POMClass.CoverFoxAddressDetails;
import coverFox_POMClass.CoverFoxHealthPlan;
import coverFox_POMClass.CoverFoxHomePage;
import coverFox_POMClass.CoverFoxMemberDetails;
import coverFox_POMClass.CoverFoxResult;
import coverFox_UtilityClass.Utility;

public class TC123_CoverFox_ValidateErrorMsgsOfPinAndMobile extends Base
{
	CoverFoxHomePage homePage;
	CoverFoxHealthPlan healthPlan;
	CoverFoxMemberDetails memberDetails;
	CoverFoxAddressDetails addressDetails;
	CoverFoxResult result;
	
	String excelPath = System.getProperty("user.dir")+"\\ExcelSheet\\data.xlsx";	
	String sheetName= "Sheet4";
	public static Logger logger;		
		
	@BeforeMethod
	public void enterDetails() throws InterruptedException, EncryptedDocumentException, IOException
	{
		logger= Logger.getLogger("Coverfox_Log");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Launching application");
		launchApplication();
		
		homePage= new CoverFoxHomePage(driver);
		healthPlan = new CoverFoxHealthPlan(driver);
		memberDetails = new CoverFoxMemberDetails(driver);
		addressDetails = new CoverFoxAddressDetails(driver);
		result = new CoverFoxResult(driver);
		
		logger.info("clicking on gender button");
		homePage.clickOnGender();
		Thread.sleep(1000);
		healthPlan.clickOnNextButton();
		logger.info("clicking on gender button");
		Thread.sleep(1000);
		memberDetails.selectAge(Utility.ExcelReading(excelPath, sheetName, 0, 0));
		logger.info("selecting age from dropdown");
		memberDetails.clickOnNextButton();
		logger.info("clicking on next button");
		Thread.sleep(1000);	
	}
	
	@Test(priority = -1)
	public void validatePincodeErrorMsg() throws EncryptedDocumentException, IOException 
	{
		logger.info("Entering mobile no.");
		addressDetails.enterMobNum(Utility.ExcelReading(excelPath, sheetName, 0, 2));
		logger.info("clicking on continue button");
		addressDetails.clickOnContinueButton();
		logger.warn("Error message displayed for PincodeField");
		String actualErrorMsg = addressDetails.errorMsgPincode();
		String expectedErrorMsg = Utility.ExcelReading(excelPath, sheetName, 0, 3);
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg , "ErrorMsg not matched with Pincode, TC failed");
	}
	@Test
	public void validateMobileErrorMsg() throws EncryptedDocumentException, IOException 
	{
		logger.info("Entering pincode");
		addressDetails.enterPincode(Utility.ExcelReading(excelPath, sheetName, 0, 1));		
		logger.info("clicking on continue button");
		addressDetails.clickOnContinueButton();		
		logger.warn("Error message displayed for MobileField");
		String actualErrorMsg = addressDetails.errorMsgMobile();
		String expectedErrorMsg = Utility.ExcelReading(excelPath, sheetName, 0, 4);
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg , "ErrorMsg not matched with Mobile, TC failed");
	}
	
	@AfterMethod
	public void closeApplication()
	{
		closeBrowser();
		logger.info("closing browser");
	}

}
