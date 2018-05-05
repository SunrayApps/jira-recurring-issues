if (typeof SunrayApps == 'undefined') { var SunrayApps = {}; }
if (typeof SunrayApps.recurringIssues == 'undefined') { SunrayApps.recurringIssues = {}; }

SunrayApps.recurringIssues.createIssueForm = JIRA.Templates.Issue.createIssueForm,
SunrayApps.recurringIssues.toggleRecurringIssuesFields = function(checkbox) {
     if(AJS.$("#recurring-issues-period").size() == 0){
        var periodFieldTemplate = SunrayApps.Templates.RecurringIssues.periodField()
        AJS.$(".issue-setup-fields").append(periodFieldTemplate);
     }
     AJS.$("#is-recurring-issue").val(checkbox.checked);
     var periodField = AJS.$("#recurring-issues-period")
     if(checkbox.checked){
        periodField.parent().show();
        periodField.focus();
     }else{
        periodField.parent().hide();
     }
}

JIRA.Templates.Issue.createIssueForm = function(opt_data, opt_ignored){
    var issueForm = SunrayApps.recurringIssues.createIssueForm(opt_data, opt_ignored);
    return issueForm.replace(
        '<div class="buttons">',
        SunrayApps.Templates.RecurringIssues.checkbox()
    );
}
