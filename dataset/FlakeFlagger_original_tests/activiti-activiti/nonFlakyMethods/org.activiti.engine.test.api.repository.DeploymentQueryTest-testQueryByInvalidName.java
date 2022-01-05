public void testQueryByInvalidName(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentName("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    repositoryService.createDeploymentQuery().deploymentName(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
