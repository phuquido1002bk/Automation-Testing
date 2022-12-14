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
import org.testng.annotations.AfterTest;

public class InitProfile {
	public WebDriver driver = null;

	private String baseUrl = "https://e-learning.hcmut.edu.vn/";
	private String pathDriver = "E:\\Program File\\ChromeDriver\\chromedriver.exe";

	private void Login() {
		assertEquals(this.driver.getTitle(), "HCMUT – Central Authentication Service");
		this.driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("qui.dobk1002cla");
		this.driver.findElement(By.xpath("//input[@id=\"password\"]")).sendKeys("2014289ddpq");
		this.driver.findElement(By.xpath("//*[@id=\"fm1\"]/div[4]/input[4]")).click();
	}

	private void goToProfile() {
		assertEquals(this.driver.getTitle(), "BKEL - HỆ THỐNG HỖ TRỢ GIẢNG DẠY VÀ HỌC TẬP");
		this.driver.findElement(By.xpath("//*[@id=\"user-menu-toggle\"]")).click();
		this.driver.findElement(By.xpath("//*[@id=\"carousel-item-main\"]/a[1]")).click();
	}

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
		
		this.Login();
		this.goToProfile();
		
		this.driver.findElement(By.linkText("Sửa hồ sơ cá nhân")).click();
		assertEquals(this.driver.getTitle(), "BKEL: Sửa hồ sơ cá nhân");
	}

	@AfterTest
	public void Quit() {
		this.driver.quit();
	}
}
