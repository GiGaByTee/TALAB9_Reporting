package listener;

import driver.DriverLoader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

@Listeners
public class CustomListener implements ITestListener {

    private static Logger log = LogManager.getLogger(CustomListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("Test with name: " + context.getName() + ", STARTED in time: " + context.getStartDate().toString());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Test completed on: " + context.getEndDate().toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(format("result : SUCCESS : %s", result.getMethod().getMethodName()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("result : FAILURE " + result.getMethod().getMethodName().toUpperCase());
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        if (!result.isSuccess()) {
            try {
                String failureImageFileName = new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
                String failureImageFileName1;
                File scrFile = ((TakesScreenshot) DriverLoader.getDriver()).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(failureImageFileName));
                String userDirector = System.getProperty("user.dir") + "/";
                String s1 = "<table><tr><td><font style=\"text-decoration: underline;\" " +
                        "size=\"3\" face=\"Comic sans MS\" color=\"green\"><b>Screenshot</b></font></td></tr> ";
                Reporter.log(s1);
                Reporter.log("<tr><td><a href=\"" + userDirector + failureImageFileName + "\"><img src=\"file:///" + userDirector + failureImageFileName + "\" alt=\"\"" + "height=’120′ width=’120′/></td></tr> ");
                appendLogToReport();
                Reporter.setCurrentTestResult(null);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    private void appendLogToReport() {
        try {
            System.setProperty("org.uncommons.reportng.escape-output", "false");
            log.info("Start read logs...........");
            Scanner s = new Scanner(new File("Logs/test-yaml.log"));
            List<String> list = new ArrayList<>();
            while (s.hasNext()) {
                list.add(s.next());
            }
            for (String l : list) {
                Reporter.log(l);
            }
            s.close();
        } catch (IOException e) {
            log.error("Can't read logs to Allure Report");
        }
    }

        @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "result : SKIPPED");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

}
