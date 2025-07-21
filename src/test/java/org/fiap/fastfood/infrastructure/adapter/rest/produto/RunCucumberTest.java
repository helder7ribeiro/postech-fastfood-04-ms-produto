package org.fiap.fastfood.infrastructure.adapter.rest.produto;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/produto.feature",
    glue = "org.fiap.fastfood.infrastructure.adapter.rest.produto",
    plugin = {"pretty", "summary", "html:target/cucumber-report.html"},
    monochrome = true
)
public class RunCucumberTest {
} 