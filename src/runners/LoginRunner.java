package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="projectFeatures",
		glue="stepDefinitions",
		tags= {"@Login"}
		)
public class LoginRunner {

}
