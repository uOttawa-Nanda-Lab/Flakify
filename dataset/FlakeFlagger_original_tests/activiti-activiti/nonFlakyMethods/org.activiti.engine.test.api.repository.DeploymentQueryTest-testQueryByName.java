public void testQueryByName(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentName("org/activiti/engine/test/repository/two.bpmn20.xml");
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
