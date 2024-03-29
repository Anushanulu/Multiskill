package MulSkill_IN;

import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import lib.Excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.jboss.aerogear.security.otp.Totp;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Step05_PO_Auth_IN {
 
  
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

@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Awaiting project office action')]") private WebElement Awaiting_PO_Auth_link ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Service ids')]") private WebElement Candidates ;
@FindBy (xpath = ".//*[@id='left-nav']//a[contains(text(),'Skill line items')]") private WebElement Skill_line_items ;
@FindBy (id="FLD_REQUEST_NUMBER") private WebElement Req_Num_Search ;
@FindBy (name="btnGo") private WebElement GO_reqnum ;
@FindBy (xpath =" .//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[3]/a " ) private WebElement Request_Number ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[4]/a" ) private WebElement Project_Name ;
@FindBy (xpath = ".//*[@id='content-main']/form/table[4]/tbody/tr[2]/td[5]/a" ) private WebElement Skill_link ;
//@FindBy(name="FLD_MULTI_LINE_ITEMS") private WebElement  Select_Checkbox ;
@FindBy(name="FLD_MULTI_LINE_ITEMS") private List<WebElement>  Select_Checkbox ;
@FindBy (name="btnSubmitSelCand") private WebElement Submit_Selected_Responses ;
@FindBy (id= "FLD_REQST_ORG" ) private WebElement Requesting_Organization ;
@FindBy (name="btnConfirmSubmit") private WebElement Confirm_submission ;
@FindBy ( id="FLD_NON_METRO_HIRING_REQUEST_NUM" ) private WebElement Non_Metro ;
@FindBy (id="FLD_REASON_NOT_HAVING_METRO_NUM") private WebElement Reason ;
@FindBy (id="ContinueSubmit") private WebElement Continue_Submission;
@FindBy(xpath = ".//a[text()='Return to request header']") private WebElement  returnToRequest ;

@FindBy(css=".beeheard-overlay-close") private WebElement popup_close;



// Initialize the web elements 
public Step05_PO_Auth_IN(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}


// Function to login to the application
// Click on the link again as workaround 
public void login() throws InterruptedException{

	WebDriverWait wait00 = new WebDriverWait(driver, 180);
	wait00.until(ExpectedConditions.visibilityOf(cred_login));
	cred_login.click();
	
	//new login changes
	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(login_Button));
	wait01.until(ExpectedConditions.elementToBeClickable(login_Button));
	login_username.clear();
	login_username.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 14, 0));
	Thread.sleep(1000);
	login_password.clear();
	login_password.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 14, 1));
	
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

		String otpKeyStr = "AZDG4RLPB4B5CRY7";// bennett26@c25a0161.toronto.ca.ibm.com 
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

//Function to click Create New Request button
public void authorize_PO ()
{

	WebDriverWait wait01 = new WebDriverWait(driver, 180);
	wait01.until(ExpectedConditions.visibilityOf(Awaiting_PO_Auth_link));

	Awaiting_PO_Auth_link.click();
	
	WebDriverWait wait02 = new WebDriverWait(driver, 180);
	wait02.until(ExpectedConditions.visibilityOf(Skill_line_items));

	//Skill_line_items.click();
	Candidates.click();

	WebDriverWait wait03 = new WebDriverWait(driver, 180);
	wait03.until(ExpectedConditions.visibilityOf(Req_Num_Search));
	
	Req_Num_Search.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 3, 15));
	GO_reqnum.click();

	WebDriverWait wait04 = new WebDriverWait(driver, 180);
	wait04.until(ExpectedConditions.visibilityOf(Project_Name));

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");

	Project_Name.click();
	
	

	WebDriverWait wait05 = new WebDriverWait(driver, 180);
	wait05.until(ExpectedConditions.visibilityOfAllElements(Select_Checkbox));

	//Select_Checkbox.click();
	List<String> li=new ArrayList<>();
	for(WebElement ele:Select_Checkbox)
	ele.click();
	
	/*isFramePresent();
	
	WebDriverWait wait06 = new WebDriverWait(driver, 180);
	wait06.until(ExpectedConditions.visibilityOf(Submit_Selected_Responses));*/
	
	Submit_Selected_Responses.click();
	
	

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");

	
	
	
	
//	WebDriverWait wait6 = new WebDriverWait(driver, 160);
//	wait6.until(ExpectedConditions.visibilityOf(Continue_Submission));
//
//	Non_Metro.click();

//	Select reason = new Select(Reason);
//	reason.selectByVisibleText("I use Metro, but can't for this situation and have a management-approved bypass");

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");

	WebDriverWait wait07 = new WebDriverWait(driver, 180);
	wait07.until(ExpectedConditions.visibilityOf(Confirm_submission));

	String RO = Excel.getCellValue(xlsFilePath, "Request_creation", 3, 1);  
	Select Rog = new Select(Requesting_Organization);
	Rog.selectByVisibleText(RO);

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");
	Confirm_submission.click();

	isAlertPresent();

	WebDriverWait wait08 = new WebDriverWait(driver, 160);
	wait08.until(ExpectedConditions.visibilityOf( returnToRequest));

	/* code to capture screenshot */
	//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\SriSwathiAnushaNulu\\Documents\\Project csa\\Practice Automation\\Review\\NCore\\IND");

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
}
