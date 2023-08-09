package hongpn.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import hongpn.common.helpers.PropertiesFile;

import java.text.SimpleDateFormat;
import java.util.Date;

import static hongpn.common.helpers.PropertiesFile.projectPath;


public class ExtentManager {


    private static final ExtentReports extentReports = new ExtentReports();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
    public synchronized static ExtentReports getExtentReports() {
        PropertiesFile.setPropertiesFile();

        ExtentSparkReporter reporter = new ExtentSparkReporter( projectPath +PropertiesFile.getPropValue("extentReport") + "/ExtentReport_" + dateFormat.format(new Date()) + ".html");
        reporter.config().setReportName("Demo Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java Framework | HongPham");
        extentReports.setSystemInfo("Author", "Hong Pham");
        return extentReports;
    }
}