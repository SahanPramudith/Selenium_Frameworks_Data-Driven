package DataDrivenTestingDataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginUsingDataProvider {
    WebDriver driver;

    @DataProvider(name = "loginData")
    public Object[][] dataProvider(){
        String[][] data={
                {"Admin","admin123","valid"},
                {"a","admin1523","Invalid"},
                {"Aodmin","admin1253","Invalid"},
        };
        return data;
    }
    @Test(dataProvider = "loginData")
    public void loginTestScenario(String username ,String password,String expectValidation) throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(" https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebElement useName = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        useName.sendKeys(username);
        WebElement pasword = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        pasword.sendKeys(password);

        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();

        boolean urlVerification = driver.getCurrentUrl().contains("dashboard");
        Thread.sleep(3000);

        if (expectValidation.equals("valid")){
            Assert.assertTrue(urlVerification,"Login success but not navigate the dashboard");
        } else if (expectValidation.equals("Invalid")) {
            Assert.assertFalse(urlVerification,"Expecting login is success but navigate the dashboard");
        }
    }
    @AfterMethod
    public void colseBorw(){
        driver.quit();
    }
}
