package MulSkill_IN;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step01_CreateRequest_IN {
 
private WebDriver driver;
public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
public String sheet="Login";

// Define the element 
/*@FindBy (xpath = ".//*[@id='content-main']/p[2]/a") private WebElement loginToContractor_Link;
@FindBy ( id="btn_signin") private WebElement Signin_Button ;
@FindBy ( xpath=".//*[@id='desktop']") private WebElement Username_Box ;
@FindBy ( xpath=".//*[@id='body']/div[1]/div[2]/div/div/form/input[4]") private WebElement Password_Box ;*/

//New login code
@FindBy ( xpath= "//span[@id='credentialSignin']") private WebElement cred_login;
//@FindBy ( xpath= "//input[@id='otp-input']") private WebElement otp_box;
//@FindBy(id = "submit_btn")    private WebElement otp_submit_btn;
@FindBy ( xpath= "//input[@name='username']") private WebElement login_username;
@FindBy ( xpath= "//input[@name='password']") private WebElement login_password;
@FindBy ( id="login-button") private WebElement login_Button ;
@FindBy(id = "otp-input")	private WebElement passcodeBox;
@FindBy(id = "submit_btn")	private WebElement SubmitPasscode;
@FindBy (xpath = "//label[@id='totp_label']") private WebElement Authenticator_App_option;
@FindBy (xpath = "//input[@id='newTotp-otp-input']") private WebElement EnterAccessCode_Box;
@FindBy (xpath = "//button[contains(text(),'Next: Verify')]") private WebElement Verify_Button;
@FindBy (xpath = ".//div[@id='totp_item']") private WebElement Totp_Link;
@FindBy (xpath = "//input[@id='otp-input']") private WebElement OTP_TextBox;
@FindBy (xpath = "//button[@id='submit_btn']") private WebElement OTP_Submit_Button;

@FindBy (xpath = ".//*[@id='content-main']/form[1]/div/span/input") private WebElement Create_New_Request;

//Skill Request Page	
@FindBy (id ="FLD_REQ_TYPE") private WebElement New_Request ;
@FindBy ( id="FLD_NORMAL_TYPE1") private WebElement Time_Materials ;
@FindBy (name = "btnContinue") private WebElement Continue ;

//Select Requestor
@FindBy  (id = "FLD_REQST_CO") private WebElement  Requesting_Company ;
@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;

// Job Role/Skill
@FindBy ( xpath= ".//*[@id='JRSS_SELECTION1']") private WebElement Priced_JRSS;
//@FindBy ( xpath= ".//*[@id='JRSS_SELECTION2']") private WebElement Priced_JRSS;
@FindBy ( id = "FLD_JOB_ROLE" ) private WebElement Select_JobRole ;
@FindBy ( id = "FLD_SKILL_SET") private WebElement Select_SkillSet;

//Project Creation
@FindBy ( id="FLD_PROJ_NAME") private WebElement Project_Name;
@FindBy ( id="FLD_CONTACT_NAME") private WebElement Customer_Name;
@FindBy (id="FLD_IS_GLOBAL_RESOURCE0") private WebElement GlobalResource_No ;
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
@FindBy ( xpath = ".//*[@id='FLD_IS_TIME_APPROVAL_TASK1']")  private WebElement Will_Manager_perform_TimeApproval_Yes ;
@FindBy ( xpath = ".//*[@id='FLD_IS_TIME_APPROVAL_TASK0']")  private WebElement Will_Manager_perform_TimeApproval_No ;
@FindBy ( xpath = ".//*[@name='btnAddTimeApprovalMgr']")  private WebElement TimeApproverID_AddButton ;

@FindBy ( xpath = ".//*[@name='FLD_EMP_WEB_ID']")  private WebElement Email_Id_TextBox ;
@FindBy ( xpath = ".//*[@name='BTN_GO']")  private WebElement GoButton ;
@FindBy (xpath = ".//*[@id='content-main']/table[4]/tbody/tr[2]/td[1]/a" ) private WebElement Name ;
@FindBy ( xpath = ".//input[@name='TEMP TIME APPROVER BUTTON']")  private WebElement UseAsTimeApprover_Button ;
@FindBy ( xpath = ".//input[@name='TEMP MANAGER FOR CR BUTTON']")  private WebElement UseAsManager_Button ;


//Skill detail Location
@FindBy ( id="FLD_WRK_LOC_STATE" ) private WebElement State_Region_Province;
@FindBy ( id="FLD_WRK_LOC_CITY" ) private WebElement City ;
@FindBy ( id="FLD_WRK_LOC" ) private WebElement Work_Location ;
@FindBy ( name="Continue" ) private WebElement Continue_2 ;
@FindBy (id="FLD_ALT_WORK_LOC" ) private WebElement Alternate_Workloc ;
@FindBy (id="FLD_FLOW_DOWN_TRMS" ) private WebElement FlowDown_Checkbox ;
@FindBy ( xpath = "//*[@id='FLD_FLOW_DOWN_RESTRICTION1']") private WebElement Flowdown_NoRadio ;
//@FindBy (xpath = ".//*[@id='FLD_ABOVE_MATRIX_RATES']") private WebElement Above_Matrix_Rate;
@FindBy (xpath = ".//*[@id='FLD_ABOVE_MATRIX_RATES0']") private WebElement Below_Matrix_Rate;

//Skill detail skill price
@FindBy ( id="FLD_RQSTD_SKILL_LVL" ) private WebElement Skill_Level;
@FindBy (id="FLD_RQSTD_PRICE_LVL" )  private WebElement Price_Level;
@FindBy (id="FLD_QTY_SKILL_NEEDED" ) private WebElement Quantity ;
@FindBy (xpath =".//*[@id='FLD_ST']") private WebElement ST_time;
@FindBy ( xpath= ".//h3[contains(text(),'Supplier warning' )]") private WebElement SuppWarning_header;
@FindBy ( id= "FLD_SUPPWARN_SUPPSELJUST") private WebElement SuppWarning_msg;
@FindBy ( id="FLD_WARNING_JUST") private WebElement just_dropdwon;
@FindBy ( xpath= "//input[@name='fldSelectSuppForReq' and @value='3']") private WebElement third_supp;
@FindBy ( id= "FLD_SUPP_NM") private WebElement sup_name;
@FindBy ( id= "FLD_CONTACT_NM") private WebElement contact_name;
@FindBy ( id= "FLD_CONTACT_PHONE_NUM") private WebElement contact_num;

//Skill Summary Page
@FindBy (xpath = ".//*[@value='Continue to request summary'] ") private WebElement ContinueToRequestSummary;

//Review Skill request
@FindBy (name = "Submit request") private WebElement SubmitRequest;

//Request Created
@FindBy ( xpath= ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ") private WebElement RequestCreated;
@FindBy ( xpath="//input[@name='btnCancel']") private WebElement btnCancel;
@FindBy ( xpath= ".//*[@id='content-main']/table[1]/tbody/tr/td[1]/h1 ") private WebElement RequestCreated_Header;
@FindBy(xpath = "//input[@type='submit' and @value='Add skill']")
private WebElement add_skill_btn;

//@FindBy ( id="custom-content") private WebElement feedback_frame; 
@FindBy ( xpath= ".//*[@id='custom-content']") private WebElement feedback_frame; 
@FindBy ( xpath= ".//*[@id='custom-email-link-email']") private WebElement popup_mail; 
@FindBy ( xpath= ".//*[@id='custom-email-link-button']") private WebElement popup_button;
@FindBy(css=".beeheard-overlay-close") private WebElement popup_close;


// Initialize the web elements 
public Step01_CreateRequest_IN(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


// Function to login to the application
public void login() throws InterruptedException{

	WebDriverWait wait00 = new WebDriverWait(driver, 180);
	wait00.until(ExpectedConditions.visibilityOf(cred_login));
	cred_login.click();
	
	//new login changes
	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(login_Button));
	wait01.until(ExpectedConditions.elementToBeClickable(login_Button));
	login_username.clear();
	login_username.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 11, 0));
	Thread.sleep(1000);
	login_password.clear();
	login_password.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 11, 1));
	
	//Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE).save(System.getProperty("user.dir") + "\\src\\test\\resources\\Screens\\GB");
	login_Button.click();
	try{
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions.visibilityOf(Authenticator_App_option));
		
		Authenticator_App_option.click();
		System.out.println("Authenticator option is selected for authentication");
	}catch(NoSuchElementException | TimeoutException e)
	{
		System.out.println("Page to select Authenticator app option is not displayed");
		e.printStackTrace();
	}
	
	try {

		String otpKeyStr = "VUESKO3ATJJPQRG3";// csatestin1@c25a0161.toronto.ca.ibm.com 
		Totp totp = new Totp(otpKeyStr);
		String twoFactorCode = totp.now();
		
		

		OTP_TextBox.sendKeys(twoFactorCode);
		System.out.println("value fetched from box= "+OTP_TextBox.getAttribute("value"));
	
		OTP_Submit_Button.click();
		System.out.println("clicked on OTP submit button");
		WebDriverWait wait02 = new WebDriverWait(driver, 100);
		wait02.until(ExpectedConditions.visibilityOf(Create_New_Request));
		System.out.println("Title of page= "+driver.getTitle());
	}
	catch (Exception e) {
		System.out.println("no OTP screen");
		e.printStackTrace();
		WebDriverWait wait02 = new WebDriverWait(driver, 100);
		wait02.until(ExpectedConditions.visibilityOf(Create_New_Request));
		System.out.println("Title of page= "+driver.getTitle());
	}	
}
//Create Request
public void Create_New_Request()
{
	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Create_New_Request));

	Create_New_Request.click();

}
//Skill Request Page
public void Skill_Request(){

	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(New_Request));

	New_Request.click();
	Time_Materials.click();
	Continue.click();

}

