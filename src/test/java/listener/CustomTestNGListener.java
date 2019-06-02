package listener;

import driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

@Listeners
public class CustomTestNGListener implements ITestListener {

    private final static Logger logger = LogManager.getLogger(CustomTestNGListener.class);

    static {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info(Thread.currentThread().getName() + ": On finis method: " + context.getPassedTests());
        clean();

    }

    @Override
    public void onStart(ITestContext context) {
        logger.info(Thread.currentThread().getName() + ": On start method " + context.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
       logger.info(Thread.currentThread().getName() + ": Test skipped: " + result.getName());
        appendLogToTestNG();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        screenCapture();
        logger.info(Thread.currentThread().getName() + ": Test failure: " + result.getName());
        if(!Objects.isNull(result.getThrowable())){
            result.getThrowable().printStackTrace();
        }
        appendLogToTestNG();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(Thread.currentThread().getName() + ": Test success: " + result.getName());
        appendLogToTestNG();
    }

    private void clean(){
        try {
            PrintWriter writer = new PrintWriter(new File("logs/Reporter.log"));
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void screenCapture() {
        try {
            String failureImageFileName =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".png";
            File scrFile = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("build/reportNG/html/" + failureImageFileName));
            String userDirector = System.getProperty("user.dir") + "/";
            Reporter.log("<a href=\""+ userDirector + "/build/reportNG/html/" + failureImageFileName +"\"><img src=\"file:///" + userDirector
                    + "/build/reportNG/html/" + failureImageFileName + "\" alt=\"\""+ "height='200' width='200'/> "+"<br />");
            Reporter.setCurrentTestResult(null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

     private void appendLogToTestNG() {
        try {
             FileInputStream report = new FileInputStream(new File("logs/Reporter.log"));
             BufferedReader reader = new BufferedReader(new InputStreamReader(report));
             String line;
             while (Objects.nonNull(line = reader.readLine())) {
                 Reporter.log(line + "</br>");
             }
         } catch (IOException e) {
            e.printStackTrace();
         }
    }

}
