public void testQueryByCategory(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionCategory("Examples");
  verifyQueryResults(query,2);
}
