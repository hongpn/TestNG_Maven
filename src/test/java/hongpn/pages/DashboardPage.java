package hongpn.pages;

import hongpn.commons.ValidateHelper;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    private ValidateHelper helper;
    // Element for Dashboard Page

    //url Dashboard Page

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        helper=new ValidateHelper(driver);
    }
    public void signIn() {
        driver.get("http://localhost/litecart/admin/");
    }

    public void openABCPage()
    {
        helper.waitForJSLoaded();
        // Validate URL
        // Validate Content
        //Click to open
    }
}
