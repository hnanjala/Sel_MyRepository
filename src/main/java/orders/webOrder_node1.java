package orders;

import utilities.ExtentReportManager;
import excelExportAndFileIO.ExcelUtilities;
import static org.testng.Assert.fail;
//import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
//import org.junit.*;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.URL;

//import java.lang.Thread;
//import org.testng.Assert.*;
import static org.testng.AssertJUnit.*;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.markuputils.*;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//import org.apache.log4j.Logger;
//import org.apache.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class webOrder_node1 {
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	String baseUrl=new String("http://cmh-dev2-www.express.com/");
	public StringBuffer verificationErrors = new StringBuffer();
	WebDriverWait wait;
	String[][] item;
	String[][] orders=new String[30][2];
	int row,col;
	int counter=0;
	int k=1;
	String SNo;
	int i;
	//Logger log=Logger.getLogger("devpinoyLogger");
	Logger log=LogManager.getLogger(WebOrder.class.getName());
	
@BeforeClass
public void Open_NewBrowser_Window_And_Launch_PPS() throws Exception 
{
	System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");
    extent=ExtentReportManager.GetExtent();
	//driver=new FirefoxDriver();

    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
   ChromeOptions options = new ChromeOptions();
       options.addArguments("--incognito");
    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	  capabilities.setBrowserName("chrome");
	  capabilities.setPlatform(Platform.WINDOWS);
      RemoteWebDriver driver=new RemoteWebDriver(new URL("http://localhost:5556/wd/hub"),capabilities);
	wait = new WebDriverWait(driver, 15);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	/*driver.get("chrome://extensions");
	WebElement checkbox = driver.findElement(By.xpath("//div[contains(@class, 'incognito-control')]/label/input[@type='checkbox']"));
	if (!checkbox.isSelected()) {
	    checkbox.click();
	} */
	
	driver.navigate().to(baseUrl);
	
	  driver.manage().window().maximize();	
	  
		//driver=new ChromeDriver();
	  
		orders[0][0]="Serial#";
		orders[0][1]="Customer Order Number";
		
		
	  Thread.sleep(2000);
	  
	  ExcelUtilities TestData=new ExcelUtilities();
	  //System.out.println("User Directory"+System.getProperty("user.dir"));
	  item=TestData.readExcel("\\\\cmhfiler1\\express_public\\OMS Testing\\Hemaraj\\Automation\\TestDataFiles","TestDataFile.xlsx","TestData_Sheet");
	  row=item.length;
	  col=item[0].length;
	  System.out.println("\n------------------------------------------------------------------\n");
	 // System.out.println("rows:"+row);
	  
	  
}


