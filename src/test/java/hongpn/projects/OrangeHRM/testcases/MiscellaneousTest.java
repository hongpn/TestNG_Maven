package hongpn.projects.OrangeHRM.testcases;

import hongpn.common.helpers.ValidatingUIHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;


public class MiscellaneousTest {
    public WebDriver driver;
    private ValidatingUIHelpers helpers;
    private WebDriverWait wait;
    public MiscellaneousTest()
    {
        driver=new ChromeDriver();
        driver.get("https://files.fm/");
        helpers=new ValidatingUIHelpers(driver);
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test
    public void testUploadFile1() throws InterruptedException {



        Thread.sleep(2000);

        //By textOnPage = By.xpath("//div[@id='file_select_dragndrop_text']");
        By divFileUpload = By.xpath("//div[@id='uploadifive-file_upload']");
        By startUpload=By.xpath("//div[@id='savefiles']");
        //By inputFileUpload = By.xpath("//span[@class='select_files_button_text']");

        String filePath = "D:\\Tester\\JavaProject\\TestNG_Maven\\logs\\app-properties.log";

        //Click để mở form upload
        helpers.uploadFile(divFileUpload,filePath);
        helpers.clickElement(startUpload);

        Thread.sleep(4000);
    }
}