//Select Requestor Page
public void Select_Requestor(){

	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(Requesting_Company));

	String RC = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 0);
	String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 1);  

	Select Rcom = new Select(Requesting_Company);
	Rcom.selectByVisibleText(RC);
	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	
	Select Rog = new Select(Requesting_Organization);
	Rog.selectByVisibleText(RO);

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");
	Continue.click();		
}

//JRSS
public void Select_JRSS()

{

	WebDriverWait wait3 = new WebDriverWait(driver, 160);
	wait3.until(ExpectedConditions.visibilityOf(Priced_JRSS)); 

	Priced_JRSS.click();	

	WebDriverWait wait1 = new WebDriverWait(driver, 160);
	wait1.until(ExpectedConditions.visibilityOf(Select_JobRole));	

	String JR = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 2);
	String SS = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 3); 

	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	Select JRlistbox = new Select(Select_JobRole);
	JRlistbox.selectByVisibleText(JR);

	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	Select SSlistbox = new Select(Select_SkillSet);
	SSlistbox.selectByVisibleText(SS);
		
	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");
	Continue.click();

}

//request details
//String MainWindow=driver.getWindowHandle();
public void Request_detailpage()

{
	WebDriverWait wait22 = new WebDriverWait(driver, 160);
	wait22.until(ExpectedConditions.visibilityOf(Project_Name));

	Project_Name.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 3, 4));
	Customer_Name.sendKeys("Test_CustName123&@#");
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

			WebDriverWait wait8 = new WebDriverWait(driver, 160);
			wait8.until(ExpectedConditions.visibilityOf(Email_Id_TextBox)); 
	//window();
	Email_Id_TextBox.sendKeys("srisnul1@in.ibm.com");                                                                     
	GoButton.click();
	WebDriverWait wait15 = new WebDriverWait(driver, 180);
	wait15.until(ExpectedConditions.visibilityOf(Name));
	Name.click();
	WebDriverWait wait16 = new WebDriverWait(driver, 180);
	wait16.until(ExpectedConditions.visibilityOf(UseAsManager_Button));
	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");
	UseAsManager_Button.click();

		}
	}
	//Switching to Parent window i.e Main Window.
	driver.switchTo().window(MainWindow1);
	/*driver.switchTo().frame(0);
	WebDriverWait wait00 = new WebDriverWait(driver, 160);
	wait00.until(ExpectedConditions.visibilityOf(popup_close));
	popup_close.click();
	driver.switchTo().defaultContent();*/
	isFramePresent();
	/*driver.switchTo().frame(0);
	System.out.println("hit the frame");
	popup_mail.sendKeys("srisnul1@in.ibm.com");
	popup_button.click();*/
	
	WebDriverWait wait10 = new WebDriverWait(driver, 160);
	wait10.until(ExpectedConditions.visibilityOf(Continue)); 

	String clientValue = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 5);
	String brandValue = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 6); 
	String sectorValue = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 7);
	String industryValue = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 8); 
	
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
	

	Accounting_Type.click();
	
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");

	Continue.click();
}

