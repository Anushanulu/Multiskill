package MulSkill_US;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

//import generics.Screenshots;


public class Step1_CreateRequest_US {

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login";

	//Login to application
	@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
	@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
	@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;
	@FindBy ( id="btn_signin") private WebElement Signin_Button ;
	
	//switch switch instance
	@FindBy ( xpath = "//*[@id='left-nav']/div/a[7]") private WebElement switch_instance ;
	@FindBy ( id = "FLD_SELECT_INSTANCE") private WebElement select_instance ;
	@FindBy (xpath = ".//*[@name='btnGo'] ") private WebElement btnGo	;
	
	@FindBy (xpath = ".//*[@id='content-main']/form[1]/div/span/input") private WebElement Create_New_Request;
	
	//Switch from IND to GB
	@FindBy (id="FLD_SRC_CTRY") private WebElement Country_select;
	
	//Creating request
	@FindBy (id ="FLD_REQ_TYPE") private WebElement New_Request ;
	@FindBy ( id="FLD_NORMAL_TYPE1") private WebElement Time_Materials ;
	@FindBy (name = "btnContinue") private WebElement Continue ;
	
	//Change requestor
	@FindBy (xpath = ".//*[@value='Change requester'] ") private WebElement Change_req;
	@FindBy (xpath = ".//*[@id='FLD_EMP_WEB_ID'] ") private WebElement email_txtbox;
	@FindBy (xpath = ".//*[@name='BTN_GO'] ") private WebElement go_btn	;
	@FindBy (xpath = "//*[@id='content-main']/table[4]/tbody/tr[2]/td[1]/a ") private WebElement name_select	;
	@FindBy (xpath = ".//*[@value='Use as requester'] ") private WebElement Use_as_req;
	
	
	//Selecting Requestor
	@FindBy  (id = "FLD_REQST_CO") private WebElement  Requesting_Company ;
	@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
	
	//job role/skill
	@FindBy ( xpath= ".//*[@id='JRSS_SELECTION1']") private WebElement Priced_JRSS;
	@FindBy ( id = "FLD_JOB_ROLE" ) private WebElement Select_JobRole ;
	@FindBy ( id = "FLD_SKILL_SET") private WebElement Select_SkillSet;
	
	//Project Creation
	@FindBy ( id="FLD_PROJ_NAME") private WebElement Project_Name;
	@FindBy ( id="FLD_CONTACT_NAME") private WebElement Customer_Name;
	@FindBy (id="FLD_IS_FED_CONTRACT") private WebElement GlobalResource_No ;
	@FindBy ( id="FLD_CONTACT_NAME") private WebElement CustomerName_Refernce ;
	@FindBy ( xpath = ".//*[@id='FLD_IS_FED_CONTRACT']")  private WebElement Govt_FederalContract ;
	@FindBy ( id="FLD_CLIENT") private WebElement Client ;
	@FindBy ( id="FLD_BRAND") private WebElement Brand ;
	@FindBy ( id="FLD_SECTOR") private WebElement Sector ;
	@FindBy ( id="FLD_INDUSTRY") private WebElement Industry ;
	@FindBy ( id="fldRegulatedAcc1") private WebElement FDA ;
	@FindBy ( id="fldRegulatedAcc2") private WebElement FFIEC ;
	@FindBy ( id="fldRegulatedAcc4") private WebElement NREG ;
	@FindBy ( xpath = " .//*[@value='I'] " ) private WebElement Accounting_Type ;
	@FindBy ( xpath = ".//*[@name='btnAddContingentMgr']")  private WebElement Project_Task_Manager_AddButton ;;
	@FindBy ( xpath = ".//*[@name='FLD_EMP_WEB_ID']")  private WebElement Email_Id_TextBox ;
	@FindBy (xpath = ".//*[@id='content-main']/table[4]/tbody/tr[2]/td[1]/a" ) private WebElement Name ;
	@FindBy ( xpath = ".//input[@name='TEMP MANAGER FOR CR BUTTON']")  private WebElement UseAsManager_Button ;
	
