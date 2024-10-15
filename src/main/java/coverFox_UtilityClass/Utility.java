package coverFox_UtilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility 
{
	public static void TakeScreenShot(WebDriver driver, String fileName) throws IOException
	{
		String date = new SimpleDateFormat("HHmmss").format(new Date());
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File distination = new File(System.getProperty("user.dir")+"\\ScreenShot\\"+fileName+""+date+".jpg");
		FileHandler.copy(source, distination);
	}
	
	public static String ExcelReading(String path, String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream file = new FileInputStream(path);
		String value = WorkbookFactory.create(file).getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return value;
	}

	public static String ReadDataFromPropertyFile(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\Config.properties");
		prop.load(file);
		String value = prop.getProperty(key);
		return value;
	}

}
