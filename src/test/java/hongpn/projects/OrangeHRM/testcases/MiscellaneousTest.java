package hongpn.projects.OrangeHRM.testcases;

import hongpn.common.helpers.ValidatingUIHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class MiscellaneousTest {
    public WebDriver driver;
    private ValidatingUIHelpers helpers;
    private WebDriverWait wait;
    JavascriptExecutor js;
    public MiscellaneousTest()
    {
        driver=new ChromeDriver();
        //driver.get("https://files.fm/");
        driver.get("https://datatables.net/");
        helpers=new ValidatingUIHelpers(driver);
        wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
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
    @Test
    public void checkDataTableWithPagination() throws InterruptedException {


        By title_H1 = By.xpath("//div[@class='fw-hero']//h1");


        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(title_H1));

        Thread.sleep(1000);

        By button_Next = By.xpath("//a[@id='example_next']");
        By label_Info_PageTotal = By.xpath("//div[@id='example_info']");
        String info = driver.findElement(label_Info_PageTotal).getText(); //Showing 1 to 10 of 57 entries

        System.out.println("Chuỗi chứa số item: " + info);

        //Mình tách cái chuỗi trên với ký tự khoảng trắng rồi lấy phần tử thứ 5 tính từ 0
        //Để bắt tổng số Item
        ArrayList< String > arrayListString = new ArrayList < > ();
        for (String s: info.split(" ")) {
            arrayListString.add(s);
        }

        int itemTotal = Integer.parseInt(arrayListString.get(5));
        System.out.println("Tổng số item: " + itemTotal);
        // process by select
        Select selectItemsOnPage=new Select(driver.findElement(By.xpath("//select[@name='example_length']")));
        int itemTotalOnePage = Integer.parseInt( selectItemsOnPage.getFirstSelectedOption().getText()); //mặc định như mẫu. Tuỳ vào hệ thống mình thay đổi theo
        System.out.println("Số item trên 1 trang: " + itemTotalOnePage);

        double pageTotal = (double) itemTotal / (double) itemTotalOnePage;

        DecimalFormat df = new DecimalFormat("#"); //Làm tròn số đến phần đơn vị của phần thập phân
        //Ví dụ 5.7 thì làm tròn 6 kiểu vậy
        int pageTotalInt = Integer.parseInt(df.format(pageTotal));
        System.out.println("Tổng số trang: " + df.format(pageTotalInt));

        //FOR này chạy tới < pageTotalInt để nó không click thêm lần cuối cùng
        //VD: 6 trang thì nó chỉ click 5 lần thôi chứ hả =))
        for (int i = 1; i < pageTotalInt; i++) {
            //Gọi hàm Check data bên trên lại
            checkContainsSearchTableByColumn(2, "Software Engineer");
            Thread.sleep(1000);
            //Click Next
            driver.findElement(button_Next).click();
        }
        Thread.sleep(2000);
    }
    /*
    public void checkContainsSearchTableByAllColumns( String value) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> totalRows = driver.findElements(By.xpath("//div[@id='example_wrapper']//tbody/tr"));
        Thread.sleep(1);
        System.out.println("Số kết quả cho từ khóa (" + value + "): " + totalRows.size());

        for (int i = 1; i <= totalRows.size(); i++) {
            boolean res = false;
            WebElement title = driver.findElement(By.xpath("//div[@id='example_wrapper']//tbody/tr[" + i + "]/td[" + column + "]"));
            // js.executeScript("arguments[0].scrollIntoView(true);", title);
            res = title.getText().toUpperCase().contains(value.toUpperCase());
            System.out.println("Dòng thứ " + i + ": " + res + " - " + title.getText());
            Assert.assertTrue(res, "Dòng thứ " + i + " (" + title.getText() + ")" + " không chứa giá trị " + value);
        }*/
    //Handle Data Table
    public void checkContainsSearchTableByColumn(int column, String value) throws InterruptedException {
        List<WebElement> totalRows = driver.findElements(By.xpath("//div[@id='example_wrapper']//tbody/tr"));
        Thread.sleep(1);
        System.out.println("Số kết quả cho từ khóa (" + value + "): " + totalRows.size());

        for (int i = 1; i <= totalRows.size(); i++) {
            boolean res = false;
            WebElement title = driver.findElement(By.xpath("//div[@id='example_wrapper']//tbody/tr[" + i + "]/td[" + column + "]"));
            // js.executeScript("arguments[0].scrollIntoView(true);", title);
            res = title.getText().toUpperCase().contains(value.toUpperCase());
            System.out.println("Dòng thứ " + i + ": " + res + " - " + title.getText());
            // when having data matched, it quits the loop
            Assert.assertFalse(res, "Dòng thứ " + i + " (" + title.getText() + ")" + " không chứa giá trị " + value);
        }
    }
}
