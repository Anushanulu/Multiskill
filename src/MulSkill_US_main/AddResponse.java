
package MulSkill_US_main;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MulSkill_US.ResponseCreation;
import MulSkill_US.Step1_CreateRequest_US;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import lib.Excel;




public class AddResponse
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

		ResponseCreation addresp = new ResponseCreation(driver); 
	    addresp.login();
	}
	@Test(priority=1)
	public void search_request() throws InterruptedException 
	{

		ResponseCreation search = new ResponseCreation(driver); 
		search.openRequest();
		//search.waitForFormOverlayDisappear();
	}
	@Test(priority=2)
	public void alertmininmize() throws InterruptedException
	{
		ResponseCreation alertcall = new ResponseCreation(driver); 
		alertcall.alertcall();
	} 
	@Test(priority=3)
 	public void create_res() throws InterruptedException 
 	{

	  ResponseCreation btnclick = new ResponseCreation(driver);
	  //btnclick.waitForFormOverlayDisappear();
	  btnclick.buttonClick();
 	}
	 
	
	
  @Test(priority=4)
 	public void add_Attachement() 
 	{

	  ResponseCreation attachemnt = new ResponseCreation(driver); 
	  attachemnt.addAttachement();
 	}
  @Test(priority=5)
	public void fill_resdetails() throws IOException, InterruptedException  
	{
	  ResponseCreation resdetails = new ResponseCreation(driver); 
	  resdetails.resDetails("fresp", "mresp", "lresp");
	  //resdetails.resDetails();
	}
  @Test(priority=6)
	public void fill_resAttrdetails() throws IOException, InterruptedException  
	{
	  ResponseCreation resattrs = new ResponseCreation(driver); 
	  resattrs.resAttributeDetails();
	}
  @Test(priority=7)
	public void fill_ratesdetails() throws IOException, InterruptedException  
	{
	  ResponseCreation rates = new ResponseCreation(driver); 
	  rates.resRates();
	}
  @Test(priority=8)
	public void save_Form() throws IOException, InterruptedException  
	{
	  ResponseCreation save = new ResponseCreation(driver); 
	  save.saveForm();
	}
}
