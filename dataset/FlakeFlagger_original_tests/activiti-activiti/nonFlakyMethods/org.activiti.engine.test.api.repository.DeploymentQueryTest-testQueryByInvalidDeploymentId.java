public void testQueryByInvalidDeploymentId(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentId("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    repositoryService.createDeploymentQuery().deploymentId(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
