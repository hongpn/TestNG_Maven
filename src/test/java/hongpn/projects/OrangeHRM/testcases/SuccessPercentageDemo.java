package hongpn.projects.OrangeHRM.testcases;

import hongpn.listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestListener.class)
public class SuccessPercentageDemo {
    int count = 0;

    @Test(invocationCount = 5, successPercentage = 50)
    public void kiemTraChanLe() {
        count++;
        System.out.println("Số lần chạy: " + count);

        if (count % 2 == 0) {
            Assert.assertTrue(false);
        } else {
            Assert.assertTrue(true);
        }
    }
}
