package Reqres.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/htmlReport",
                "rerun:target/rerun.txt"
                //"me.jvt.cucumber.report.PrettyReports:target/cucumber"
        },
        features = "src/test/resources/features",
        glue = "Reqres/stepDefinitions",
        dryRun = false,
        strict = true,
        tags = "@wip"
)

public class CukesRunner {
}
