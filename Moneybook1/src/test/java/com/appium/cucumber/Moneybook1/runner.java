package com.appium.cucumber.Moneybook1;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty"}, features ={"src/test/resource"},format = {"json:target/checkoutJenkins/cucumber.json", "html:target/html/cucumber.html"})
public class runner {

}



