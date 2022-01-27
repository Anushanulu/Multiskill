package MulSkill_IN_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import MulSkill_IN.Step01_CreateRequest_IN;

import org.apache.log4j.Logger;

import lib.Excel;

public class CreateRequestAuth_IN {
	// TestNG logger

	public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet = "Login";
	public String url;
	public String id;
	public String paswd;
	public String id_green;
	public String paswd_green;
	public String url_green;
	public String url2;

	public WebDriver driver;

	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		/*System.setProperty("webdriver.chrome.driver","C:\\Users\\SriSwathiAnushaNulu\\Documents\\Softwares\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();*/
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project
		// csa\\Softwares\\chromedriver_win32\\chromedriver.exe");
		// driver = new ChromeDriver();

		// id = Excel.getCellValue(xlsFilePath, sheet, 12, 0);
		// paswd = Excel.getCellValue(xlsFilePath, sheet, 12, 1);
		url = Excel.getCellValue(xlsFilePath, sheet, 11, 2);

		// String url1 = "https://" + id + ":" + paswd + "@" + url;

		driver.get(url);
		driver.manage().window().maximize();
	}

	// test to Login to the application as RIPC
	@Test(priority = 0)
	public void RIPC_Login() throws InterruptedException {

		Step01_CreateRequest_IN login = new Step01_CreateRequest_IN(driver);
		login.login();
	}

	@Test(priority = 1)
	public void create_req() {

		Step01_CreateRequest_IN req = new Step01_CreateRequest_IN(driver);
		req.Create_New_Request();
	}

	@Test(priority = 2)
	public void skill_req() {

		Step01_CreateRequest_IN skillreq = new Step01_CreateRequest_IN(driver);
		skillreq.Skill_Request();
	}

	@Test(priority=3)
	public void select_requestor() 
	{

		 Step01_CreateRequest_IN selectreq = new Step01_CreateRequest_IN(driver);
		 selectreq.Select_Requestor();
	 }

	@Test(priority = 4)
	public void select_jrss() {

		Step01_CreateRequest_IN jrss = new Step01_CreateRequest_IN(driver);
		jrss.Select_JRSS();

	}

	@Test(priority = 5)
	public void req_details() {

		Step01_CreateRequest_IN reqdetials = new Step01_CreateRequest_IN(driver);
		reqdetials.Request_detailpage();

	}

	@Test(priority = 6)
	public void loc_details() {

		Step01_CreateRequest_IN loc = new Step01_CreateRequest_IN(driver);
		loc.Skill_detailLocationpage();

	}

	@Test(priority = 7)
	public void alert_call() {

		Step01_CreateRequest_IN call = new Step01_CreateRequest_IN(driver);
		call.isAlertPresent();

	}

	@Test(priority = 8)
	public void rates_details() {

		Step01_CreateRequest_IN rate = new Step01_CreateRequest_IN(driver);
		rate.Skill_detail_skillpricepage();

	}

	@Test(priority = 9)
	public void sup_slection() {

		Step01_CreateRequest_IN sup = new Step01_CreateRequest_IN(driver);
		sup.SupplierSelectionPage();

	}

	@Test(priority = 10)
	public void selecting_jrss2() throws InterruptedException {

		Step01_CreateRequest_IN jrss = new Step01_CreateRequest_IN(driver);
		jrss.Select_JRSS_2();

	}

	@Test(priority = 11)
	public void submit_req() throws InterruptedException {

		Step01_CreateRequest_IN submit = new Step01_CreateRequest_IN(driver);

		submit.subreq();

	}

}
