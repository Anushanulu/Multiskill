package MulSkill_IN_main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MulSkill_IN.Step02_ResponeAdd_IN;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import lib.Excel;


public class AddResponse_IN
{
	// TestNG logger

	
	
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
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
		//to display  browser logs in console
       // ((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        
		//System.setProperty("webdriver.chrome.driver","C:\\Chrome driver\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 12, 2);
		driver.get(url);   
		driver.manage().window().maximize();
	}	



	// Test to add response
	@Test(priority=0)
	public void add_response() throws IOException, InterruptedException
	{
	
		Step02_ResponeAdd_IN addresp = new Step02_ResponeAdd_IN(driver); 
		addresp.login();
		addresp.openRequest();
		
		/*for (int i=1; i<5; i++)
		{
			

				addresp.FillRespdetails("fResp"+i , "Canada" , "lResp"+i , "Canada" , "30", "10");
			
		}*/
		

		addresp.FillRespdetails("Skill 001","fResp1" , "mResp1" , "lResp1", "India" , "10", "10","RefNum1");
	

		addresp.Submit();
	}
	@Test(priority=1)
	public void add_response2() throws IOException, InterruptedException
	{
	
		Step02_ResponeAdd_IN addresp2 = new Step02_ResponeAdd_IN(driver); 
		//addresp2.login();
		//addresp2.openRequest();
		
		/*for (int i=1; i<5; i++)
		{
			

				addresp.FillRespdetails("fResp"+i , "Canada" , "lResp"+i , "Canada" , "30", "10");
			
		}*/
		
		addresp2.skill2();
		addresp2.FillRespdetails("Skill 002","fResp2" , "mResp2" , "lResp2", "India" , "10", "10","RefNum2");
	

		addresp2.Submit2();
	
}
}




