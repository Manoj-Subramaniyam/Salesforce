package week2.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class S6_42_Services_Sort_order {
	@Test
	public void servicesSorting() throws InterruptedException {
		EdgeDriver driver=new EdgeDriver();             
		driver.get("https://login.salesforce.com/"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Login to Login | Salesforce 
		driver.findElement(By.id("username")).sendKeys("gokul.sekar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$321");
		driver.findElement(By.id("Login")).click();
		
		//Click on the sliding icon until """"See System Status"""" is displayed
		boolean flag=true,temp;
		
		while (flag) {
			temp=driver.findElement(By.xpath("//span[text()='See System Status']")).isDisplayed();
			if(temp )
			{
				driver.findElement(By.xpath("//span[text()='See System Status']/following::button[1]")).click();
				flag=false;
			}
			else driver.findElement(By.xpath("//div[@class='rightScroll']/button")).click();
		}
		
		//Click confirm on the redirecting page and navigate to SalesForce Trust new Window.
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows= new LinkedList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		driver.findElement(By.xpath("//button[@onclick='goAhead()']")).click();
		
		//Select Trust Compliance from the dropdown
		driver.findElement(By.xpath("//a[text()='Compliance']")).click();
		
		//Click and navigate to Services tab
		driver.findElement(By.xpath("//a[text()='Services']")).click();
		Thread.sleep(3000);
		//Verify the Services are displayed in alphabetical order"" 
		List<WebElement> elements = driver.findElements(By.xpath("(//div[@class='row'])[2]//div[@class='mb-4 heading_medium']//span"));
		List<String> sorted= new ArrayList<String>();
		List<String> unsorted= new ArrayList<String>();
		for (int i = 1; i < elements.size(); i=i+2) {
			sorted.add(elements.get(i).getText().toLowerCase());
			unsorted.add(elements.get(i).getText().toLowerCase());
		}
		System.out.println(unsorted);
		Collections.sort(sorted);
		System.out.println(sorted);
		if(sorted.equals(unsorted)) System.out.println("Services in sorted order");
		else System.out.println("services not in sorted order");
		Thread.sleep(5000);
		driver.quit();
	}
		
}
