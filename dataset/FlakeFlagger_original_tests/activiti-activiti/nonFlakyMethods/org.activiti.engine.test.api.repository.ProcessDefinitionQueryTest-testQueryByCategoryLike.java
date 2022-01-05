public void testQueryByCategoryLike(){
  ProcessDefinitionQuery query=repositoryService.createProcessDefinitionQuery().processDefinitionCategoryLike("%Example%");
  verifyQueryResults(query,3);
  query=repositoryService.createProcessDefinitionQuery().processDefinitionCategoryLike("%amples2");
  verifyQueryResults(query,1);
}
