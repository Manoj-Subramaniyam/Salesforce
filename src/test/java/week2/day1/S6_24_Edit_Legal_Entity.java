package week2.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class S6_24_Edit_Legal_Entity {

	@Test
	public void editlegalEntity() throws InterruptedException {
		EdgeDriver driver=new EdgeDriver();             
		driver.get("https://login.salesforce.com/"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys("gokul.sekar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$321");
		driver.findElement(By.id("Login")).click();
		
		//Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//button[@title='App Launcher']")).click();
		//Click View All and click Legal Entities from App Launcher
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//div[@type='search']/input")).sendKeys("Legal Entities");
		driver.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@data-label='Legal Entities']")));
		
		//Click on the legal Entities tab 
		driver.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[contains(@title,'Legal')]")));
		
		//Search the Legal Entity 'Salesforce Automation by *Your Name*'
		driver.findElement(By.xpath("//input[@name='LegalEntity-search-input']")).sendKeys("Salesforce Automation by Manoj");
		 //Click on the Dropdown icon and Select Edit
		Thread.sleep(3000);
		driver.findElement(By.xpath("//table//tr/td[5]")).click();
		driver.findElement(By.xpath("//ul[@class='scrollable']/li[1]")).click();
		

		//Enter the Company name as 'Tetsleaf'.
		driver.findElement(By.xpath("//input[@name='CompanyName']")).sendKeys("Tetsleaf");
		//Enter Description as 'SalesForce'.
		driver.findElement(By.xpath("//label[text()='Description']/following::textarea[@class='slds-textarea']")).sendKeys("SalesForce");
		
		//Select Status as 'Active'
		//Actions action= new Actions(driver);
		//action.scrollToElement(driver.findElement(By.xpath("//label[text()='Status']/following::div[1]"))).perform();
		driver.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//label[text()='Status']/following::div[1]")));
		driver.findElement(By.xpath("//label[text()='Status']/following::div[1]")).click();
		driver.findElement(By.xpath("//span[@title='Active']")).click();
		//Click on Save and Verify Status as Active
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		System.out.println(driver.findElement(By.xpath("//div[@role='alert']/following::span[@class='toastMessage slds-text-heading--small forceActionsText']")).getText());
		driver.close();
	}
}
