public void testQueryByKeyAndVersion(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("one").processDefinitionVersion(1);
  verifyQueryResults(query,1);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("one").processDefinitionVersion(2);
  verifyQueryResults(query,1);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("one").processDefinitionVersion(3);
  verifyQueryResults(query,0);
}
