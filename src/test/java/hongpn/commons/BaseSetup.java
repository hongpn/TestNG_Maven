package hongpn.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import  java.lang.*;
import java.util.concurrent.TimeUnit;

public class BaseSetup {

    private WebDriver driver;
    public WebDriver getDriver() { return driver;}
@Parameters({"chrome", "https://opensource-demo.orangehrmlive.com/"})
    @BeforeClass
    private void setDriver(String browserType,String appURL) {
        switch (browserType) {
            case "firefox" -> initFirefoxDriver(appURL);
            case "ie" -> initIEDriver(appURL);
            case "safari" -> initSafariDriver(appURL);
            default -> initChromeDriver(appURL);
        }
    }
    private  void initChromeDriver(String appURL) {
        System.out.println("Launching Chrome browser...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    private void initFirefoxDriver(String appURL) {
        System.out.println("Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    private void initSafariDriver(String appURL) {
        System.out.println("Launching Safari browser...");
        driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    private void initIEDriver(String appURL) {
        System.out.println("Launching Safari browser...");
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