	//Skill detail Location
	@FindBy ( id="FLD_WRK_LOC_STATE" ) private WebElement State_Region_Province;
	@FindBy ( id="FLD_WRK_LOC_CITY" ) private WebElement City ;
	@FindBy ( id="FLD_WRK_LOC" ) private WebElement Work_Location ;
	@FindBy ( name="Continue" ) private WebElement Continue_2 ;
	@FindBy ( xpath = "//*[@id='FLD_FLOW_DOWN_RESTRICTION']") private WebElement Flowdown_YesRadio ;
	@FindBy ( xpath = "//*[@id='FLD_FLOW_DOWN_RESTRICTION1']") private WebElement Flowdown_NoRadio ;

	//@FindBy (xpath = ".//*[@id='FLD_ABOVE_MATRIX_RATES']") private WebElement Above_Matrix_Rate;
	@FindBy (xpath = ".//*[@id='FLD_ABOVE_MATRIX_RATES0']") private WebElement Below_Matrix_Rate;
	
	//Skill detail skill price
	@FindBy ( id="FLD_RQSTD_SKILL_LVL" ) private WebElement Skill_Level;
	@FindBy (id="FLD_RQSTD_PRICE_LVL" )  private WebElement Price_Level;
	@FindBy (id="FLD_QTY_SKILL_NEEDED" ) private WebElement Quantity ;
	@FindBy (xpath =".//*[@id='FLD_ST']") private WebElement ST_time;
	@FindBy (xpath = "//*[@id='content-main']/form/table[8]/tbody/tr[2]/td[2]/select") private WebElement Core_skill_1;
	@FindBy (xpath = "//*[@id='content-main']/form/table[8]/tbody/tr[3]/td[2]/select") private WebElement Core_skill_2;
	@FindBy (xpath = "//*[@id='content-main']/form/table[8]/tbody/tr[4]/td[2]/select") private WebElement Core_skill_3;
	
	//Supplier selection page
	@FindBy ( xpath= "//input[@id='ALL_RADIAL_BUTTON']") private WebElement secondary_supp;
	@FindBy ( xpath= "//input[@id='PRIMARY_RADIAL_BUTTON']") private WebElement Primary_supp;
	@FindBy ( xpath= "//a[contains(text(), 'Deselect all')]") private WebElement Deselect_All;
	@FindBy ( xpath= "//*[contains(text(), 'WOI US TEST VENDER 1-A')]//preceding-sibling::input[@name='fldSuppliers']") private WebElement RadioButton_UStestVendor;
	@FindBy ( xpath= "//textarea[@id='FLD_SUPPWARN_SUPPSELJUST']") private WebElement SuppSelectionJustification;
	@FindBy ( xpath= "//*[@id='FLD_SUPPLIER_LIST1']") private WebElement Contract_selection;
	
	//Skill Summary Page
		@FindBy (xpath = ".//*[@value='Continue to request summary'] ") private WebElement ContinueToRequestSummary;
		@FindBy (xpath = ".//*[@value='Save as draft'] ") private WebElement SaveasDraft;
		@FindBy (name = "Submit request") private WebElement SubmitRequest;

	//OTP Screen code
		//@FindBy (xpath = ".//a[@class='delivery method-link' and @type='totp']") private WebElement Totp_Link;
		@FindBy (xpath = ".//div[@id='totp_item']") private WebElement Totp_Link;
//		@FindBy(id = "otp")	private WebElement passcodeBox;
//		@FindBy(id = "btn_submit")	private WebElement SubmitPasscode;
		
		@FindBy ( xpath= "//span[@id='credentialSignin']") private WebElement cred_login;
//		@FindBy ( xpath= "//input[@id='otp-input']") private WebElement otp_box;
//		 @FindBy(id = "submit_btn")    private WebElement otp_submit_btn;
		@FindBy ( xpath= "//input[@name='username']") private WebElement login_username;
		@FindBy ( xpath= "//input[@name='password']") private WebElement login_password;
		@FindBy ( id="login-button") private WebElement login_Button ;
		@FindBy(id = "otp-input")	private WebElement passcodeBox;
		@FindBy(id = "submit_btn")	private WebElement SubmitPasscode;
		@FindBy (xpath = "//label[@id='totp_label']") private WebElement Authenticator_App_option;
		@FindBy (xpath = "//input[@id='newTotp-otp-input']") private WebElement EnterAccessCode_Box;
		@FindBy (xpath = "//button[contains(text(),'Next: Verify')]") private WebElement Verify_Button;
		@FindBy (xpath = "//input[@id='otp-input']") private WebElement OTP_TextBox;
		@FindBy (xpath = "//button[@id='submit_btn']") private WebElement OTP_Submit_Button;

