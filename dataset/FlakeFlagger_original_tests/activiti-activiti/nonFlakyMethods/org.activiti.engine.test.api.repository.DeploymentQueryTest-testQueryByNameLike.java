public void testQueryByNameLike(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentNameLike("%activiti%");
  assertEquals(2,query.list().size());
  assertEquals(2,query.count());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
