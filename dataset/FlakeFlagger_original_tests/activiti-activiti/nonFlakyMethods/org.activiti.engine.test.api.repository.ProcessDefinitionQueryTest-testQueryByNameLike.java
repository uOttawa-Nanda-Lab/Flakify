public void testQueryByNameLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%w%");
  verifyQueryResults(query,1);
}
