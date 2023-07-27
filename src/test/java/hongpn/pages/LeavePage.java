package hongpn.pages;

import hongpn.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LeavePage {
    private WebDriver driver;
    private ValidateHelper helper;
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
    private final By BuzzMenu = By.xpath("//ul[@class='oxd-main-menu']//span[normalize-space()='Dashboard']");

    //url Dashboard
    private  final String urlDashboard="/dashboard";
    //Header Page
    private final By headerPage=By.xpath("//h6[normalize-space()='Dashboard']");
    public LeavePage(WebDriver driver) {
        this.driver = driver;
        helper = new ValidateHelper(driver);
    }
}
