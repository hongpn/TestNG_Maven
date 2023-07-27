package hongpn.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ValidateHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    private final int timeoutWaiForPageLoaded = 20;
    JavascriptExecutor js;

    public ValidateHelper(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    //sendKeys to a locator
    public void setText(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    //click on a locator
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        //Click JS
        //js.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    //choose text on dropdownlist
    public void selectOptionByText(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    //choose by value on dropdownlist
    public void selectOptionByValue(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    // wait until Page is Loaded
//    public void waitForPageLoaded() {
//        ExpectedCondition<Boolean> expected = new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
//            }
//        };
//        try {
//            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaiForPageLoaded));
//            wait1.until(expected);
//        } catch (Throwable error) {
//            Assert.fail("Time out waiting for page");
//        }
//    }

    // verify the link based on the expected url
    public boolean verifyUrl(String url) {
        System.out.println(driver.getCurrentUrl());
        System.out.println(url);
        return driver.getCurrentUrl().contains(url);
    }

    public boolean verifyTxtElement(By by, String text) {
        return driver.findElement(by).getText().equals(text);
    }

    // Wait for page loaded
    //using JQuery
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

    // Wait for both JQuery and JS
    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaiForPageLoaded));
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Time out for Page Loading");
        }
    }
    // check exist element
    public boolean checkExistElement(By by) {
        List<WebElement> lstEle = driver.findElements(by);
        return lstEle.size() != 0;
    }
}



