package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//video 198
// we use it to run cucumber feature file
// the features file that you want to run, you must provide their path here
// it also needs to know where are stepDefenitions for mapping
// the output of cucumber is coming in encoded format and results are not readable,
// there is attribute monoChrome which will give results in readable format
// in what format you want your report out put we use plug in and we say in html format and you 
//have to say where you want that html report is stored

//cucumber->  TestNG, junit


@CucumberOptions(features="src/test/java/features",glue={"stepDefinitions"},
monochrome=true, tags = "@Regression", plugin= {"html:target/cucumber.html"})


//@CucumberOptions(features="src/test/java/features",glue={"stepDefinitions"},
//monochrome=true, tags = "@ErrorValidation", plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
}