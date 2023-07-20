package hongpn.pages;

import hongpn.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SignInPage {
    WebDriver driver;
    ValidateHelper helper;
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        helper=new ValidateHelper(this.driver);
    }
    private final By UsernameInput=By.xpath("//input[@placeholder='Username']");
    private final By PasswordInput=By.xpath("//input[@placeholder='Password']");
    private final By LoginBtn=By.xpath("//button[normalize-space()='Login']");

    public DashboardPage SignIn(String usernameValue, String passwordValue)
    {
        // verify whether we're in login page or not
        Assert.assertTrue(helper.verifyTxtElement(LoginBtn, "Login"),"Access wrong login page!");
        helper.setText(UsernameInput,usernameValue);
        helper.setText(PasswordInput,passwordValue);
        helper.click(this.LoginBtn);
        return new DashboardPage(this.driver);
    }

}
