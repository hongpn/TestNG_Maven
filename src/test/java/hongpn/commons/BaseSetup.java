package hongpn.commons;

import org.openqa.selenium.WebDriver;

public class BaseSetup {
    static final String driverPath = "resources\\driver\\";
    private WebDriver driver;
    public WebDriver getDriver() { return driver;}
    private void setDriver(String browserType,String appURL) {}
}
