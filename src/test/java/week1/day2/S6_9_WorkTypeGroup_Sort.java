package week1.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class S6_9_WorkTypeGroup_Sort {
	@Test
	public void verifySorting() throws InterruptedException {
		EdgeDriver  driver=new EdgeDriver();             
		driver.get("https://login.salesforce.com/"); 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.id("username")).sendKeys("gokul.sekar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Leaf$321");
		driver.findElement(By.id("Login")).click();
		
		//Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//button[@title='App Launcher']")).click();
		//Click View All and click Work Type Groups from App Launcher 
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//div[@type='search']/input")).sendKeys("Work Type Groups");
		
		driver.executeScript("arguments[0].click()",  driver.findElement(By.xpath("//a[@data-label='Work Type Groups']")));
		//Click on the Work Type Group tab 
		driver.executeScript("arguments[0].click()",  driver.findElement(By.xpath("(//a[contains(@title,'Work Type Groups')])[2]")));
		//Click the sort arrow in the Work Type Group Name 
		driver.findElement(By.xpath("//span[@title='Work Type Group Name']/parent::a")).click();
		//Verify the Work Type Group displayed in ascending order by Work Type Group Name. 
		String count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText();
		//System.out.println(count);
		String[] length = count.split(" ");
		int size = driver.findElements(By.xpath("//tbody/tr")).size();
		//System.out.println(size);
		List<WebElement> name=new ArrayList<WebElement>();
		Actions action = new Actions(driver);
		while(!length[0].equals(String.valueOf(size)))
		{
			name.clear();
			// adding all the elements in a list which is available
			name.addAll( driver.findElements(By.xpath("//tbody/tr/th//a")));
			//Thread.sleep(2000);
			//scrolling till the last element using list-1
			action.scrollToElement(driver.findElement(By.xpath("(//tbody/tr/th//a)["+ (name.size()-1)+"]"))).perform();
			//Thread.sleep(5000);			
			//finding new size
			size = driver.findElements(By.xpath("//tbody/tr")).size();
			//getting count on each time which display on left top corner
			count = driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText();
			//System.out.println(count);
			length = count.split(" ");
		}
		List<String> unsorted=new ArrayList<String>();
		List<String> sorted=new ArrayList<String>();
		
		for (int i = 0; i < name.size(); i++) {
			unsorted.add( name.get(i).getText().toLowerCase());
			sorted.add( name.get(i).getText().toLowerCase());
		}
		Collections.sort(sorted);
		int count1=0;
		for (int i = 0; i < name.size(); i++) {
			if(sorted.get(i).equals(unsorted.get(i)))
				count1+=1;
			else 
			{
				System.out.println("WorkTypeGroup not in sorted order");
				break;
			}
		}
		if(name.size()==count1) System.out.println("WorkTypeGroup in sorted order");
		Thread.sleep(2000);
		driver.close();
	}
}
