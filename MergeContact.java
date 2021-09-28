package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']/following-sibling::a")).click();
		
		//get the window handles
		Set<String> winSet = driver.getWindowHandles();
		//convert to list
		List<String> winList = new ArrayList<String>(winSet);
		driver.switchTo().window(winList.get(1)); //-- switch to the new window
		String title = driver.getTitle();
		System.out.println("The title of the page is: "+title);
		//reach the table
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[1]/a")).click();
		driver.switchTo().window(winList.get(0));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdTo']/following-sibling::a")).click();
		
		Set<String> winSet2 = driver.getWindowHandles();
		List<String> winList2=new ArrayList<String>(winSet2);
		driver.switchTo().window(winList2.get(1));
		String newTitle = driver.getTitle();
		System.out.println("The title of the second page is: "+newTitle);
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(winList.get(0));
		driver.findElement(By.linkText("Merge")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String finalTitle = driver.getTitle();
		System.out.println("The final title after merge is: "+finalTitle);
		if(finalTitle.equalsIgnoreCase("View Contact | opentaps CRM")){
			System.out.println("Merge Contact Successful");
		}else {
			System.out.println("Merge unsuccessful");
		}
	}
}
