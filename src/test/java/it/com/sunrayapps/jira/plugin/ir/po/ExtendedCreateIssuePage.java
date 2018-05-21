package it.com.sunrayapps.jira.plugin.ir.po;

import com.atlassian.jira.pageobjects.pages.AbstractJiraPage;
import com.atlassian.pageobjects.elements.ElementBy;
import com.atlassian.pageobjects.elements.PageElement;
import com.atlassian.pageobjects.elements.query.TimedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.atlassian.collectors.CollectorsUtil.toImmutableList;

public class ExtendedCreateIssuePage extends AbstractJiraPage {
    @ElementBy(
        className = "qf-create-recurring"
    )
    private PageElement createRecurringCheckbox;

    @ElementBy(
        id = "create-issue-submit"
    )
    private PageElement createIssueSubmit;

    @ElementBy(
        id = "summary"
    )
    private PageElement summary;

    @ElementBy(
        id = "create-issue-submit"
    )
    private PageElement submit;

    public void fillSummary(String summaryValue) {
        summary.type(summaryValue);
    }

    public void submit() {
        submit.click();
        driver.waitUntil(webDriver -> webDriver.findElements(By.id("create-issue-dialog")).size() == 0 || getErrorMessages().size() > 0);
    }

    public List<String> getErrorMessages() {
        return driver.findElements(By.cssSelector("#create-issue-dialog .error"))
            .stream()
            .map(WebElement::getText)
            .collect(toImmutableList());
    }

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