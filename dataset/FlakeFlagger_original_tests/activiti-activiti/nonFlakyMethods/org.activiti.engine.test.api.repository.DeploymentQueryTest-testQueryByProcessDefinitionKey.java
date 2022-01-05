public void testQueryByProcessDefinitionKey(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().processDefinitionKey("one");
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  assertNotNull(query.singleResult());
}
