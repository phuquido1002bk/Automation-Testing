package RunTest;

import Initation.*;
import parameters.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.lang.AssertionError;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.ITestContext;

public class TestUpdateAccount extends InitAccount {

	private By xpathUsernameBy = By.xpath("//input[@id=\"login\"]");
	private By xpathOldPassBy = By.xpath("/html/body/div/div/div/div[3]/form/div[2]/div/div/input");
	private By xpathNewPBy = By.xpath("//input[@id=\"newpassword\"]");
	private By xpathConfPassBy = By.xpath("//input[@id=\"confirmpassword\"]");
	private By xpathEmailBy = By.xpath("//input[@id=\"mail\"]");

	private String username, oldPass, newPass, confPass, expected;
	private void SendKeys(By path, String text) {
		this.driver.findElement(path).sendKeys(text);
	}
	
	@Test(dataProvider = "getData", dataProviderClass = DataProviderClass.class, priority = 0)
	public void ChangePassword(String[] array) throws InterruptedException {
		// =============================
		String username = array[0];
		String oldPass = array[1];
		String newPass = array[2];
		String confPass = array[3];
		String expected = array[4];
		// =============================
		this.SendKeys(xpathUsernameBy, username);
		this.SendKeys(xpathOldPassBy, oldPass);
		this.SendKeys(xpathNewPBy, newPass);
		this.SendKeys(xpathConfPassBy, confPass);

		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		assertEquals(driver.findElement(By.xpath("/html/body/div/div/div/div[2]")).getText(), expected);
	}

	@Test(dataProvider = "getData", dataProviderClass = DataProviderClass.class, priority = 1)
	public void ResetPassword(String[] array) throws InterruptedException {
		driver.findElement(By.linkText("Reset Password by Email")).click();
		// =============================
		String username = array[0];
		String email = array[1];
		String expected = array[2];
		// =============================

		this.SendKeys(xpathUsernameBy, username);
		this.SendKeys(xpathEmailBy, email);
		
		driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
		assertEquals(driver.findElement(By.xpath("/html/body/div/div/div/div[2]")).getText(), expected);

	}
	
	@AfterMethod
	public void ClearUsername() {
		driver.findElement(xpathUsernameBy).clear();
	}
}
