public void testQueryByInvalidNameLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionNameLike("%invalid%");
  verifyQueryResults(query,0);
}
