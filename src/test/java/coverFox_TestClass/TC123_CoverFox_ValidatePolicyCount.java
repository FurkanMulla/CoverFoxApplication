package coverFox_TestClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
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

public class TC123_CoverFox_ValidatePolicyCount extends Base
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
		logger.info("clicking on gender button");
		homePage.clickOnGender();
		Thread.sleep(1000);
		
		healthPlan = new CoverFoxHealthPlan(driver);
		healthPlan.clickOnNextButton();
		logger.info("clicking on gender button");
		Thread.sleep(1000);
		
		memberDetails = new CoverFoxMemberDetails(driver);
		memberDetails.selectAge(Utility.ExcelReading(excelPath, sheetName, 0, 0));
		logger.info("selecting age from dropdown");
		memberDetails.clickOnNextButton();
		logger.info("clicking on next button");
		Thread.sleep(1000);
		addressDetails = new CoverFoxAddressDetails(driver);
		addressDetails.enterPincode(Utility.ExcelReading(excelPath, sheetName, 0, 1));
		logger.warn("Entering pincode");
		addressDetails.enterMobNum(Utility.ExcelReading(excelPath, sheetName, 0, 2));
		logger.warn("Entering mobile no.");
		addressDetails.clickOnContinueButton();
		logger.info("clicking on continue button");
		Thread.sleep(4000);		
		
	}
	
	@Test
	public void validatePolicyCount() 
	{
		result = new CoverFoxResult(driver);
		int textCount = result.matchingHealthPlans();
		int bannerCount = result.totalDivision();
		logger.info("Validating result");
		Assert.assertEquals(textCount, bannerCount, "Text count is not matching with Banner count, TC is failed");
	//	Assert.fail();
		
	}
	
	@AfterClass
	public void closeApplication()
	{
		Reporter.log("Closing Application",true);
		closeBrowser();
		logger.info("closing browser");
	}

}
