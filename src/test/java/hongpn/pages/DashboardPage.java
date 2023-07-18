package hongpn.pages;

import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }
    public void signIn() {
        driver.get("http://localhost/litecart/admin/");
    }
}
