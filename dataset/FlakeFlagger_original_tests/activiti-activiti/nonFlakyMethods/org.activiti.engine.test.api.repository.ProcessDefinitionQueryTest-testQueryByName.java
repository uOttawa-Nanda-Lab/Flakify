public void testQueryByName(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionName("Two");
  verifyQueryResults(query,1);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionName("One");
  verifyQueryResults(query,2);
}
