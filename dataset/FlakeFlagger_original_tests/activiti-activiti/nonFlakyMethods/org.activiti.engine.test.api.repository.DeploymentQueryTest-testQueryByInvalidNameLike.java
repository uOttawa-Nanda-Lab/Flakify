public void testQueryByInvalidNameLike(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentNameLike("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    repositoryService.createDeploymentQuery().deploymentNameLike(null);
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
