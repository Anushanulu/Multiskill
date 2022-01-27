package MulSkill_US_main;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MulSkill_US.Step1_CreateRequest_US;
import lib.Excel;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import lib.Excel;


public class CreateRequest_US {
	
	//public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath =System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	//public static String xlsFilePath = System.getProperty("./src/TnM_Review_Core/src/main/resources/testdata/testdata.xls");
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

		//ProfilesIni ini = new ProfilesIni();
		//FirefoxProfile profile = ini.getProfile("default");
		//WebDriver driver =  new FirefoxDriver(profile);

		driver = new FirefoxDriver();
	       driver.manage().window().maximize();
	       driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);

		url = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		


		driver.get(url); 
		driver.manage().window().maximize();
	}	

  @Test(priority=0)
	public void RIPC_Login() throws IOException, InterruptedException 
	{

	  Step1_CreateRequest_US login = new Step1_CreateRequest_US(driver);
		login.login();
	}
//  @Test(priority=1)
//	public void switching_insta() 
//	{
//
//	  Step1_CreateRequest_US insta = new Step1_CreateRequest_US(driver);
//		insta.switch_instance();
//	}
  @Test(priority=2)
 	public void create_req() 
 	{

 	  Step1_CreateRequest_US req = new Step1_CreateRequest_US(driver);
		req.Create_New_Request();
 	}
  @Test(priority=3)
 	public void switch_cntry() 
 	{

 	  Step1_CreateRequest_US cntry = new Step1_CreateRequest_US(driver);
 	   cntry.switch_country();
 	}
  @Test(priority=4)
	public void skill_req() 
	{

	  Step1_CreateRequest_US skillreq = new Step1_CreateRequest_US(driver);
		skillreq.Skill_Request();
		
	}		
@Test(priority=5)
	public void chang_requestor() 
	{

	Step1_CreateRequest_US chngreq = new Step1_CreateRequest_US(driver);
	  chngreq.Change_Requestor();
		
	}	
@Test(priority=6)
	public void select_requestor() 
	{

	Step1_CreateRequest_US selectreq = new Step1_CreateRequest_US(driver);
	  selectreq.Select_Requestor();
		
	}
@Test(priority=7)
	public void select_jrss() 
	{

	Step1_CreateRequest_US jrss = new Step1_CreateRequest_US(driver);
		jrss.Select_JRSS();
		
	}
@Test(priority=8)
	public void req_details() 
	{

	Step1_CreateRequest_US reqdetials = new Step1_CreateRequest_US(driver);
	  reqdetials.Request_detailpage();
		
	}
@Test(priority=9)
	public void loc_details() 
	{

	Step1_CreateRequest_US loc = new Step1_CreateRequest_US(driver);
		loc.Skill_detailLocationpage();
		
	}
@Test(priority=10)
	public void alert_call() 
	{

	Step1_CreateRequest_US call = new Step1_CreateRequest_US(driver);
	   call.isAlertPresent();
		
	}
@Test(priority=11)
	public void rates_details() 
	{

	Step1_CreateRequest_US rate = new Step1_CreateRequest_US(driver);
	   rate.Skill_detail_skillpricepage();
		
	}
@Test(priority=12)
	public void sup_slection() throws InterruptedException 
	{

	Step1_CreateRequest_US sup = new Step1_CreateRequest_US(driver);
		sup.SupplierSelectionPage();
		
	}				

}

		