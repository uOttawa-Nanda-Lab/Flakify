public void testQueryByDeploymentId(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentId(deploymentOneId);
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
