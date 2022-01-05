public void testQueryByVersion(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionVersion(2);
  verifyQueryResults(query,1);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionVersion(1);
  verifyQueryResults(query,2);
}
