package it.com.sunrayapps.jira.plugin.ir.po;

import com.atlassian.jira.pageobjects.pages.AbstractJiraPage;
import com.atlassian.pageobjects.elements.ElementBy;
import com.atlassian.pageobjects.elements.PageElement;
import com.atlassian.pageobjects.elements.query.TimedCondition;

public class ExtendedCreateIssuePage extends AbstractJiraPage {
    @ElementBy(
        className = "qf-create-recurring"
    )
    private PageElement createRecurringCheckbox;

    @ElementBy(
        id = "create-issue-submit"
    )
    private PageElement createIssueSubmit;


    public boolean isCrateRecurringCheckboxVisible() {
        return createRecurringCheckbox.isVisible();
    }

    @Override
    public TimedCondition isAt() {
        return createIssueSubmit.timed().isVisible();
    }

    @Override
    public String getUrl() {
        throw new RuntimeException("Not Implemented");
    }
}