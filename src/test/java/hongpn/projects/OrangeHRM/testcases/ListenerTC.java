package hongpn.projects.OrangeHRM.testcases;

import hongpn.commons.BaseSetup;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
// when running by testlistenersuite xml, it should be framed
//@Listeners(TestListener.class)
public class ListenerTC {
    private WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver=new BaseSetup().setupDriver("chrome");
    }

    @Test(priority = 1) //Success Test
    public void gotoPage() {
        driver.get("https://anhtester.com");
    }

    @Test(priority = 2) //Failed Test
    public void checkTitle() {
        String expectedTitle = "Anh Tester";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, expectedTitle, "Title of the website do not match");
    }

    @Test(priority = 3)  //Skip Test
    public void skipTest() {
        throw new SkipException("Skipping The Test Method ");
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
