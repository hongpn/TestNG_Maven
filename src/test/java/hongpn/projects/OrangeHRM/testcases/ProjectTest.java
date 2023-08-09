package hongpn.projects.OrangeHRM.testcases;

import hongpn.common.helpers.CaptureHelpers;
import hongpn.common.helpers.ExcelHelpers;
import hongpn.common.helpers.PropertiesFile;
import hongpn.common.helpers.ValidatingUIHelpers;
import hongpn.common.utilities.Log.Log;
import hongpn.commons.BaseSetup;
import hongpn.listeners.ReportListener;
import hongpn.projects.OrangeHRM.pages.DashboardPage;
import hongpn.projects.OrangeHRM.pages.PIMPage;
import hongpn.projects.OrangeHRM.pages.SignInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ReportListener.class)
@Epic("Regression Test HRM")
@Feature("Project Test")
public class ProjectTest extends BaseSetup{
    private WebDriver driver;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;
    //private WebUI webUI;
    @BeforeClass
    @Description("Init browser and corresppding classes")
    public void setupBrowser(){
        driver=new BaseSetup().setupDriver("chrome");
    }
    @Test(priority = 0, description = "Sign in to https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
    @Step("Sign in page to HRM page")
    public void SignInHRMPage() throws InterruptedException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/");
        Log.info("Starting SignInHRMPage");
        signInPage = new SignInPage(this.driver);
        dashboardPage= signInPage.SignIn("Admin", "admin123");
        //webUI.waitForPageLoaded();
    }
    @Test(priority = 1, description = "Search PIM")
    @Step("Click Pim filters")
    public void SearchEmployee() throws InterruptedException {
        //webUI.waitForPageLoaded();
        Log.info("Starting SearchEmployee");
        pimPage= dashboardPage.openPimMenu();
        pimPage.ClickToJobTitle();
        pimPage.ClickToElementStatus();
        pimPage.ClickToSubUnit();
        pimPage.ClickToInclude();
        pimPage.ClickSearch();
    }
    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
