package coverFox_TestClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;

import coverFox_BaseClass.Base;
import coverFox_POMClass.CoverFoxAddressDetails;
import coverFox_POMClass.CoverFoxHealthPlan;
import coverFox_POMClass.CoverFoxHomePage;
import coverFox_POMClass.CoverFoxMemberDetails;
import coverFox_POMClass.CoverFoxResult;
import coverFox_UtilityClass.Utility;

public class TC123_CoverFox_ValidateMembersAfterUpdate extends Base
{
	CoverFoxHomePage homePage;
	CoverFoxHealthPlan healthPlan;
	CoverFoxMemberDetails memberDetails;
	CoverFoxAddressDetails addressDetails;
	CoverFoxResult result;
	
	String excelPath = System.getProperty("user.dir")+"\\ExcelSheet\\data.xlsx";	
	String sheetName= "Sheet4";
	public static Logger logger;

		
	@BeforeClass
	public void openApplication() throws IOException, InterruptedException
	{	
		logger= Logger.getLogger("Coverfox_Log");
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Launching application");
		launchApplication();
		
	}
	
	@BeforeMethod
	public void enterDetails() throws InterruptedException, EncryptedDocumentException, IOException
	{
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
		Thread.sleep(500);
		memberDetails.selectAge(Utility.ExcelReading(excelPath, sheetName, 0, 0));
		logger.info("selecting age from dropdown");
		memberDetails.clickOnNextButton();
		logger.info("clicking on next button");
		Thread.sleep(1000);
		addressDetails.enterPincode(Utility.ExcelReading(excelPath, sheetName, 0, 1));
		logger.warn("Entering pincode");
		addressDetails.enterMobNum(Utility.ExcelReading(excelPath, sheetName, 0, 2));
		logger.warn("Entering mobile no.");
		addressDetails.clickOnContinueButton();
		logger.info("clicking on continue button");
		Thread.sleep(4000);		
		
	}
	
	@Test
	public void validateUpdatedMembers() throws InterruptedException, EncryptedDocumentException, IOException 
	{
		String existingMember = result.members();
		logger.info("Getting Member Details");
		result.viewMemberDetails();
		result.editMemberDetails();
		Thread.sleep(500);
		healthPlan.clickOnWifeButton();
		healthPlan.clickOnNextButton();
		Thread.sleep(500);
		logger.info("Adding Spouce Details");
		memberDetails.selectSpouceAge(Utility.ExcelReading(excelPath, sheetName, 1, 0));
		memberDetails.clickOnNextButton();
		addressDetails.clickOnContinueButton();
		String updatedMember = result.members();
		logger.info("Getting Updated Member Details");
		Thread.sleep(1000);
		Assert.assertNotEquals(existingMember, updatedMember, "both members details matching, TC failed");
		logger.info("Veryfied updated members");
	}
	
	@AfterClass
	public void closeApplication()
	{
		closeBrowser();
		logger.info("closing browser");
	}

}