		@FindBy ( xpath= ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ") private WebElement RequestCreated;
		@FindBy ( xpath= ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ") private WebElement RequestCreated_Header;


	// Initialize the web elements 
	public Step1_CreateRequest_US(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	// Function to login to the application
	public void login() throws IOException, InterruptedException{

//		WebDriverWait wait = new WebDriverWait(driver, 180);
//		wait.until(ExpectedConditions.visibilityOf(loginToContractor_Link));
//
//		loginToContractor_Link.click();
		WebDriverWait wait00 = new WebDriverWait(driver, 180);
		wait00.until(ExpectedConditions.visibilityOf(cred_login));
		cred_login.click();
		
		//new login changes
		WebDriverWait wait01 = new WebDriverWait(driver, 180);
		wait01.until(ExpectedConditions.visibilityOf(login_Button));
		wait01.until(ExpectedConditions.elementToBeClickable(login_Button));
		login_username.clear();
		login_username.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));
		Thread.sleep(1000);
		login_password.clear();
		login_password.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));

		//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
		login_Button.click();
		
		try {

			Totp_Link.click();
			//String otpKeyStr = "47UORWOB2YBVMXQE"; // <- this 2FA secret key for csatestus2@c25a0161.toronto.ca.ibm.com
			String otpKeyStr = "WEIBVLBVWMVAZZPZ";// csatestus1@c25a0161.toronto.ca.ibm.com 
			Totp totp = new Totp(otpKeyStr);
			String twoFactorCode = totp.now();
			
			

			OTP_TextBox.sendKeys(twoFactorCode);
			System.out.println("value fetched from box= "+OTP_TextBox.getAttribute("value"));
		
			OTP_Submit_Button.click();
			System.out.println("clicked on OTP submit button");
		}
		catch (Exception e) {
			System.out.println("no OTP screen");
			//e.printStackTrace();
		}	
	}
	//switch instance
