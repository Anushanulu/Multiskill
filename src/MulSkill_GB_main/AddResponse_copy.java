
package MulSkill_GB_main;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MulSkill_GB.ResponseCreation_copy;
import MulSkill_GB.Step1_CreateRequest_GB;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import lib.Excel;




public class AddResponse_copy
{
	// TestNG logger

	public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath =System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd;
	public String id_green;
	public String paswd_green;
	public String url_green;
	public String url2;

	public WebDriver driver;


	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();

		//id = Excel.getCellValue(xlsFilePath, sheet, 2, 0);
		//paswd = Excel.getCellValue(xlsFilePath, sheet, 2, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 4, 2);

		//String url1 = "https://" +  id + ":" + paswd + "@" + url;

		driver.get(url);  
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}	



	// Test to add response
	@Test(priority=0)
	public void RIPC_Login() throws IOException, InterruptedException 
	{

		ResponseCreation_copy addresp = new ResponseCreation_copy(driver); 
	    addresp.login();
	}
	@Test(priority=1)
	public void search_request() throws InterruptedException 
	{

		ResponseCreation_copy search = new ResponseCreation_copy(driver); 
		search.openRequest();
		//search.waitForFormOverlayDisappear();
	}

	@Test(priority=2)
 	public void create_res() throws InterruptedException, IOException 
 	{
		
		ResponseCreation_copy formFill = new ResponseCreation_copy(driver);
		
		//formFill.alertcall();
		
		for (int i=14;i<=100;i++)
		{
	 
		
			
			
			formFill.buttonClick();
			 
			formFill.addAttachement();
			
			formFill.resDetails("fresp"+ i, "mresp"+ i, "lresp"+ i);
			
			formFill.resAttributeDetails();
			
			formFill.resRates(String.valueOf(i), String.valueOf(i));
			
			formFill.saveForm();
		}
	  
	}

	}
