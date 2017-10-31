package orders;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


import org.apache.commons.io.FileUtils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public class CsrOrder {

@Test
public void CSR_Order() throws InterruptedException, AWTException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver,15);
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		driver.navigate().to("https://qaeomweb.expdev.local:17001");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.manage().window().maximize();
				driver.findElement(By.id("textfield-1019-inputEl")).clear();
				driver.findElement(By.id("textfield-1019-inputEl")).sendKeys("hnanjala");
				TakeScreenShot("Enter_User_Name",ts);
				driver.findElement(By.id("textfield-1020-inputEl")).clear();
				driver.findElement(By.id("textfield-1020-inputEl")).sendKeys("kekA8ewa");
				TakeScreenShot("Enter_Password",ts);
				Thread.sleep(3000);
				driver.findElement(By.id("button-1022-btnEl")).click();
				Thread.sleep(10000);
									
			    driver.switchTo().activeElement().click();
				driver.findElement(By.xpath("//td[contains(.,'173')]")).click();
				TakeScreenShot("SelectStore",ts);
				//Thread.sleep(5000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(.,'OK')]")));
                driver.findElement(By.xpath("//span[contains(.,'OK')]")).click();
				TakeScreenShot("EOM_HomePage",ts);
				//driver.findElement(By.xpath("//span[contains(.,'OK')]")).click();
				//button-1184-btnEl
					//button-1184-btnInnerEl
		//driver.close();
		//driver.quit();
				Actions act=new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]"))).perform();
				//act.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]//*[contains(@id,'triggerfield')] [contains(@placeholder,'item description')]"))).click().perform();
				driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]//*[contains(@id,'triggerfield')] [contains(@placeholder,'item description')]")).clear();
				driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]//*[contains(@id,'triggerfield')] [contains(@placeholder,'item description')]")).sendKeys("10181058");
				driver.findElement(By.xpath("//div[contains(@id,'ds-itemtile-')]//*[contains(@id,'triggerfield')] [contains(@placeholder,'item description')]")).click();
				Robot rb=new Robot();
	
				rb.keyPress(KeyEvent.VK_ENTER);
				rb.keyRelease(KeyEvent.VK_ENTER);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(.,'Pick up at store')][contains(@id,'radiofield')]")));
					Thread.sleep(3000);			
                driver.findElement(By.xpath("//label[contains(.,'Pick up at store')][contains(@id,'radiofield')]")).click();
				
	}
	
private void TakeScreenShot(String scrshotfilename,TakesScreenshot ts)
{
File src=ts.getScreenshotAs(OutputType.FILE);
try {
	FileUtils.copyFile(src,  new File("./Screenshots/"+scrshotfilename+".png"));
	//System.out.println("Thread.currentThread().getName(): "+Thread.currentThread().getName());
	//System.out.println("Thread.currentThread().getStackTrace()[1].getMethodName(): "+Thread.currentThread().getStackTrace()[1].getMethodName());
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
}

}
