package hongpn.testcases;

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

    public void setUpBrowser()  {
        this.driver = getDriver();}
    private By searchField=By.xpath("//textarea[@id='APjFqb']");
    private By searchButton=By.xpath("(//input[@name='btnK'])[2]");
    @Test
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

    @Test
    public void SignInHRMPage() throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver = super.getDriver();
        signInPage = new SignInPage(this.driver);
        signInPage.SignIn("Admin", "admin123");
        Thread.sleep(5000);
    }


}
