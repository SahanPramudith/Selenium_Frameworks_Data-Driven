package DataDrivenUsingExcel;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class DataDriveTestWithExcel {



//    public void readExcelData() throws IOException {
//        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\testData\\LoginData.xlsx");
//        XSSFWorkbook workbook = new XSSFWorkbook(file);
//        XSSFSheet sheet = workbook.getSheet("Sheet1");
//        int totalRow = sheet.getLastRowNum();
//        int TotalCellNum = sheet.getRow(0).getLastCellNum();
//        System.out.println("TotalCellNum = " + TotalCellNum);
//        System.out.println("totalRow = " + totalRow);
//
//        for(int i=1;i<totalRow;i++){
//            XSSFRow  row = sheet.getRow(i);
//            for (int j = 0; j < TotalCellNum; j++) {
//                XSSFCell cell = row.getCell(j);
//                String celval = cell.toString();
//                System.out.print( celval+"\t\t");
//            }
//            System.out.println();
//        }
//    }


//    @DataProvider(name = "loginData")
//    public Object[][] dataProvider(){
//        String[][] data={
//                {"Admin","admin123","valid"},
//                {"a","admin1523","Invalid"},
//                {"Aodmin","admin1253","Invalid"},
//        };
//        return data;
//    }

//}

@DataProvider(name = "loginData")
public Object[][] excelDataProvider() throws IOException {
    FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\testData\\LoginData.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(file);
    XSSFSheet sheet = workbook.getSheet("Sheet1");
    int totalRow = sheet.getLastRowNum();
    int totalCell = sheet.getRow(0).getLastCellNum();

    String[][] data = new String[totalRow][totalCell];
    for (int i = 1; i <= totalRow; i++) {
        for (int j = 0; j < totalCell; j++) {
            data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
        }
    }
    workbook.close();
    file.close();
    return data;


}

    WebDriver driver;
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

        if (expectValidation.equals("Valid")){
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
