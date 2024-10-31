package week2.day2;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.sukgu.Shadow;

public class S6_41_Non_Profit_Certifications {
	@Test
	public void nonProfitCertification() throws InterruptedException {
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

		//Click on the 'Visit Site' for Trailhead under 'Resources'
		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//a[text()='Visit Site' and @href='https://trailhead.salesforce.com/']"));
		action.scrollToElement(element);
		element.click();

		//Click on the 'Industries'
		Shadow sDom= new Shadow(driver);
		sDom.findElementByXPath("//span[text()='Industries']").click();

		//Click on the 'Nonprofit'
		element=sDom.findElementByXPath("//span[text()='Nonprofit']");
		action.scrollToElement(element);
		element.click();

		//Click on the 'Start Learning' for 'Salesforce for Nonprofits Basics'
		Thread.sleep(2000);
		element = sDom.findElementByXPath("//a[@aria-label='Start learning: Salesforce for Nonprofits Basics']");
		action.scrollToElement(element);
		element.click();
		Thread.sleep(3000);
		//title[contains(text(),'Salesforce for Nonprofits Basics')]


		if((driver.getTitle().split("\\|"))[0].trim().equals("Salesforce for Nonprofits Basics"))
		{
			if(driver.findElement(By.xpath("//a[@aria-label='Nonprofit']/h5")).getText().equals("Nonprofit"))
				System.out.println("Title is 'Salesforce for Nonprofits Basics' and Industries is 'Nonprofit'");
			else System.out.println("Not in Nonprofit industry");
		}
		else System.out.println("Not in nonprofit page");

		driver.quit();
	}
}
