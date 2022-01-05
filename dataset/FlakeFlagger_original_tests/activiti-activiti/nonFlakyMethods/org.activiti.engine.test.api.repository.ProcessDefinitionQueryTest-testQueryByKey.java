public void testQueryByKey(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("one");
  verifyQueryResults(query,2);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionKey("two");
  verifyQueryResults(query,1);
}
