var createIssueForm = JIRA.Templates.Issue.createIssueForm;
JIRA.Templates.Issue.createIssueForm = function(opt_data, opt_ignored){
    var issueForm = createIssueForm(opt_data, opt_ignored);
    return issueForm.replace('<div class="buttons">','<div class="buttons"><label for="qf-create-recurring" class="qf-create-recurring"><input id="qf-create-recurring" type="checkbox">Create recurring</label>');
}