//		public void switch_instance()
//		{
//			WebDriverWait wait02 = new WebDriverWait(driver, 180);
//			wait02.until(ExpectedConditions.visibilityOf(switch_instance));
//			switch_instance.click();
//			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
//			Select cntry = new Select(select_instance);
//			cntry.selectByVisibleText("Americas");	
//			btnGo.click();
//		}
		//Create Request
		public void Create_New_Request()
		
		{
			WebDriverWait wait03 = new WebDriverWait(driver, 180);
			wait03.until(ExpectedConditions.visibilityOf(Create_New_Request));
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Create_New_Request.click();

		}
		
		public void switch_country()
		{
			WebDriverWait wait04 = new WebDriverWait(driver, 180);
			wait04.until(ExpectedConditions.visibilityOf(Country_select));
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Select cntry1 = new Select(Country_select);
			cntry1.selectByVisibleText("United States");
		}
		
		//Creating a request
		public void Skill_Request(){

			WebDriverWait wait05 = new WebDriverWait(driver, 180);
			wait05.until(ExpectedConditions.visibilityOf(New_Request));

			New_Request.click();
			Time_Materials.click();
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Continue.click();
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
		}
		
		//Switching to other country
		public void Change_Requestor()
		{
			WebDriverWait wait06 = new WebDriverWait(driver, 180);
			wait06.until(ExpectedConditions.visibilityOf(Change_req));
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Change_req.click();
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			email_txtbox.sendKeys("acarruth@us.ibm.com");
			go_btn.click();
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			name_select.click();
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Use_as_req.click();
		}
		public void Select_Requestor(){

			WebDriverWait wait07 = new WebDriverWait(driver, 180);
			wait07.until(ExpectedConditions.visibilityOf(Requesting_Company));

			String RC = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 0);
			String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 1);  

			Select Rcom = new Select(Requesting_Company);
			Rcom.selectByVisibleText(RC);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			
			Select Rog = new Select(Requesting_Organization);
			Rog.selectByVisibleText(RO);

			/* code to capture screenshot */
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Continue.click();		
		}
		public void Select_JRSS()

		{

			WebDriverWait wait08 = new WebDriverWait(driver, 160);
			wait08.until(ExpectedConditions.visibilityOf(Priced_JRSS)); 

			Priced_JRSS.click();	

			WebDriverWait wait09 = new WebDriverWait(driver, 160);
			wait09.until(ExpectedConditions.visibilityOf(Select_JobRole));	

			String JR = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 2);
			String SS = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 3); 

			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			Select JRlistbox = new Select(Select_JobRole);
			JRlistbox.selectByVisibleText(JR);

			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			Select SSlistbox = new Select(Select_SkillSet);
			SSlistbox.selectByVisibleText(SS);
				
			/* code to capture screenshot */
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Continue.click();

		}
		
		public void Request_detailpage()

		{
			WebDriverWait wait10 = new WebDriverWait(driver, 160);
			wait10.until(ExpectedConditions.visibilityOf(Project_Name));

			Project_Name.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 4));
			Customer_Name.sendKeys("Sanity test_US");
			GlobalResource_No.click();
			Govt_FederalContract.click();
			
			Project_Task_Manager_AddButton.click();
			String MainWindow1=driver.getWindowHandle();

			Set<String> s1=driver.getWindowHandles();		
			Iterator<String> i1=s1.iterator();		

			while(i1.hasNext())			
			{	
				String ChildWindow1=i1.next();		

				if(!MainWindow1.equalsIgnoreCase(ChildWindow1))			
				{    		
					System.out.println("Window handler Id of Parent window= "+MainWindow1);
					System.out.println("Window handler Id of Child window= "+ChildWindow1);

					// Switching to Child window
					driver.switchTo().window(ChildWindow1);	

					WebDriverWait wait11 = new WebDriverWait(driver, 160);
					wait11.until(ExpectedConditions.visibilityOf(Email_Id_TextBox)); 
			//window();
			Email_Id_TextBox.sendKeys("srisnul1@in.ibm.com");                                                                     
			go_btn.click();
			WebDriverWait wait12 = new WebDriverWait(driver, 180);
			wait12.until(ExpectedConditions.visibilityOf(Name));
			Name.click();
			WebDriverWait wait13 = new WebDriverWait(driver, 180);
			wait13.until(ExpectedConditions.visibilityOf(UseAsManager_Button));
			/* code to capture screenshot */
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			UseAsManager_Button.click();
				}
			}
			//Switching to Parent window i.e Main Window.
			driver.switchTo().window(MainWindow1);
			//driver.switchTo().defaultContent();
			
			WebDriverWait wait14 = new WebDriverWait(driver, 160);
			wait14.until(ExpectedConditions.visibilityOf(Continue)); 

			String clientValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 5);
			String brandValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 6); 
			String sectorValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 7);
			String industryValue = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 8); 
			
			Select clientdropdown = new Select(Client);
			clientdropdown.selectByVisibleText(clientValue);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			
			Select branddropdown = new Select(Brand);
			branddropdown.selectByVisibleText(brandValue);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			
			Select sectordropdown = new Select(Sector);
			sectordropdown.selectByVisibleText(sectorValue);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			
			Select industrydropdown = new Select(Industry);
			industrydropdown.selectByVisibleText(industryValue);
			
			FFIEC.click();
			Accounting_Type.click();
			/* code to capture screenshot */
			
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Continue.click();
		}

		//Skill and location selection page
		public void Skill_detailLocationpage()

		{

			WebDriverWait wait15 = new WebDriverWait(driver, 160);
			wait15.until(ExpectedConditions.visibilityOf(State_Region_Province)); 
			
			Select st = new Select(State_Region_Province);
			st.selectByVisibleText("Vermont");
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			
			Select ct = new Select(City);
			ct.selectByVisibleText("ESSEX JUNCTION");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Flowdown_NoRadio.click();
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Continue_2.click();
		}
		public boolean isAlertPresent() 
		{ 
			try 
			{ 
				driver.switchTo().alert().accept();
				return true;


			}   // try 
			catch (NoAlertPresentException Ex) 
			{ 
				return false; 
			}   // catch 

		}
		
		
		//Rates selection page
		public void Skill_detail_skillpricepage()

		{

			WebDriverWait wait16 = new WebDriverWait(driver, 160);
			wait16.until(ExpectedConditions.visibilityOf(Skill_Level));

			String SL_Value = Excel.getCellValue(xlsFilePath, "Request_creation", 1, 12); 

			Select SLDropdown = new Select(Skill_Level);
			SLDropdown.selectByVisibleText(SL_Value);

			//Quantity.clear();
			//driver.switchTo().alert().accept();
			//Quantity.sendKeys("3");
			//ST_time.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"12.00");
			//ST_time.clear();
			//ST_time.sendKeys("12.00");
			//Above_Matrix_Rate.click();
			//Below_Matrix_Rate.click();
			Select skill1 = new Select(Core_skill_1);
			skill1.selectByVisibleText("1 Knowledge/some job experience");
			Select skill2 = new Select(Core_skill_2);
			skill2.selectByVisibleText("3 Expert, can lead others");
			Select skill3 = new Select(Core_skill_3);
			skill3.selectByVisibleText("0 No Experience");
			
			//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\US");
			Continue_2.click();

		}
		
		//Supplier selection page
		public void SupplierSelectionPage() throws InterruptedException
		{
			WebDriverWait wait17 = new WebDriverWait(driver, 160);
			wait17.until(ExpectedConditions.visibilityOf(Continue));

			WebDriverWait wait4 = new WebDriverWait(driver, 160);
			wait4.until(ExpectedConditions.visibilityOf(secondary_supp));

			secondary_supp.click();
			Deselect_All.click();
			RadioButton_UStestVendor.click();

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\NPrice\\US");

			Continue.click();

			//Skill detail Skill request
			WebDriverWait wait5 = new WebDriverWait(driver, 160);
			wait5.until(ExpectedConditions.visibilityOf(Contract_selection));

			Continue.click();

			//supplier warning page
			WebDriverWait wait6 = new WebDriverWait(driver, 160);
			wait6.until(ExpectedConditions.visibilityOf(SuppSelectionJustification));

			SuppSelectionJustification.sendKeys("test");

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\NPrice\\US");

			Continue.click();

			//Summary Page
			WebDriverWait wait7 = new WebDriverWait(driver, 160);
			wait7.until(ExpectedConditions.visibilityOf(ContinueToRequestSummary));

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\NPrice\\US");


			ContinueToRequestSummary.click();

			WebDriverWait wait8 = new WebDriverWait(driver, 160);
			wait8.until(ExpectedConditions.visibilityOf(SubmitRequest));

			/* code to capture screenshot */
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\NPrice\\US");

			SubmitRequest.click();
			Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\NPrice\\US");
//			WebDriverWait wait11 = new WebDriverWait(driver, 160);
//			wait11.until(ExpectedConditions.visibilityOf(RequestCreated));

//			String Number = RequestCreated.getText().substring(33, 39);
//			Excel.setCellValue(xlsFilePath, "Request_creation", 1, 15, Number );
			WebDriverWait wait11 = new WebDriverWait(driver, 160);
			wait11.until(ExpectedConditions.visibilityOf(RequestCreated_Header));
			String header=RequestCreated_Header.getText();
			System.out.println("header ="+header);
			System.out.println("header length ="+header.length());

			String RequestNumber = header.substring((header.length()-7),(header.length()-1) );
			System.out.println("REQUEST Number ="+RequestNumber);
			Excel.setCellValue(xlsFilePath, "Request_creation", 1, 15, RequestNumber );
			
		}
	public static boolean isloadComplete(WebDriver driver)
	{
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
				|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}
}