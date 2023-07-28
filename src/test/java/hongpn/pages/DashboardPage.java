package hongpn.pages;

import hongpn.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DashboardPage extends Menu  {
    private WebDriver driver;
    private ValidateHelper helper;

    // Element for Dashboard Page
    //menu


    //url Dashboard
    private  final String urlDashboard="/dashboard";
    //Header Page
    private final By headerPage=By.xpath("//h6[normalize-space()='Dashboard']");
    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        helper = new ValidateHelper(driver);
        setParameters(headerPage,urlDashboard,"Dashboard");
    }

}
