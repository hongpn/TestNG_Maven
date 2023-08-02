package hongpn.testcases;

import hongpn.common.helpers.ExcelHelpers;
import hongpn.common.helpers.PropertiesFile;
import hongpn.commons.BaseSetup;
import hongpn.commons.ValidateHelper;
import hongpn.pages.SignInPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;

public class ManyTests extends BaseSetup {
    private WebDriver driver;
    private ValidateHelper validateHelper;
    private SignInPage signInPage;
    private ExcelHelpers excelHelpers;
    @BeforeClass
    public void setUpBrowser()  {
        this.driver = getDriver();
        excelHelpers=new ExcelHelpers();
        PropertiesFile.setPropertiesFile();
    }
    @Test(priority = 0)
    public void SignInHRMPage() throws Exception {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver = super.getDriver();
        signInPage = new SignInPage(this.driver);
        excelHelpers.setExcelFile("src/test/resources/Book1.xlsx","Sheet1");
        String username=excelHelpers.getCellData("username",1);
        System.out.println("Username: "+username);
        String password=excelHelpers.getCellData("password",1);
        System.out.println("Password: "+password);
        signInPage.SignIn(username, password);
        Thread.sleep(2000);
    }
    @AfterMethod
    public void CaptureScreen(ITestResult result)
    {
      //  if (ITestResult.FAILURE == result.getStatus()) {
            try {
                // Tạo tham chiếu của TakesScreenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                // Gọi hàm capture screenshot - getScreenshotAs
                File source = ts.getScreenshotAs(OutputType.FILE);
                //Kiểm tra folder tồn tại. Nêu không thì tạo mới folder
                File theDir = new File("./Screenshots/");
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                // result.getName() lấy tên của test case xong gán cho tên File chụp màn hình
                FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".png"));
                System.out.println("Đã chụp màn hình: " + result.getName());
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }
   // }
}
