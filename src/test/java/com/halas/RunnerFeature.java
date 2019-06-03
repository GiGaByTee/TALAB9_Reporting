package com.halas;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/resources/gmail.feature",
glue = {"src.main.java.com.halas.MyStepdefs"
})
public class RunnerFeature {
}
