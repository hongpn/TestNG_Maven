package hongpn.testcases;

import hongpn.common.helpers.CaptureHelpers;
import hongpn.common.helpers.ExcelHelpers;
import hongpn.common.helpers.PropertiesFile;
import hongpn.common.helpers.ValidatingUIHelpers;
import hongpn.commons.BaseSetup;
import hongpn.commons.ValidateHelper;
import hongpn.pages.SignInPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
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
    private ValidatingUIHelpers helpers;
    @BeforeClass
    public void setUpBrowser() throws Exception {
        this.driver = getDriver();
        excelHelpers=new ExcelHelpers();
        PropertiesFile.setPropertiesFile();
        helpers=new ValidatingUIHelpers(driver);
        CaptureHelpers.startRecord("LoginHRM");
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
    public void CaptureScreen(ITestResult result) {
        helpers.waitForPageLoaded();
        //  if (ITestResult.FAILURE == result.getStatus()) {
        try {
            CaptureHelpers.CaptureScreenshot(driver, result.getName());
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }
    @AfterClass
    public void tearDownClass()throws Exception{
        helpers.waitForPageLoaded();
        CaptureHelpers.stopRecord();
    }
}
