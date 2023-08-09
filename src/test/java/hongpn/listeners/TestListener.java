package hongpn.listeners;

import hongpn.common.helpers.CaptureHelpers;
import hongpn.common.utilities.Log.Log;
import hongpn.commons.BaseSetup;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
public class TestListener implements ITestListener {
    @Override
    public void onFinish(ITestContext result) {
        Log.info("Finish auto test");
    }
    @Override
    public void onStart(ITestContext result) {
        Log.info("Starting autotest");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("This is test case having positive fail percentage "+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
       Log.error("Fail test case: " + result.getName());
        try {
            CaptureHelpers.CaptureScreenshot(BaseSetup.getDriver(), result.getName());
        } catch (Exception ex) {
            Log.error("Exception while taking photo " + ex.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
       Log.info("Skipped test case: " + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //System.out.println("Đây là test case chạy thành công: " + result.getName());
        Log.info("Successful test case: " + result.getName());
    }
}
