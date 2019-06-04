package cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features", glue = {"cucumber.steps"})
public class TestNGRunner extends AbstractTestNGCucumberTests {
}
