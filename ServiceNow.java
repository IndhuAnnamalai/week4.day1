package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev112969.service-now.com/");
		driver.manage().window().maximize();
		WebElement frameElement = driver.findElement(By.xpath("(//iframe[@id='gsft_main'])[1]"));
		driver.switchTo().frame(frameElement);
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("SelSep@2021");
		driver.findElement(By.id("sysverb_login")).click();
		//driver.switchTo().defaultContent();
		driver.findElement(By.id("filter")).sendKeys("incident");
		driver.findElement(By.id("filter")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//ul[@aria-label='Modules for Application: Incident']/li[6]//div//a/div/div")).click();
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("sysverb_new")).click();
		//WebElement frame3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		//driver.switchTo().frame(frame3);
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']/span")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winList.get(1));
		driver.findElement(By.linkText("System Administrator")).click();
		driver.switchTo().window(winList.get(0));
		System.out.println("The title is: "+driver.getTitle());
		driver.switchTo().defaultContent();
		//Thread.sleep(1000);
		WebElement frame3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame3);
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Administrator");
		
		String incidentNumber = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("The incident number is: "+incidentNumber);
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		driver.findElement(By.xpath(" (//input[@class='form-control'])[1]")).sendKeys(incidentNumber);
		driver.findElement(By.xpath(" (//input[@class='form-control'])[1]")).sendKeys(Keys.ENTER);
		System.out.println("Incident created successufully");
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dst=new File("./snaps/incident.png");
		FileUtils.copyFile(src, dst);

	
	}
	

}
