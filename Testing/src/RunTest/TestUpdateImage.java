package RunTest;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import Initation.*;
import parameters.DataProviderClass;

public class TestUpdateImage extends InitProfile {
	private String pathCancel = "/html/body/div[4]/div[4]/div/div[2]/div/section/div/form/div[3]/div[2]/fieldset/div/div[2]/span/input";
	private String pathInsert = "/html/body/div[3]/div[4]/div/div[2]/div/section/div/form/fieldset[2]/div/div[3]/div[2]/fieldset/div[1]/div[2]/div[1]/div[1]/div[1]/a";

	private String pathUploadFile = "/html/body/div[8]/div[3]/div/div[2]/div/div/div[2]/div/div[2]/div/div/div/button";
	private String pathChooseFileString = "/html/body/div[8]/div[3]/div/div[2]/div/div/div[2]/div/div[2]/div/div/form/div/div[1]/div/input";
	
	private String[] fileExtension = { ".gif", ".jpe", ".jpeg", ".jpg", ".png"};
	
	private String pathFileImage = "E:\\phuqui\\workspaces\\Testing\\Testing\\src\\data\\file\\";
	
	private boolean CheckFile(String filename) {
		File file = new File(this.pathFileImage + filename);
		if (file.length() > 524288000 || file.length() == 0) {
			return false;
		}
		for (String string : this.fileExtension) {
			if (filename.endsWith(string))
				return true;
		}
		return false;
	}
	
	@Test(dataProvider = "getFile", dataProviderClass = DataProviderClass.class)
	public void UpdateImage(String[] array) throws InterruptedException {
		String filename = array[0];
		String state = array[1];
		String expected = array[2];
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.linkText("Ảnh người dùng")));
		
		this.driver.findElement(By.xpath(pathInsert)).click();
		this.driver.findElement(By.xpath(pathChooseFileString)).sendKeys(this.pathFileImage + filename);;
		Thread.sleep(2000);

		if (CheckFile(filename)) {
			this.driver.findElement(By.xpath(pathUploadFile)).click();
			Thread.sleep(2000);
			if (state.equals("Cancel")) {
				this.driver.findElement(By.xpath(pathCancel)).click();
				assertEquals(this.driver.findElement(By.linkText("Sửa hồ sơ cá nhân")).getText(), expected);
				this.driver.findElement(By.linkText("Sửa hồ sơ cá nhân")).click();
			}
		} else {
			this.driver.findElement(By.xpath(pathUploadFile)).click();
			assertEquals(this.driver.findElement(By.xpath("/html/body/div[10]/div[3]/div/div[1]/h5")).getText(), expected);
			
			//reload page
			this.driver.navigate().refresh();
		}
		Thread.sleep(1000);
	}
}