@Test
public void Add_items_and_Checkout()throws Exception 
{
	log.debug("Test message - Hemaraj");
	log.trace("Trace message");
	test=extent.createTest("Create Order - Add items & Checkout", "Add items to the cart & checkout");
	outer:
	for (i=1;i<row;i++)
	{
				if (!isElementPresent(By.id("header-search-field")))
				{
					driver.navigate().to(baseUrl);
				}
	/* Code for search field
	driver.findElement(By.id("header-search-field")).click();
	driver.findElement(By.id("header-search-field")).clear();
	driver.findElement(By.id("header-search-field")).sendKeys(item[i][1]);
	driver.findElement(By.id("header-search-field")).sendKeys(Keys.ENTER);
	*/
				
				driver.navigate().to("http://cmh-dev2-www.express.com/clothing/women/express-one-eleven-long-sleeve-slash-neck-tee/pro/"+item[i][5]+"/cat1830015");
				Thread.sleep(2000);

				if (isElementPresent(By.id("acsMainInvite")))
				 {
					//driver.findElement(By.xpath("//*[@class='acsModalBackdrop acsAbandonButton']")).click();
					if(isElementPresent(By.cssSelector(".acsInviteButton.acsDeclineButton")))
					{
						driver.findElement(By.cssSelector(".acsInviteButton.acsDeclineButton")).click();
						System.out.println(".acsInviteButton.acsDeclineButton");
					}
				 }
				Thread.sleep(2000);
	//if (isElementPresent(By.xpath("//ul[@data-product-id='"+item[i][5]+"']//a")))
				if (isElementPresent(By.xpath("//*[@id='content']//button[contains(.,'Add to Bag')]")))
		{
	
				//driver.findElement(By.xpath("//ul[@data-product-id='"+item[i][5]+"']//a")).click();
				Thread.sleep(2000);
				if (isElementPresent(By.id("acsMainInvite")))
				 {
					//driver.findElement(By.xpath("//*[@class='acsModalBackdrop acsAbandonButton']")).click();
					if(isElementPresent(By.cssSelector(".acsInviteButton.acsDeclineButton")))
					{
						driver.findElement(By.cssSelector(".acsInviteButton.acsDeclineButton")).click();
						System.out.println(".acsInviteButton.acsDeclineButton");
					}
				 }
				
				
				if (isElementPresent(By.id("email-promotion")) && counter==0)
				{
					if (driver.findElement(By.xpath("//div[@class='footernewslettersignupcomponent']//button[@class='close-text drawer-close']")).isEnabled() && driver.findElement(By.xpath("//div[@class='footernewslettersignupcomponent']//button[@class='close-text drawer-close']")).isDisplayed())
					{
					//TestScroll(By.xpath("//div[@class='footernewslettersignupcomponent']//button[@class='close-text drawer-close']"));
					driver.findElement(By.xpath("//div[@class='footernewslettersignupcomponent']//button[@class='close-text drawer-close']")).click();
					counter++;
					}
				}
				
			
				if (isElementPresent(By.id("acsMainInvite")))
				 {
					//driver.findElement(By.xpath("//*[@class='acsModalBackdrop acsAbandonButton']")).click();
					if(isElementPresent(By.cssSelector(".acsInviteButton.acsDeclineButton")))
					{
						driver.findElement(By.cssSelector(".acsInviteButton.acsDeclineButton")).click();
						System.out.println(".acsInviteButton.acsDeclineButton");
					}
				 }
				
				Thread.sleep(1000);	
				
				//*[@id='express-view-colors']//label[@title='"+item[i][3]+"']/img
				driver.findElement(By.xpath("//*[@id='content']//a[contains(@class,'colorSwatch')][contains(@aria-label,'"+item[i][3]+"')]")).click();
				Thread.sleep(1000);
				
				if(item[i][4]!="No Size" && isElementPresent(By.xpath("//*[@id='content']//button[contains(.,'Select Size')]")))
				{
					//*[@id='content']//button[contains(.,'Select Size')]
					//*[@id='availableSizes']//button[contains(@aria-label,'Select X Small')]
					//*[@id='content']//button[contains(.,'Add to Bag')]
					driver.findElement(By.xpath("//*[@id='content']//button[contains(.,'Select Size')]")).click();
					if(item[i][4].contains("Short") || item[i][4].contains("Long") || item[i][4].contains("Regular"))
					{
						String size_extn=new String(item[i][4].trim().substring(3));
						item[i][4]=item[i][4].trim().substring(0,3);
						//System.out.println("Size after trim: "+item[i][4]);
						driver.findElement(By.xpath("//*[@class='ReactTabs__TabList']//h3[contains(.,'"+size_extn+"')]")).click();
					}
					driver.findElement(By.xpath("//*[@id='availableSizes']//button[contains(@aria-label,'Select "+item[i][4]+"')]")).click();
					//selectDropdownValue(By.xpath("express-view-sizes-dropdown"),item[i][4]);
				}
				Thread.sleep(2000);
				//TestScroll(By.id("add-to-bag-button"));
				//driver.findElement(By.id("add-to-bag-button")).click();
				
				driver.findElement(By.xpath("//*[@id='content']//button[contains(.,'Add to Bag')]")).click();
				System.out.println("item '"+item[i][1]+"' has been added to the Cart");
				updateExcel("Added to Cart",i,7);
				Thread.sleep(4000);
				String session=driver.getWindowHandle();
				//System.out.println("Session id:"+session);
		}
				
	else
		{
			
		    test.info("item not found - "+item[i][1]);
		    System.out.println("item not found: "+item[i][1]);
			updateExcel("unable to find this item",i,7);
		}
	
	if (!((i+1)==row))
	{
	if (item[i][0]==item[i+1][0])
	{
		test.info("Adding remaining items to the cart");
		System.out.println("Adding remaining items to the cart");
		continue outer;
	}

	}
/*
driver.findElement(By.xpath("//a[contains(.,'express lip gloss')]")).click();
Thread.sleep(3000);
driver.findElement(By.xpath(".//div[@title='GORGEOUS ORCHID']")).click();
Thread.sleep(2000);
driver.findElement(By.id("AddToBag")).click();
*/
Thread.sleep(6000);

if(!isElementPresent(By.xpath("//*[@class='bag-icon bag-empty'][contains(text(),'0')]")))
{

		driver.findElement(By.className("my-bag-link")).click();
		
		TestScroll(By.id("order-summary-checkout-button"));
		driver.findElement(By.id("order-summary-checkout-button")).click();
		
		if(isElementPresent(By.id("login")))
		{
			driver.findElement(By.xpath("//*[@id='login']//a[@class='cta final continue-guest']")).click();
		}
		
		driver.findElement(By.id("first-name")).sendKeys("Hemaraj");
		driver.findElement(By.id("last-name")).sendKeys("Nanjala");
		driver.findElement(By.id("address-line1")).sendKeys("1 Express Dr");
		driver.findElement(By.id("city")).sendKeys("Columbus");
		Select state=new Select(driver.findElement(By.id("state")));
		state.selectByValue("OH");
		driver.findElement(By.id("zip")).sendKeys("43230");
		driver.findElement(By.id("phone-number")).sendKeys("4123456789");
		driver.findElement(By.id("email-address")).sendKeys("hemaraj208@gmail.com");
		driver.findElement(By.id("confirm-email-address")).sendKeys("hemaraj208@gmail.com");
		Thread.sleep(2000);	
		System.out.println("Entered Customer Details");
		test.info("Customer details have been successfully entered");
		driver.findElement(By.id("goto-payment")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("credit-card-number")).sendKeys("4012000033330026");
		Select month=new Select(driver.findElement(By.id("credit-card-expiration-month")));
		Select year=new Select(driver.findElement(By.id("credit-card-expiration-year")));
		month.selectByValue("03");
		year.selectByValue("2025");
		driver.findElement(By.id("credit-card-security-code")).sendKeys("208");
		TestScroll(By.id("checkoutPlaceOrderBtn"));
		System.out.println("Entered Payment Details");
		test.info("Payment details have been entered");
		driver.findElement(By.id("checkoutPlaceOrderBtn")).click();
		Thread.sleep(5000);
		//System.out.println(driver.findElement(By.className("order-confirmation")).getText());
		assertTrue("Order Creation was unsuccessful",driver.findElement(By.className("order-confirmation")).getText().contains("Order Confirmation #"));
		int EXP_index=driver.findElement(By.className("order-confirmation")).getText().indexOf("EXP");
		String order=driver.findElement(By.className("order-confirmation")).getText().substring(EXP_index, EXP_index+13);
		System.out.println("Order"+i+"#: "+order);
		test.pass("Order#: "+order+" has been successfully created");
		//System.out.println("i:"+i);
		System.out.println("\n------------------------------------------------------------------\n");
		updateExcel(order,i,8);
		//SNo=String.valueOf(k);
		//orders[k][0]=SNo;
		//System.out.println("orders["+k+"][0]:"+orders[k]);
		orders[k][0]=String.valueOf(k);
		orders[k][1]=String.valueOf(order);
		//System.out.println("orders["+k+"]:"+orders[k]);
		k=k+1;
		//Thread.sleep(3000);
		}

if (item[i][0]==null || item[i][0]=="")
{
	System.out.println("No value mentioned for ORDER under row#:"+(i+1));
	break;
}

/*
//EOM code
driver.navigate().to("https://qaeomweb.expdev.local:17001/");

driver.findElement(By.id("textfield-1019-inputEl")).click();
driver.findElement(By.id("textfield-1019-inputEl")).sendKeys("hnanjala");
driver.findElement(By.id("textfield-1020-inputEl")).click();
driver.findElement(By.id("textfield-1020-inputEl")).sendKeys("pa6reSte");
Thread.sleep(2000);
//driver.findElement(By.id("button-1021-btnInnerEl")).click();
driver.findElement(By.id("textfield-1020-inputEl")).sendKeys(Keys.ENTER);
Thread.sleep(10000);
System.out.println("Login success");
Thread.sleep(7000);

//Set<String>handle=driver.getWindowHandles();
// System.out.println(handle);
System.out.println("Window size: "+driver.manage().window().getSize());
System.out.println("Title: "+driver.getTitle());

driver.switchTo().activeElement().click();

System.out.println("Active Element text???:"+    driver.switchTo().activeElement().getText());
System.out.println("Element Tag Name"+    driver.switchTo().activeElement().getTagName());

System.out.println("class"+    driver.switchTo().activeElement().getClass());
Robot robot=new Robot();

driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);	
while(!(isElementPresent(By.xpath("//td[contains(.,'955')]"))))
{
    robot.mouseWheel(100);
	driver.switchTo().activeElement().sendKeys(Keys.PAGE_DOWN);
}
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
TestScroll(By.xpath("//td[contains(.,'955')]"));
driver.findElement(By.xpath("//td[contains(.,'955')]")).click();
//div[@class='x-grid-cell-inner'][contains(.,'100')]
Thread.sleep(4000);
driver.findElement(By.id("button-1118-btnEl")).click();
Thread.sleep(10000);
*/
}
}


