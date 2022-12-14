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
	
	@BeforeTest
	public void SetUp() {
		System.setProperty("webdriver.chrome.driver", pathDriver);
    	
		this.driver = new ChromeDriver();
		this.driver.get(baseUrl);
		this.driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.driver.manage().window().maximize();
		
		assertEquals(this.driver.getTitle(), "BKEL - HỆ THỐNG HỖ TRỢ GIẢNG DẠY VÀ HỌC TẬP");
		this.driver.findElement(By.linkText("Log in")).click();
		
		assertEquals(this.driver.getTitle(), "BKEL - HỆ THỐNG HỖ TRỢ GIẢNG DẠY VÀ HỌC TẬP: Log in to the site");
		this.driver.findElement(By.linkText("Teachers and Students of HCMUT")).click();
		
		assertEquals(this.driver.getTitle(), "HCMUT – Central Authentication Service");

		this.driver.findElement(By.linkText("Change password?")).click();
		assertEquals(this.driver.getTitle(), "Change password");
	}
	
	@AfterTest
	public void Quit() {
		this.driver.quit();
	}
}
