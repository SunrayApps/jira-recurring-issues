package it.com.sunrayapps.jira.plugin.ir.rule;

import com.atlassian.jira.pageobjects.JiraTestedProduct;
import com.atlassian.pageobjects.DefaultProductInstance;
import com.atlassian.pageobjects.TestedProductFactory;
import it.com.sunrayapps.jira.plugin.ir.chrome.ChromeTestedFactory;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class JiraIntegrationTestRule implements TestRule {
    private final JiraTestedProduct jira = TestedProductFactory
        .create(
            JiraTestedProduct.class,
            new DefaultProductInstance("http://localhost:2990/jira",
                "jira",
                2990,
                "/jira"
            ),
            new ChromeTestedFactory()
        );

    @Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                } finally {
                    jira.getTester().getDriver().quit();
                }
            }
        };
    }

    public JiraTestedProduct getJira() {
        return jira;
    }
}
