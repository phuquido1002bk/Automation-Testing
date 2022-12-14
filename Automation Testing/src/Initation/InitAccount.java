package Initation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class InitAccount {
	public WebDriver driver = null;
	private String baseUrl = "https://e-learning.hcmut.edu.vn/";
	private String pathDriver = "E:\\Program File\\ChromeDriver\\chromedriver.exe";
	protected int count = 0;
	@BeforeTest
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", pathDriver);
    	
		this.driver = new ChromeDriver();
		this.driver.get(baseUrl);
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
		
		this.driver.findElement(By.linkText("Log in")).click();
		
		assertEquals(this.driver
				.findElement(By
						.xpath("/html/body/div[2]/div[2]/div/div/section/div/div/div/div/div[3]/h2")
						).getText(), "Log in using your account on:");
		this.driver.findElement(By.linkText("Teachers and Students of HCMUT")).click();
		
		assertEquals(this.driver
				.findElement(By
						.xpath("/html/body/div/div/div[1]/div/h1")
						).getText(), "Central Authentication Service");
		this.driver.findElement(By.linkText("Change password?")).click();
	}
	@AfterTest
	public void Quit() {
		this.driver.quit();
	}
}
