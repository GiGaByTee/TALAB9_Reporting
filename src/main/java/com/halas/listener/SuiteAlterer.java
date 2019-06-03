package com.halas.listener;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class SuiteAlterer implements IAlterSuiteListener {
    private static final String DEFAULT_THREADS = "3";

    @Override
    public void alter(List<XmlSuite> suites) {
        int count = Integer.parseInt(System.getProperty("threads", DEFAULT_THREADS));
        XmlSuite suite = suites.get(0);
        suite.setDataProviderThreadCount(count);
    }
}
