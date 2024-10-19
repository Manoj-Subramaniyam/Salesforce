package week2.day2;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class S6_48_Attach_document_to_the_newly_created_campaign {
	@Test
	public void deleteLead() throws InterruptedException, AWTException {
		EdgeOptions  options = new EdgeOptions();
		options.addArguments("--disable-notifications");
		EdgeDriver driver=new EdgeDriver(options);             
		driver.get("https://login.salesforce.com/"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys("gokul.sekar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$321");
		driver.findElement(By.id("Login")).click();

		//Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//button[@title='App Launcher']")).click();
		//Click view All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//P[text()='Sales']")).click();

		//Click on Campaign tab 
		WebElement element=driver.findElement(By.xpath("//a[contains(@title,\"Campaigns\")]"));
		driver.executeScript("arguments[0].click()", element);
		//Click Bootcamp link 
		driver.findElement(By.xpath("//input[@name='Campaign-search-input']")).sendKeys("Bootcamp",Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tbody/tr[1]/th//a")).click();
		
		//Click on Upload button 
		element= driver.findElement(By.xpath("(//a[@title='Upload Files'])[1]"));
		element.click();
		//Select a file from local and upload a pdf file 
		//element.sendKeys("D:\\SDET Ref Documents");
		//element.click();
		Thread.sleep(2000);
		StringSelection path = new StringSelection("D:\\SDET Ref Documents\\Test.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
		
		Robot robo = new Robot();
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyRelease(KeyEvent.VK_V);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
		element=driver.findElement(By.xpath("//span[text()='Done']"));
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		Thread.sleep(2000);
		//Verify the file name displayed as link
		driver.findElement(By.xpath("//article[@aria-label='Attachments']//span[@class='view-all-label']")).click();
		Thread.sleep(2000);
		String text = driver.findElement(By.xpath("//tbody/tr[1]/th//div[@class='forceContentFileTitle']//span")).getText();
		if(text.equals("Test")) System.out.println("File uploaded sucessfully");
		else System.out.println("File not uploaded");
		driver.quit();
	}
}
