package it.com.sunrayapps.jira.plugin.ir;

import com.atlassian.jira.pageobjects.JiraTestedProduct;
import com.atlassian.jira.testkit.client.restclient.SearchClient;
import com.atlassian.jira.testkit.client.restclient.SearchRequest;
import it.com.sunrayapps.jira.plugin.ir.po.ExtendedCreateIssuePage;
import it.com.sunrayapps.jira.plugin.ir.po.TopMenuPage;
import it.com.sunrayapps.jira.plugin.ir.rule.JiraIntegrationTestRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

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

    @Test
    public void shouldCreateStandardIssue() {
        jira.quickLoginAsAdmin();
        final SearchClient search = jira
            .backdoor()
            .search();
        final Integer issuesBefore = search.getSearch(new SearchRequest()).total;

        final TopMenuPage topMenuPage = jira.goTo(TopMenuPage.class);
        final ExtendedCreateIssuePage createIssuePage = topMenuPage.createIssue();
        createIssuePage.fillSummary("A new issue");
        createIssuePage.submit();

        final Integer issuesAfter = search.getSearch(new SearchRequest()).total;

        Assert.assertTrue(issuesAfter == issuesBefore + 1);
    }

    @Test
    public void shouldCreateIssueValidationWork() {
        jira.quickLoginAsAdmin();

        final TopMenuPage topMenuPage = jira.goTo(TopMenuPage.class);
        final ExtendedCreateIssuePage createIssuePage = topMenuPage.createIssue();
        final List<String> errorMessagesBeforeSubmit = createIssuePage.getErrorMessages();
        createIssuePage.submit();

        final List<String> errorMessagesAfterSubmit = createIssuePage.getErrorMessages();


        Assert.assertEquals(0, errorMessagesBeforeSubmit.size());
        Assert.assertEquals(1, errorMessagesAfterSubmit.size());
        Assert.assertEquals("You must specify a summary of the issue.", errorMessagesAfterSubmit.get(0));
    }
}
