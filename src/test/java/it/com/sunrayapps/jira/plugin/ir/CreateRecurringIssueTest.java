package it.com.sunrayapps.jira.plugin.ir;

import com.atlassian.jira.pageobjects.JiraTestedProduct;
import com.atlassian.pageobjects.DefaultProductInstance;
import com.atlassian.pageobjects.TestedProductFactory;
import it.com.sunrayapps.jira.plugin.ir.po.ExtendedCreateIssuePage;
import it.com.sunrayapps.jira.plugin.ir.po.TopMenuPage;
import org.junit.Assert;
import org.junit.Test;

public class CreateRecurringIssueTest {
    private final JiraTestedProduct jira = TestedProductFactory
        .create(
            JiraTestedProduct.class,
            new DefaultProductInstance("http://localhost:2990/jira",
                "jira",
                2990,
                "/jira"
            ),
            null
        );

    @Test
    public void shouldInjectCheckbox() {
        jira.quickLoginAsAdmin();
        final TopMenuPage topMenuPage = jira.goTo(TopMenuPage.class);
        final ExtendedCreateIssuePage createIssuePage = topMenuPage.createIssue();
        Assert.assertTrue(createIssuePage.isCrateRecurringCheckboxVisible());
    }
}
