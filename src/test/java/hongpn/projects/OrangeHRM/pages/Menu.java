package hongpn.projects.OrangeHRM.pages;

import hongpn.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Menu {
    private WebDriver driver;
    private ValidateHelper helper;

    private By headerPage;
    private String expectedHeader;
    private String url;
    // Element for Dashboard Page
    //menu
    private final By adminMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Admin']");
    private final By searchMenuInput = By.xpath("//ul[@class='oxd-main-menu']//input[@placeholder='Search']]");
    private final By pimMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='PIM']");
    private final By leaveMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Leave']");
    private final By recruitmentMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Recruitment']");
    private final By myInfoMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='My Info']");
    private final By performanceMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Performance']");
    private final By dashBoardMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Dashboard']");
    private final By directoryMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Directory']");
    private final By maintenanceMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Maintenance']");
    private final By claimMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Dashboard']");
    private final By BuzzMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Buzz']");
    public Menu(WebDriver driver) {
        this.driver = driver;
        helper = new ValidateHelper(driver);
    }
    public  void setParameters(By headerPagePar, String urlPar,String expectedHeaderPar)
    {
        headerPage=headerPagePar;
        url=urlPar;
        expectedHeader=expectedHeaderPar;
    }
    public AdminPage openAdminMenu() {
        helper.waitForJSLoaded();
        Assert.assertTrue(helper.verifyUrl(url), "Wrong link " + expectedHeader);
        Assert.assertTrue(helper.verifyTxtElement(headerPage, expectedHeader), "Wrong header " + expectedHeader);
        helper.click(adminMenu);
        return new AdminPage(driver);
    }
    public PIMPage openPimMenu()
    {
        helper.waitForJSLoaded();
        Assert.assertTrue(helper.verifyUrl(url), "Wrong link " + expectedHeader);
        Assert.assertTrue(helper.verifyTxtElement(headerPage, expectedHeader), "Wrong header " + expectedHeader);
        helper.click(pimMenu);
        return new PIMPage(driver);
    }
    public LeavePage openLeaveMenu()
    {
        helper.waitForJSLoaded();
        Assert.assertTrue(helper.verifyUrl(url), "Wrong link " + expectedHeader);
        Assert.assertTrue(helper.verifyTxtElement(headerPage, expectedHeader), "Wrong header " + expectedHeader);
        helper.click(leaveMenu);
        return new LeavePage(driver);
    }
}
