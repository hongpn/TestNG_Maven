package hongpn.testcases;

import hongpn.common.helpers.ExcelHelpers;
import hongpn.common.helpers.PropertiesFile;
import hongpn.commons.BaseSetup;
import hongpn.commons.ValidateHelper;
import  hongpn.pages.SignInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class SignInTest extends BaseSetup {
    private WebDriver driver;
    private ValidateHelper validateHelper;

    private SignInPage signInPage;
    private ExcelHelpers excelHelpers;
    @BeforeClass
    public void setUpBrowser()  {
        this.driver = getDriver();
        excelHelpers=new ExcelHelpers();
        PropertiesFile.setPropertiesFile();
//        PropertiesFile.getPropValue("browser");
//        System.out.println(PropertiesFile.getPropValue("browser"));
//        System.out.println(PropertiesFile.getPropValue("username"));
//        System.out.println(PropertiesFile.getPropValue("password"));
    }
    private By searchField=By.xpath("//textarea[@id='APjFqb']");
    private By searchButton=By.xpath("(//input[@name='btnK'])[2]");

    public void SearchOnGG() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com.vn/");
        validateHelper=new ValidateHelper(driver);
        validateHelper.setText(searchField, "BBC");
        validateHelper.click(searchButton);
        Thread.sleep(5000);
        driver.quit();
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
        excelHelpers.setCellData("Passed",1,2);
        PropertiesFile.setPropValue("result","passed");
        Thread.sleep(5000);
    }


}
