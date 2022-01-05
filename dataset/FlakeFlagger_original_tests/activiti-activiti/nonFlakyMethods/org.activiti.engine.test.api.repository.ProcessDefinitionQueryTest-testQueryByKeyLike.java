public void testQueryByKeyLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionKeyLike("%o%");
  verifyQueryResults(query,3);
}
