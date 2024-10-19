package week2.day1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class S6_30_Delete_Dashboard {

	@Test
	public void deleteDashboard() throws InterruptedException
	{
		EdgeDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Login to Login | Salesforce 
		driver.get("https://login.salesforce.com");
		//Login to Login | Salesforce 
		driver.findElement(By.id("username")).sendKeys("gokul.sekar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$321");
		driver.findElement(By.id("Login")).click();
		
		//Click on the toggle menu button from the left corner
		driver.findElement(By.xpath("//button[@title='App Launcher']")).click();
		
		//Click View All and click Dashboards from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("(//input[@type='search'])[3]")).sendKeys("Dashboards");
		driver.findElement(By.xpath("//div[@class='slds-accordion__content']//li[1]")).click();
		
		//Click on the Dashboards tab
		WebElement element= driver.findElement(By.xpath("//a[@title='Dashboards']"));
		driver.executeScript("arguments[0].click()", element);
		
		//Search the Dashboard 'Salesforce Automation by *Your Name*'
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("test");
		Thread.sleep(2000);
		String before=driver.findElement(By.xpath("//div[@class='test-listViewStatusInfo']")).getText();
		String[] split = before.split(" ");
		//Click on the Dropdown icon and Select Delete
		driver.findElement(By.xpath("//tbody/tr/td[6]//button")).click();
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		
		//Click on the Delete option in the displayed popup window.
		driver.findElement(By.xpath("//button[@title='Delete']")).click();
		//Verify Whether Dashboard is Deleted using Dashboard 
		Thread.sleep(2000);
		System.out.println(driver.findElement(By.xpath("//div[@data-aura-class='forceToastMessage']//span[@data-aura-class='forceActionsText']")).getText());
//		driver.findElement(By.xpath("//input[@type='text']")).clear();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("test");
//		Thread.sleep(2000);
		String url=driver.getCurrentUrl();
		driver.get(url);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("test");
		Thread.sleep(2000);
		String after=driver.findElement(By.xpath("//div[@class='test-listViewStatusInfo']")).getText();
		String[] split1 = after.split(" ");
		//System.out.println(Integer.parseInt(split1[0]));
		//System.out.println(Integer.parseInt(split[0])-1);
		if(Integer.parseInt(split1[0])==Integer.parseInt(split[0])-1) System.out.println("Dashboard Deleted Successfully");
		else System.out.println("Dashboard not Deleted");
		driver.close();
	}
}
