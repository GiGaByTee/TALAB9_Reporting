package listeners;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class ThreadCountChanger  implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        System.err.println("**Alter is invoked**");
        int count = Integer.parseInt(System.getProperty("threadCount", "-1"));
        if (count <= 0) {
            return;
        }
        for (XmlSuite suite : suites) {
            suite.setThreadCount(count);
        }
    }
}

