package week2.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class S6_54_Delete_the_Lead_associated_to_Campaign_and_check {
	@Test
	public void deleteLead() throws InterruptedException {
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
		//Click on Leads tab 
		WebElement element = driver.findElement(By.xpath("//a[contains(@title,\"Leads\")]"));
		driver.executeScript("arguments[0].click()", element);
		Thread.sleep(1000);
		//Search for Lead with name as <your name> 
		driver.findElement(By.xpath("//input[@name='Lead-search-input']")).sendKeys("Manoj",Keys.ENTER);
		Thread.sleep(2000);
		//Confirm the Delete for Lead 
		driver.findElement(By.xpath("//tbody//tr[1]/td[10]//a")).click();
		driver.findElement(By.xpath("//a[@title='Delete']")).click();
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		element=driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
		if(element.isDisplayed()) {
			String input=element.getText();
			System.out.println(input);
			System.out.println("Lead is deleted successfully");
		}
		else System.out.println("Lead is not deleted");
		//Click on Campaign tab 
		element=driver.findElement(By.xpath("//a[contains(@title,\"Campaigns\")]"));
		driver.executeScript("arguments[0].click()", element);
		//Click Bootcamp link 
		driver.findElement(By.xpath("//input[@name='Campaign-search-input']")).sendKeys("Bootcamp",Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//tbody/tr[1]/th//a")).click();
		
		//Verify the associated Lead 
		driver.findElement(By.xpath("//span[@title='Campaign Members']/parent::a")).click();
		element=driver.findElement(By.xpath("(//div[@data-aura-class='forceListViewManagerGrid']//lightning-formatted-rich-text[@class='slds-rich-text-editor__output']//span)[2]"));
		if(element.isDisplayed()) {
			String input=element.getText();
			System.out.println(input);
			System.out.println("No leads are displayed as expected");
		}
		else System.out.println("leads are displayed - Failed");
		driver.close();
	}
}
