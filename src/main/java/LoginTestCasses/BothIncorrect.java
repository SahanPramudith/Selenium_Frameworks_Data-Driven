package LoginTestCasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BothIncorrect {

    WebDriver driver;
    @Test
    public void loginBothInCorrect(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(" https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebElement useName = driver.findElement(By.name("username"));
        useName.sendKeys("Admin");
        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("admin123");
    }
}
