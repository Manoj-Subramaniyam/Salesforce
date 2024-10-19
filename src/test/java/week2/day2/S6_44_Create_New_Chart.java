package week2.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class S6_44_Create_New_Chart {
	@Test
	public void createNewChart() throws InterruptedException {
		EdgeDriver driver=new EdgeDriver();             
		driver.get("https://login.salesforce.com/"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys("gokul.sekar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$321");
		driver.findElement(By.id("Login")).click();
		
		//Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//button[@title='App Launcher']")).click();
		//Click view All and click Service Console from App Launcher
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//P[text()='Service Console']")).click();
		//Click on the drop down and select Refunds 
		driver.findElement(By.xpath("//button[@title='Show Navigation Menu']")).click();
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//a[@data-itemid='Refund']"));
		action.scrollToElement(element).perform();
		driver.findElement(By.xpath("//a[@data-itemid='Refund']")).click();
		
		//Click on drop down near Recently viewed and change into 'All'.
		
		driver.findElement(By.xpath("//button[@title='Select a List View: Refunds']")).click();
		driver.findElement(By.xpath("//span[text()='All Refunds']")).click();
		
		//Click on Chart icon under New Button 
		driver.findElement(By.xpath("//button[@title='Charts']")).click();
		
		//Click New Chart 
		element=driver.findElement(By.xpath("//a[@title='Settings']"));
		action.scrollToElement(element).perform();
		driver.findElement(By.xpath("//a[@title='Settings']")).click();
		driver.findElement(By.xpath("//a[@title='New Chart']")).click();
		
		//Give Chart Name and Select Chart Type
		driver.findElement(By.xpath("//input[@part='input']")).sendKeys("Manoj");
		driver.findElement(By.xpath("//button[@aria-label='Chart Type']")).click();
		driver.findElement(By.xpath("//span[@title='Vertical Bar Chart']")).click();
		Thread.sleep(1000);
		//Select Aggregate Type as Average and ggregate Field as Amount 
		driver.findElement(By.xpath("//button[@aria-label='Aggregate Type']")).click();
		driver.findElement(By.xpath("//span[@title='Average']")).click();
		driver.findElement(By.xpath("//button[@aria-label='Aggregate Field']")).click();
		driver.findElement(By.xpath("(//span[@title='Amount'])[2]")).click();
		
		//Select Grouping Field as Account and click Save 
		element=driver.findElement(By.xpath("//button[@aria-label='Grouping Field']"));
		action.scrollToElement(element).perform();
		driver.findElement(By.xpath("//button[@aria-label='Grouping Field']")).click();
		driver.findElement(By.xpath("//div[@aria-label='Grouping Field']//span[@title='Account']")).click();
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		
		//Click on settings icon and change the Chart Type
		System.out.println(driver.findElement(By.xpath("//a[@class='select']")).getText());
		Thread.sleep(1000);
		element=driver.findElement(By.xpath("//a[@title='Settings']"));
		action.scrollToElement(element).perform();
		driver.findElement(By.xpath("//a[@title='Settings']")).click();
		driver.findElement(By.xpath("//a[@title='Donut Chart']")).click();
		Thread.sleep(2000);
		driver.close();
		
	}
}
