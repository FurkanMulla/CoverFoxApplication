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

public class TC123_CoverFox_ValidateAgeErrorMsg extends Base
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
		Reporter.log("Open application",true);
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
		
		Reporter.log("click on gender",true);
		logger.info("clicking on gender button");
		homePage.clickOnGender();
		Thread.sleep(1000);
		Reporter.log("click on next",true);
		healthPlan.clickOnNextButton();
		logger.info("clicking on gender button");
		Thread.sleep(1000);
		Reporter.log("click on next again",true);
		logger.info("clicking on next button");	
		memberDetails.clickOnNextButton();
		Thread.sleep(1000);
		
	}
	
	@Test
	public void validateAgeErrorMsg() throws EncryptedDocumentException, IOException 
	{
		logger.warn("getting an error on AgeFiled");
		String actualError = memberDetails.ageErrorMsg();
		String expectedError = Utility.ExcelReading(excelPath, sheetName, 0, 5);
		Assert.assertEquals(actualError, expectedError,"ErrorMsg not matching with agefield");
	}
	
	@AfterClass
	public void closeApplication()
	{
		Reporter.log("close applicaion",true);
		closeBrowser();
		logger.info("closing browser");
	}

}