//skill detail Page
public void Skill_detailLocationpage()

{

	WebDriverWait wait = new WebDriverWait(driver, 160);
	wait.until(ExpectedConditions.visibilityOf(State_Region_Province)); 
	
	Select st = new Select(State_Region_Province);
	st.selectByVisibleText("Karnataka");
	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	
	Select ct = new Select(City);
	ct.selectByVisibleText("BANGALORE");
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	if(FlowDown_Checkbox.isSelected())
	{
		Flowdown_NoRadio.click();
	}
	else if(!FlowDown_Checkbox.isSelected())
	{
		FlowDown_Checkbox.click();
		WebDriverWait wait13 = new WebDriverWait(driver, 160);
		wait13.until(ExpectedConditions.visibilityOf( Flowdown_NoRadio));
		Flowdown_NoRadio.click();

	}
	
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
	//driver.switchTo().alert().accept();
}
public void isFramePresent() 
{ 
	try 
	{ 
		driver.switchTo().frame(0);
		WebDriverWait wait00 = new WebDriverWait(driver, 160);
		wait00.until(ExpectedConditions.visibilityOf(popup_close));
		popup_close.click();
		driver.switchTo().defaultContent();

	}   // try 
	catch (NoSuchFrameException Ex) 
	{ 
		System.out.println("no such frame");
	}   // catch 
}


