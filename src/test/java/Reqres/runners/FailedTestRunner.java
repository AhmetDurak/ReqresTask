package Reqres.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "Reqres/stepDefinitions",
        features = "@target/rerun.txt",
        dryRun = false,
        tags = ""

)
public class FailedTestRunner {

}
