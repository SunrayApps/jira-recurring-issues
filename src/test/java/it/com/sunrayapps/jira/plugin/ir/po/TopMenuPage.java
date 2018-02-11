package it.com.sunrayapps.jira.plugin.ir.po;

import com.atlassian.jira.pageobjects.pages.AbstractJiraPage;
import com.atlassian.pageobjects.elements.ElementBy;
import com.atlassian.pageobjects.elements.PageElement;
import com.atlassian.pageobjects.elements.query.TimedCondition;

public class TopMenuPage extends AbstractJiraPage {
    @ElementBy(
        id = "create_link"
    )
    private PageElement createIssueLink;

    public ExtendedCreateIssuePage createIssue() {
        createIssueLink.click();
        return this.pageBinder.bind(ExtendedCreateIssuePage.class);
    }

    @Override
    public TimedCondition isAt() {
        return createIssueLink.timed().isVisible();
    }

    @Override
    public String getUrl() {
       return "/secure/Dashboard.jspa";
    }
}