package hongpn.common.helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class ValidatingUIHelpers {
    private WebDriver driver;
    private WebDriverWait wait;
    private final Actions actions;
    private  Select select;
    private final int timeoutWait=10;
    private final int timeoutWaiForPageLoaded = 20;
    private JavascriptExecutor js;
    public   ValidatingUIHelpers(WebDriver driver)
    {
        this.driver=driver;
        wait= new WebDriverWait(driver,Duration.ofSeconds(timeoutWait));
        js=(JavascriptExecutor)driver;
        actions=new Actions(driver);
    }
    public String getPageTitle()
    {
        waitForPageLoaded();
        System.out.println("Current Page Title: "+driver.getTitle());
        return driver.getTitle();
    }
    public boolean verifyPageTitle(String pageTitle)
    {
        waitForPageLoaded();
        return getPageTitle().equals(pageTitle);
    }
    public boolean verifyPageUrl(String pageUrl)
    {
        System.out.println("Current URL: "+driver.getCurrentUrl());
        return driver.getCurrentUrl().contains(pageUrl);
    }
    public void setText(By element,String value) throws InterruptedException{
        waitForPageLoaded();
        WebElement eleWaited=wait.until(ExpectedConditions.elementToBeClickable(element));
        eleWaited.click();
        eleWaited.clear();
        eleWaited.sendKeys(value);
    }
    public void clickElement(By element) throws InterruptedException{
        waitForPageLoaded();
        WebElement eleWaited=wait.until(ExpectedConditions.elementToBeClickable(element));
        eleWaited.click();
    }
    public void clickElementWithJS(By element)
    {
        waitForPageLoaded();
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(element));
        js.executeScript("arguments[0].click();",driver.findElement(element));
    }
    public void rightClickElement(By element)
    {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.contextClick().build().perform();
    }
    // Dropdown
    public void selectOptionByText(By by,String Text){
        select=new Select(driver.findElement(by));
        select.selectByVisibleText(Text);
    }
    public void selectOptionByValue(By by,String value){
        select=new Select(driver.findElement(by));
        select.selectByValue(value);
    }
    public void selectOptionByIndex(By by,int index){
        select=new Select(driver.findElement(by));
        select.selectByIndex(index);
    }
    public void verifyTotalOptions(By by,int total)
    {
        select=new Select(driver.findElement(by));
        Assert.assertEquals(total,select.getOptions().size(),"Not equal options in total");
    }
    public boolean verifySelectedByText(By by, String text)
    {
        select=new Select(driver.findElement(by));
        System.out.println("Selected value is: "+select.getFirstSelectedOption().getText());
        return select.getFirstSelectedOption().getText().equals(text);
    }
    //Frame
    public void switchToFrameByIndex(int index)
    {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }
    public  void switchToFrameById(String idOrName)
    {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
    }
    public void switchToMainWindow(){
        driver.switchTo().defaultContent();
    }
    // Alert
    public void alertAccept(){driver.switchTo().alert().accept();}
    public void alertDismiss(){driver.switchTo().alert().dismiss();}
    public  void alertGetText(){driver.switchTo().alert().getText();}
    public void alertSetText(String text){driver.switchTo().alert().sendKeys(text);}
    public void switchToFrameByElement(By by)
    {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
    }
    public boolean verifyAlertPresent() {
        if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
            System.out.println("Alert not exists");
            return false;
        } else {
            System.out.println("Alert not exists");
            return true;
        }
    }
    // scroll to
    public void scrollToElement(By by)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(by));
    }
    public void waitForJQueryLoaded() {
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaiForPageLoaded));
            wait.until(jQueryLoad);
        } catch (Throwable error) {
            Assert.fail("Time out for loading JQuery");
        }
    }
    // using JS
    public void waitForJSLoaded() {
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaiForPageLoaded));
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Time out for loading JS");
        }
    }
    public void waitForPageLoaded() {
       waitForJQueryLoaded();
       waitForJSLoaded();
    }

    // Upload file
    public void uploadFile(By uploadBtn, String filePath) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(uploadBtn));
        driver.findElement(uploadBtn).click();
        Thread.sleep(1);

        // Khởi tạo Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        Thread.sleep(1000);

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        Thread.sleep(1000);

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        actions.contextClick().build().perform();
    }
}
