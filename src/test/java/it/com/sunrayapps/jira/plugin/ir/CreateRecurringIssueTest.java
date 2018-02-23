package it.com.sunrayapps.jira.plugin.ir;

import com.atlassian.jira.pageobjects.JiraTestedProduct;
import it.com.sunrayapps.jira.plugin.ir.po.ExtendedCreateIssuePage;
import it.com.sunrayapps.jira.plugin.ir.po.TopMenuPage;
import it.com.sunrayapps.jira.plugin.ir.rule.JiraIntegrationTestRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class CreateRecurringIssueTest {
    @Rule
    public JiraIntegrationTestRule jiraIntegrationTestRule = new JiraIntegrationTestRule();
    private final JiraTestedProduct jira = jiraIntegrationTestRule.getJira();

    @Test
    public void shouldInjectCheckbox() {
        jira.quickLoginAsAdmin();
        final TopMenuPage topMenuPage = jira.goTo(TopMenuPage.class);
        final ExtendedCreateIssuePage createIssuePage = topMenuPage.createIssue();

        Assert.assertTrue(createIssuePage.isCrateRecurringCheckboxVisible());
    }
}