@AfterMethod
public void Capture_Screenshot(ITestResult result) throws Exception 
{
	TakesScreenshot ts=(TakesScreenshot)driver;
	 
	// Call method to capture screenshot
	File source=ts.getScreenshotAs(OutputType.FILE);

	 
	// Copy files to specific location here it will save all screenshot in our project home directory and
	// result.getName() will return name of test case so that screenshot name will be same
	if(result.getStatus()==1) 
	{
		FileUtils.copyFile(source, new File("./Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png"));
		test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_PASS.png");
	}
	else
	{
		FileUtils.copyFile(source, new File("./Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png"));
		test.addScreenCaptureFromPath("../Screenshots/"+result.getInstanceName()+"_"+result.getName()+"_FAIL.png");
	}
	System.out.println("Screenshot has been captured for the test"+result.getName());
	//test.addScreenCaptureFromPath("../Screenshots/"+result.getName()+".png");
}

@AfterClass
public void Close_Browser() throws Exception
  {
	Markup output=MarkupHelper.createLabel("FILE://cmhfiler1/express_public/OMS%20Testing/Hemaraj/Automation/TestDataFiles/TestDataFile.xlsx", ExtentColor.ORANGE);
	test.info(output);
	test.log(Status.PASS, "Output File Path: <href>FILE://cmhfiler1/express_public/OMS%20Testing/Hemaraj/Automation/TestDataFiles/TestDataFile.xlsx</href>");
	Markup table=MarkupHelper.createTable(orders);
	test.warning(table);
	//Markup orderTable=MarkupHelper.createTable(orders);
	//test.info(orderTable);
	extent.flush();
	 driver.quit();
	  String verificationErrorString = verificationErrors.toString();
	  if (!"".equals(verificationErrorString)) 
	  {
	    fail(verificationErrorString);
	  }
	}


private boolean isElementPresent(By by) {
 try {
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   driver.findElement(by);
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   return true;
 } catch (Exception e) {
   return false;
 }
}

public void TestScroll (By by) throws Exception
{
WebElement element = driver.findElement(by);
Point location = element.getLocation();
//int x=location.x;
int y=(location.y)-200;
((JavascriptExecutor) driver).executeScript("window.scrollBy(-(document.body.scrollWidth),-(document.body.scrollHeight))");
((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+y+")"+"");
Thread.sleep(3000);
}

public void updateExcel(String dataToAdd,int row,int column) throws IOException{
	ExcelUtilities objExcelFile=new ExcelUtilities();
	objExcelFile.updateExcel("\\\\cmhfiler1\\express_public\\OMS Testing\\Hemaraj\\Automation\\TestDataFiles","TestDataFile.xlsx","TestData_Sheet", dataToAdd, row, column);	
}

public void selectDropdownValue(By by,String value)
{
	Select dropdown=new Select(driver.findElement(by));
	dropdown.selectByValue(value);
}

private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {

      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();

        alert.accept();
    
      return alertText;
  
  }


}
