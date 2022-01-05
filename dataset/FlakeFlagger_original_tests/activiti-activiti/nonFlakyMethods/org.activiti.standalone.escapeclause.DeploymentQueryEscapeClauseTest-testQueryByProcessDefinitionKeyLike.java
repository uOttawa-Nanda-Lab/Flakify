public void testQueryByProcessDefinitionKeyLike(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().processDefinitionKeyLike("%\\_%");
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