//above matrix rate confirmation and Skill detail skill price
public void Skill_detail_skillpricepage()

{

	WebDriverWait wait3 = new WebDriverWait(driver, 160);
	wait3.until(ExpectedConditions.visibilityOf(Skill_Level));

	String SL_Value = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 12); 

	Select SLDropdown = new Select(Skill_Level);
	SLDropdown.selectByVisibleText(SL_Value);

	Quantity.clear();
//	driver.switchTo().alert().accept();
	Quantity.sendKeys("3");
	//ST_time.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"12.00");
	//ST_time.clear();
	//ST_time.sendKeys("12.00");
	//Above_Matrix_Rate.click();
	Below_Matrix_Rate.click();
	Continue_2.click();

}
//Supplier Selection Page
public void SupplierSelectionPage()
{   
//	third_supp.click();
//	sup_name.sendKeys("Supplier1");
//	contact_name.sendKeys("sup");
//	contact_num.sendKeys("9756453126");

	WebDriverWait wait9 = new WebDriverWait(driver, 160);
	wait9.until(ExpectedConditions.visibilityOf(Continue));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");

	Continue.click();
	

	//Summary Page
	WebDriverWait wait6 = new WebDriverWait(driver, 180);
	wait6.until(ExpectedConditions.visibilityOf(ContinueToRequestSummary));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");

	ContinueToRequestSummary.click();

	WebDriverWait wait7 = new WebDriverWait(driver, 160);
	wait7.until(ExpectedConditions.visibilityOf(SubmitRequest));

	/* code to capture screenshot */
	Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");
}

public void Select_JRSS_2() throws InterruptedException {
	WebDriverWait wait17 = new WebDriverWait(driver, 160);
	wait17.until(ExpectedConditions.visibilityOf(SubmitRequest));
	add_skill_btn.click();

	WebDriverWait wait18 = new WebDriverWait(driver, 160);
	wait18.until(ExpectedConditions.visibilityOf(Priced_JRSS));

	Priced_JRSS.click();

	WebDriverWait wait06 = new WebDriverWait(driver, 160);
	wait06.until(ExpectedConditions.visibilityOf(Select_JobRole));

	String JR = Excel.getCellValue(xlsFilePath, "Request_creation", 5, 2);
	String SS = Excel.getCellValue(xlsFilePath, "Request_creation", 5, 3);

	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	Select JRlistbox = new Select(Select_JobRole);
	JRlistbox.selectByVisibleText(JR);

	driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	Select SSlistbox = new Select(Select_SkillSet);
	SSlistbox.selectByVisibleText(SS);

	/* code to capture screenshot */
	// Shutterbug.shootPage(driver,
	// ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project
	// csa\\Practice Automation\\Multiskill\\Core\\UK");

	Continue.click();
	Step01_CreateRequest_IN req3 = new Step01_CreateRequest_IN(driver);
	req3.Skill_detailLocationpage();
	req3.isAlertPresent();
	req3.Skill_detail_skillpricepage();
	req3.SupplierSelectionPage();
}
public void subreq() {
	WebDriverWait wait19 = new WebDriverWait(driver, 160);
	wait19.until(ExpectedConditions.visibilityOf(SubmitRequest));

	/* code to capture screenshot */
	// Shutterbug.shootPage(driver,
	// ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project
	// csa\\Practice Automation\\Multiskill\\Core\\UK");

	SubmitRequest.click();
	// Shutterbug.shootPage(driver,
	// ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project
	// csa\\Practice Automation\\Multiskill\\Core\\UK");

	WebDriverWait wait20 = new WebDriverWait(driver, 160);
	wait20.until(ExpectedConditions.visibilityOf(RequestCreated_Header));
	String header = RequestCreated_Header.getText();
	System.out.println("header =" + header);
	System.out.println("header length =" + header.length());

	String RequestNumber = header.substring((header.length() - 7), (header.length() - 1));
	System.out.println("REQUEST Number =" + RequestNumber);
	Excel.setCellValue(xlsFilePath, "Request_creation", 3, 15, RequestNumber);

	/* code to capture screenshot */
	// Shutterbug.shootPage(driver,
	// ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project
	// csa\\Practice Automation\\Multiskill\\Core\\UK");

}
public static boolean isloadComplete(WebDriver driver)
{
	return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
			|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
}
}




