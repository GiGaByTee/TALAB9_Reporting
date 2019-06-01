package listener;

import driver.DriverLoader;
import org.openqa.selenium.OutputType;
import org.testng.Reporter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class ReportLogger {
    private static final int DEFAULT_VERBOSITY_LEVEL = 2;

    public static final int DEBUG_VERBOSITY_LEVEL = 4;

    private static boolean logOutputEscaped = Boolean.parseBoolean(System
            .getProperty("org.uncommons.reportng.escape-output"));

    /* public static void log(String message)
     {
         log(message, DEFAULT_VERBOSITY_LEVEL);
     }*/
    /*public static void logDebug(String message)
    {
        log("DEBUG: " + message, DEBUG_VERBOSITY_LEVEL);
    }

    public static void logFailure(String message) {
        log("---- Test Failed. Please check the console or TestNg report for details");
        log("<mark><strong>FAILURE:</strong> <em>" + message + "</em></mark>");
    }*/
/*
    public static void log(String message, int verbosity)
    {
        if (!logOutputEscaped)
        {
            message = "<br>"+ message + "<br/>";
        }

        Reporter.log(message);
    }*/
    public void log(String text) {
        String newLine = System.getProperty("line.separator");
            final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
            System.setProperty(ESCAPE_PROPERTY, "false");
            Reporter.log("<b>text</b>");
    }

  /*  public void downloadReportDisplay(Boolean addReport) throws IOException {
        // set file format and destination for report
        DateFormat dateFormat = new SimpleDateFormat(
                "dd_MMM_yyyy__hh_mm_ssaa");
        String destDir = "./test-output/html/screenshots/";
        new File(destDir).mkdirs();
        String destFile = dateFormat.format(new Date());
        // Display screenshot to ReportNG
        String userDirector = "./screenshots/";
        String destFileNew = destFile + ".pdf";
        log("perfectoReport: " + userDirector + destFileNew);
        if (addReport) {
            log("<a href=\"" + userDirector + destFileNew
                    + "\">Perfecto Report</a><br />");
        }
    }*/
}