package listener;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

@Listeners
public class CustomTestNGListener implements ITestListener {

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("On finis method: " + context.getPassedTests());
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("On start method " + context.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        screenCapture();
        Reporter.log("Test failure: " + result.getName());
        if(!Objects.isNull(result.getThrowable())){
            result.getThrowable().printStackTrace();
        }
    }

    public void screenCapture() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        try {

            String failureImageFileName =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".png";
            File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("build/reportNG/" + failureImageFileName));

            String userDirector = System.getProperty("user.dir") + "/";
            Reporter.log("<a href=\""+ userDirector + "/build/reportNG/" + failureImageFileName +"\"><img src=\"file:///" + userDirector
                    + "/build/reportNG/" + failureImageFileName + "\" alt=\"\""+ "height='200' width='200'/> "+"<br />");
            Reporter.setCurrentTestResult(null);

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
