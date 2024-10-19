package week2.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class S6_43_Create_New_Refund {

	@Test
	public void createNewRefund() throws InterruptedException {
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
		
		Thread.sleep(2000);
		//Click on New 
		element=driver.findElement(By.xpath("//a[@title='New']"));
		//action.click(element);
		driver.executeScript("arguments[0].click()",element);
		
		//Select Account name
		driver.findElement(By.xpath("//div[@class='listContent']//li[1]")).click();
		
		//Select Status as Canceled
		driver.findElement(By.xpath("//span[text()='Status']/parent::span//following-sibling::div//a")).click();
		driver.findElement(By.xpath("//a[@title='Canceled']")).click();
		
		//Give Amount as 50000 and select Referenced in Type
		driver.findElement(By.xpath("//span[text()='Amount']/parent::label//following-sibling::input")).sendKeys("50000");
		driver.findElement(By.xpath("//span[text()='Type']/parent::span//following-sibling::div//a")).click();
		driver.findElement(By.xpath("//a[@title='Referenced']")).click();
		
		//Select Processing Mode as External 
		element =driver.findElement(By.xpath("//span[text()='Processing Mode']/parent::span//following-sibling::div//a"));
		action.scrollToElement(element).perform();
		driver.findElement(By.xpath("//span[text()='Processing Mode']/parent::span//following-sibling::div//a")).click();
		driver.findElement(By.xpath("//a[@title='External']")).click();
		
		//Click Save
		driver.findElement(By.xpath("//button[@title='Save']")).click();
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']")).getText());
	
		driver.close();
	}
	
}
