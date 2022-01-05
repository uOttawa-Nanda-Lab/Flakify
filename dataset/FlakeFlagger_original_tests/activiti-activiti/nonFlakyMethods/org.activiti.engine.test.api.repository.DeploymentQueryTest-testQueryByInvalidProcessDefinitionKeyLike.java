public void testQueryByInvalidProcessDefinitionKeyLike(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().processDefinitionKeyLike("invalid");
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
