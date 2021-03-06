package com.collinsongroup.travel.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features/citybike.feature"},
				 plugin = {"pretty", "json:target/jsonReports/cucumber-report.json"},
		glue = {"com.collinsongroup.travel.stepdefinition"},
		monochrome=true
				
)
public class RunnerTest {

}
