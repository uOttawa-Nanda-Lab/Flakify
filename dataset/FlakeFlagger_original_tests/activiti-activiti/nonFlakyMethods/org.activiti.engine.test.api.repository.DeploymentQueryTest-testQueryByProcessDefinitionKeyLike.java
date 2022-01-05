public void testQueryByProcessDefinitionKeyLike(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().processDefinitionKeyLike("%o%");
  assertEquals(2,query.list().size());
  assertEquals(2,query.count());
}
