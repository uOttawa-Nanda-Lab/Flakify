public void testQueryByNameAndCategory(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentCategory("testCategory").deploymentNameLike("%activiti%");
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  assertNotNull(query.singleResult());
}
