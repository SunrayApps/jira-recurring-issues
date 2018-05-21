package com.sunrayapps.jira.plugin.ri.filter;

import com.atlassian.core.filters.AbstractHttpFilter;
import com.atlassian.jira.action.ActionContextKit;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.rest.api.util.ErrorCollection;
import com.atlassian.jira.user.ApplicationUser;
import org.apache.commons.lang.NotImplementedException;
import webwork.action.Action;
import webwork.action.factory.ActionFactory;
import webwork.action.factory.ChainingActionFactoryProxy;
import webwork.action.factory.PrepareActionFactoryProxy;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.joor.Reflect.on;

public class RecurringIssuesDispatcherFilter extends AbstractHttpFilter {
    @Override
    protected void doFilter(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        if (isRecurringIssue(request)) {
            final ActionFactory actionFactory = ActionFactory.getActionFactory();
            try {
                final ChainingActionFactoryProxy chainingActionFactoryProxy = on(actionFactory).get("factory");
                final Object safeParameterSettingActionFactory = on(chainingActionFactoryProxy).get("nextFactory");
                final PrepareActionFactoryProxy prepareActionFactory = on(safeParameterSettingActionFactory).get("nextFactory");
                final String quickCreateIssueActionName = "QuickCreateIssue";
                final Action quickCreateIssue = prepareActionFactory.getActionImpl(quickCreateIssueActionName);
                ActionContextKit.setContext(request, response, quickCreateIssueActionName);
                final ErrorCollection errors = on(quickCreateIssue).call("getValidationErrors").get();
                if (errors.hasAnyErrors()) {
                    on(quickCreateIssue)
                        .field("jsonActionSupport")
                        .call(
                            "asJson",
                            getErrorReturnCode(),
                            errors
                        );
                } else {
                    throw new NotImplementedException();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                ActionContextKit.resetContext();
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private int getErrorReturnCode() {
        final ApplicationUser loggedInUser = ComponentAccessor.getJiraAuthenticationContext().getLoggedInUser();
        return loggedInUser == null ? 401 : 400;
    }


    private boolean isRecurringIssue(HttpServletRequest request) {
        boolean isMethodPost = request.getMethod().equals("POST");
        String isRecurring = request.getParameter("isRecurringIssue");
        return isMethodPost && isRecurring != null && isRecurring.equalsIgnoreCase("true");
    }
}