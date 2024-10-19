package week1.day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class EditCase {

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
		//Click on Cases tab visible or select from more.
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='More']")).click();
		
		driver.executeScript( "arguments[0].click()",driver.findElement(By.xpath("//div[@role='menu']//span[text()='Cases']")));
		//Click on the Dropdown icon and select Edit from the case you created by reffering 'case owner alias'
		driver.findElement(By.xpath("//tr[1]/td[7]")).click();
		
		driver.executeScript( "arguments[0].click()",driver.findElement(By.xpath("//div[@title='Edit']")));
		//Update Status as Working
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='Status']//button")).click();
		driver.findElement(By.xpath("//div[@aria-label='Status']//lightning-base-combobox-item[@data-value='Working']")).click();
		//Update Priority to low
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='Priority']//button")).click();
		driver.findElement(By.xpath("//div[@aria-label='Priority']//lightning-base-combobox-item[@data-value='Low']")).click();
		//Update Case Origin as Phone
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='Case Origin']//button")).click();
		driver.findElement(By.xpath("//div[@aria-label='Case Origin']//lightning-base-combobox-item[@data-value='Phone']")).click();
		//Update SLA violation to No
		driver.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//records-record-layout-item[@field-label='SLA Violation']//button")));
		driver.findElement(By.xpath("//records-record-layout-item[@field-label='SLA Violation']//button")).click();
		driver.findElement(By.xpath("//div[@aria-label='SLA Violation']//lightning-base-combobox-item[@data-value='No']")).click();
		//Click on Save and Verify Status as Working
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		if(driver.findElement(By.xpath("//tbody/tr[1]/td[4]//span/span[1]")).getText().equals("Working"))
			System.out.println("Working is updated Successfully");
		else System.out.println("Working is not updated Successfully");
		driver.close();
		
	}
}
