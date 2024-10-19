package week2.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class s6_17_Create_Opportunity_without_mandatory_field {
	
	@Test
	public void createOpportunity() throws InterruptedException {
		EdgeDriver driver=new EdgeDriver();             
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
		//Click on Opportunity tab 
		WebElement element =driver.findElement(By.xpath("//a//span[text()='Opportunities']"));
		driver.executeScript("arguments[0].click()", element);
		//Click on New button  
		driver.findElement(By.xpath("//a//div[@title='New']")).click();
		//Choose Close date as Tomorrow Date
		driver.findElement(By.xpath("//input[@name='CloseDate']")).click();
		driver.findElement(By.xpath("//table[@class='slds-datepicker__month']//td[@class='slds-is-today']/following::td[1]")).click();
		// Click on save 
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		// Verify the Alert message (Complete this field) displayed for Name and Stage 
		WebElement alert=driver.findElement(By.xpath("//label[text()='Opportunity Name']/following::div[@class='slds-form-element__help']"));
		WebElement alert1=driver.findElement(By.xpath("//label[text()='Stage']/following::div[@class='slds-form-element__help']"));
		if(alert.isDisplayed() && alert1.isDisplayed())
			System.out.println("Alert Message is displayed");
		else System.out.println("Alert Message is Not displayed");
		driver.close();
}		
}
