package hongpn.pages;

import hongpn.commons.ValidateHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class PIMPage extends Menu {
    private WebDriver driver;
    private ValidateHelper helper;
    // Element for Dashboard Page

    // Internal Element
    private final By empNameInput=By.xpath("//label[normalize-space()='Employee Name']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");
    private  final By empIDInput=By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
    private  final By spNameInput=By.xpath("//label[normalize-space()='Supervisor Name']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");
    private final By jobTitleDrop=By.xpath("//label[normalize-space()='Job Title']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//div[@class='oxd-select-text-input']");
    private final By empStatusDrop=By.xpath("//label[normalize-space()='Employment Status']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//div[@class='oxd-select-text-input']");
    private final By subUnitDrop=By.xpath("//label[normalize-space()='Sub Unit']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//div[@class='oxd-select-text-input']");
   private final By includeDrop=By.xpath("//label[normalize-space()='Include']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//div[@class='oxd-select-text-input']");
    private final By contextDrop =By.xpath("//div[@role='listbox']");
    private final By chiefExecutiveValue=By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='Chief Executive Officer']");
    private final By fullTimeValue=By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='Full-Time Contract']");
    private final By financeValue=By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='Finance']");
    private final By currentPastValue=By.xpath("//div[@role='listbox']//div[@role='option' and normalize-space()='Current and Past Employees']");

    private  final By searchBtn=By.xpath("//button[normalize-space()='Search']");
    private  final By resetBtn=By.xpath("//button[normalize-space()='Reset']");
    //url Dashboard
    private  final String urlPIM="/pim";
    //Header Page
    private final By headerPage=By.xpath("//h6[normalize-space()='Dashboard']");
    private WebElement lbElement;
    private WebElement chosenElement;
    Actions actions;
    public PIMPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        helper = new ValidateHelper(driver);
        setParameters(headerPage,urlPIM,"PIM");
    }

    public void ClickToJobTitle() throws InterruptedException {
        helper.waitForJQueryLoaded();
        helper.click(jobTitleDrop);
        Thread.sleep(2000);
        actions=new Actions(driver);
        //actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        //actions.sendKeys(Keys.ENTER).build().perform();
        if(helper.checkExistElement(contextDrop)) {
            lbElement = driver.findElement(contextDrop);
            String innerHTML= lbElement.getAttribute("innerHTML");
            System.out.println(innerHTML);
            if(helper.checkExistElement(chiefExecutiveValue))
            {
                chosenElement= driver.findElement(chiefExecutiveValue);
                actions.moveToElement(chosenElement).pause(Duration.ofSeconds(1)).click(chosenElement).build().perform();
            }
            else
                System.out.println("Can not find out Chief Executive");

        }
        else
            System.out.println("Can not get list item in JobTitle");
    }
    public void ClickToElementStatus() throws InterruptedException {
        helper.waitForJQueryLoaded();
        helper.click(empStatusDrop);
        Thread.sleep(2000);
        actions=new Actions(driver);
        //actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        //actions.sendKeys(Keys.ENTER).build().perform();
        if(helper.checkExistElement(contextDrop)) {
            lbElement = driver.findElement(contextDrop);
            String innerHTML= lbElement.getAttribute("innerHTML");
            System.out.println(innerHTML);
            if(helper.checkExistElement(fullTimeValue))
            {
                chosenElement= driver.findElement(fullTimeValue);
                actions.moveToElement(chosenElement).pause(Duration.ofSeconds(1)).click(chosenElement).build().perform();
            }
            else
                System.out.println("Can not find out Full time contract");

        }
        else
            System.out.println("Can not get list item in Employment status");
    }
    public void ClickToSubUnit() throws InterruptedException {
        helper.waitForJQueryLoaded();
        helper.click(subUnitDrop);
        Thread.sleep(2000);
        actions=new Actions(driver);
        //actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        //actions.sendKeys(Keys.ENTER).build().perform();
        if(helper.checkExistElement(contextDrop)) {
            lbElement = driver.findElement(contextDrop);
            String innerHTML= lbElement.getAttribute("innerHTML");
            System.out.println(innerHTML);
            if(helper.checkExistElement(financeValue))
            {
                chosenElement= driver.findElement(financeValue);
                actions.moveToElement(chosenElement).pause(Duration.ofSeconds(1)).click(chosenElement).build().perform();
            }
            else
                System.out.println("Can not find out Finance Value");

        }
        else
            System.out.println("Can not get list item in Sub Unit");
    }
    public void ClickToInclude() throws InterruptedException {
        helper.waitForJQueryLoaded();
        helper.click(includeDrop);
        Thread.sleep(2000);
        actions=new Actions(driver);
        //actions.sendKeys(Keys.ARROW_DOWN).build().perform();
        //actions.sendKeys(Keys.ENTER).build().perform();
        if(helper.checkExistElement(contextDrop)) {
            lbElement = driver.findElement(contextDrop);
            String innerHTML= lbElement.getAttribute("innerHTML");
            System.out.println(innerHTML);
            if(helper.checkExistElement(currentPastValue))
            {
                chosenElement= driver.findElement(currentPastValue);
                actions.moveToElement(chosenElement).pause(Duration.ofSeconds(1)).click(chosenElement).build().perform();
            }
            else
                System.out.println("Can not find out current and past Value");

        }
        else
            System.out.println("Can not get list item in Include");
    }
    public  void ClickSearch()
    {
        helper.click(searchBtn);
    }
}
