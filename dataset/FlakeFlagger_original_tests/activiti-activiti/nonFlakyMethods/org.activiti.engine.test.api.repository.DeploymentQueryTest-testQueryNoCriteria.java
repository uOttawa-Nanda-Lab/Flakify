public void testQueryNoCriteria(){
  DeploymentQuery query=repositoryService.createDeploymentQuery();
  assertEquals(2,query.list().size());
  assertEquals(2,query.count());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
