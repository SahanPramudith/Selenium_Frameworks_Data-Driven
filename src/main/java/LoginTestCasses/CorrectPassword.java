package LoginTestCasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CorrectPassword {
    WebDriver driver;
    @Test
    public void CorrectPassword(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(" https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebElement useName = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        useName.sendKeys("Admini");
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("admin123");
    }
    @AfterMethod
    public void colseBorw(){
        driver.quit();
    }
}
