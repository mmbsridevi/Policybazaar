package TestCases;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
 
 
public class Baseclass {
    //Author:Sri Devi, Rishasri
	//Date of Creation:28/04/2024
	//Date of Modification:03/05/2024
 
		 static WebDriver driver;		
		  @BeforeClass
		  @Parameters({"url","browser"})
		  	public void beforeClass(String URL,String browser) throws InterruptedException, IOException {
			 if(browser.equalsIgnoreCase("chrome"))
			 {
				 driver = new ChromeDriver();
			 }
			 else  
			 {
				 driver=new EdgeDriver(); 
			 }
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 driver.get(URL);
			 driver.manage().window().maximize();
			 driver.manage().deleteAllCookies();
		     }
		  
		  @AfterClass
		  public void afterClass() throws IOException {
			 driver.quit();
		  }
		  
		  public static String captureScreen(String tname) throws IOException {
				String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
				File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				String targetFilePath=System.getProperty("user.dir")+"\\ScreenShot\\" + tname + "_" + timeStamp + ".png";
				File targetFile=new File(targetFilePath);
				FileUtils.copyFile(sourceFile, targetFile);
				sourceFile.renameTo(targetFile);
				return targetFilePath;
		 
		}
	}