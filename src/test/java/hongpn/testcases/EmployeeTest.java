package hongpn.testcases;

import hongpn.commons.BaseSetup;
import hongpn.commons.ValidateHelper;
import hongpn.pages.DashboardPage;
import hongpn.pages.PIMPage;
import hongpn.pages.SignInPage;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseSetup {
    private WebDriver driver;
    private ValidateHelper validateHelper;

    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @Test(priority = 0)
    public void SignInHRMPage() throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver = super.getDriver();
        signInPage = new SignInPage(this.driver);
        dashboardPage= signInPage.SignIn("Admin", "admin123");
        Thread.sleep(5000);
    }
    @Test(priority = 1)
    public void SearchEmployee() throws InterruptedException {
        pimPage= dashboardPage.openPimMenu();
        pimPage.ClickToJobTitle();
        pimPage.ClickToElementStatus();
        pimPage.ClickToSubUnit();
        pimPage.ClickToInclude();
        pimPage.ClickSearch();
    }

}

