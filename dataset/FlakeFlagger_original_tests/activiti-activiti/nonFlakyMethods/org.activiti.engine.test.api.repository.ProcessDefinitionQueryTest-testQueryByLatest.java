public void testQueryByLatest(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().latestVersion();
  verifyQueryResults(query,2);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("one").latestVersion();
  verifyQueryResults(query,1);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("two").latestVersion();
  verifyQueryResults(query,1);
}
