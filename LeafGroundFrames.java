package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundFrames {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		WebElement frame = driver.findElement(By.xpath("(//div[@id='wrapframe']/iframe)[1]"));
		driver.switchTo().frame(frame);
		WebElement buttonClick = driver.findElement(By.id("Click"));
		File src= buttonClick.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/ClickButton.png");
		FileUtils.copyFile(src, dst);
		driver.switchTo().defaultContent();
		List<WebElement> iframeList = driver.findElements(By.tagName("iframe"));
		System.out.println("The number of frames in this page is: "+iframeList.size());
		
	}

}
