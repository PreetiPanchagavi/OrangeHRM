package orange.crm.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Practice {
	
	static WebDriver driver;
	
	public static void main(String [] args) throws InterruptedException{
	System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    
    driver.get("https://loans-staging.flexiloans.com/");
    
    driver.findElement(By.id("mobile-no")).sendKeys("1012423601");
    //JavascriptExecutor executor = (JavascriptExecutor)driver;

    
     Thread.sleep(3000);
   WebElement check= driver.findElement(By.xpath("//span[@onclick='onSubmit()']"));
   //executor.executeScript("arguments[0].click();", check);
   check.click();
    
    Thread.sleep(8000);
    
   // WebElement element = driver.findElement(By.xpath("//span[contains(text(),' Provide us few details to get your loan eligibility ')]"));
    
  /*  WebDriverWait wait = new WebDriverWait(driver,30);
    WebElement ele1=wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Provide us few details to get your loan eligibility')]"))));
    
    System.out.println("Get text:" +ele1.getText())*/;
    
    driver.switchTo().frame("flexiIframe");
    
   /* driver.findElement(By.xpath("//button[@class='mat-focus-indicator button-style button-rounded full-width mat-flat-button mat-button-base mat-primary']")).click();

    Set<String> window=driver.getWindowHandles();
    
    System.out.println("Windows size::" +window.size());*/
/*   String parent = driver.getWindowHandle();
   driver.switchTo().window(parent);*/
   Thread.sleep(8000);
   WebElement ele = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
   ele.sendKeys("1234");
    
    
	}
}
	


