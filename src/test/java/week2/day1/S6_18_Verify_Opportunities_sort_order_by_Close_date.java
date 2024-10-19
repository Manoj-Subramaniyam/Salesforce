package week2.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class S6_18_Verify_Opportunities_sort_order_by_Close_date {
	@Test
	public void verifyOpportunities() throws InterruptedException {
		
		EdgeOptions options = new EdgeOptions();
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
		//Click on Opportunity tab 
		WebElement element =driver.findElement(By.xpath("//a//span[text()='Opportunities']"));
		driver.executeScript("arguments[0].click()", element);
		//Select the Table view
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@title='Select list display']")).click();
		
		element=driver.findElement(By.xpath("//li[@title='Display as table']"));
		driver.executeScript("arguments[0].click()", element);
		Thread.sleep(2000);
		
		// Sort the Opportunities by Close Date in ascending order
		element=driver.findElement(By.xpath("//span[@title='Close Date']"));
		driver.executeScript("arguments[0].click()", element);
		Thread.sleep(2000);
		driver.executeScript("arguments[0].click()", element);
		
		//Verify the Opportunities displayed in ascending order by Close date
		List<WebElement> elements = driver.findElements(By.xpath("//tbody/tr/td[6]//span[@class='uiOutputDate']"));
		String count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText();
		String[] split = count.split(" ");
		int size=elements.size();
		
		//System.out.println(size);
		//System.out.println(split[0]);
		Actions action = new Actions(driver);
			while(!split[0].equals(String.valueOf(size)))
			{
				elements.clear();
				// adding all the elements in a list which is available
				elements.addAll( driver.findElements(By.xpath("//tbody/tr/td[6]//span[@class='uiOutputDate']")));
				//Thread.sleep(2000);
				//scrolling till the last element using list-1
				action.scrollToElement(elements.get(elements.size()-1)).perform();
				//Thread.sleep(5000);			
				//finding new size
				size = driver.findElements(By.xpath("//tbody/tr")).size();
				//System.out.println(size);
				//getting count on each time which display on left top corner
				count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText();
				//System.out.println(count);
				split = count.split(" ");
			}
			Thread.sleep(2000);
			elements.clear();
			elements.addAll( driver.findElements(By.xpath("//tbody/tr/td[6]//span[@class='uiOutputDate']")));
			List<String> sorted = new ArrayList<String>();
			List<String> unsorted = new ArrayList<String>();
			String[] temp = new String[3];
			String temp1="";
		for (int i = 0; i <elements.size(); i++) {
			temp=elements.get(i).getText().split("/");
			for (int j = temp.length-1; j > -1; j--) {
				if(j!=0)
				temp1+=temp[j]+"/";
				else temp1+=temp[j];
			}
			sorted.add(temp1);
			unsorted.add(temp1);
			temp1="";
		}
		Collections.sort(sorted);
		System.out.println(sorted); // Manual sort on java
		System.out.println(unsorted); // UI picked values
		sorted.equals(unsorted);
//		int count1=0;
//		for (int i = 0; i < elements.size(); i++) {
//			if(sorted.get(i).equals(unsorted.get(i)))
//				count1+=1;
//			else 
//			{
//				System.out.println("Date not in sorted order");
//				break;
//			}
//		}
		if(sorted.equals(unsorted)) System.out.println("Date in sorted order");
		else System.out.println("Date Not in sorted order");
		//Thread.sleep(15000);
		driver.close();
	}
}
