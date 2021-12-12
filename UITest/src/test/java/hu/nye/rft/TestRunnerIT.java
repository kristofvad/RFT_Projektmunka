package hu.nye.rft;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "hu.nye.ta.uni",
        plugin = {"pretty", "hu.nye.reportportal.cucumber.StepReporter"}
)
public class TestRunnerIT {

}
