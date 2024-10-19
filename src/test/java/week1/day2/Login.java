package week1.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;


public class Login {
	@Test
	public void loginPage() throws InterruptedException {
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
		//Click on Accounts tab 
		
		WebElement element = driver.findElement(By.xpath("//a//span[text()='Accounts']"));
		driver.executeScript("arguments[0].click()", element);
		//Click on New button
		driver.findElement(By.xpath("//a//div[@title='New']")).click();
		//Enter 'your name' as account name
		driver.findElement(By.xpath("//input[@name='Name']")).sendKeys("Manoj");
		//Select Ownership as Public                                       
		driver.findElement(By.xpath("//button[@aria-label='Ownership']")).click();
		driver.findElement(By.xpath("//div//*[text()='Public']")).click();
		//Click save and verify Account name 
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		//Expected Result:
		//Account should be created Successfully
		if(driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']")).isDisplayed()) {
			System.out.println(driver.findElement(By.xpath("//div[@role='alert']/following::a/div")).getText());
			System.out.println("Account created Successfully");
		}
			
		else System.out.println("Account not created ");
		//Thread.sleep(2000);
		driver.close();
		
	}
	//org.openqa.selenium.NoSuchElementException
	//org.openqa.selenium.WebDriverException
	//org.openqa.selenium.JavaScriptException
	//org.openqa.selenium.ElementClickInterceptedException
}
