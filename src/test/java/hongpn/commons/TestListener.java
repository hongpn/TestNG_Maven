package hongpn.commons;

import hongpn.common.helpers.CaptureHelpers;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
public class TestListener implements ITestListener {
    @Override
    public void onFinish(ITestContext result) {
        System.out.println("Kết thúc auto test");
    }
    @Override
    public void onStart(ITestContext result) {
        System.out.println("Bắt đầu chạy autotest");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Đây là test case bị fail: " + result.getName());
        try {
            CaptureHelpers.CaptureScreenshot(new BaseSetup().getDriver(), result.getName());
        } catch (Exception ex) {
            System.out.println("Exception while taking photo " + ex.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Đây là test case bị bỏ qua: " + result.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Đây là test case chạy thành công: " + result.getName());

    }
}
