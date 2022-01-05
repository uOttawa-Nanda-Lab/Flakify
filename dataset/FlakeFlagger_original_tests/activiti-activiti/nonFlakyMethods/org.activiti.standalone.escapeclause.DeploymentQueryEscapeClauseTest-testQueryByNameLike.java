public void testQueryByNameLike(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentNameLike("%\\%%");
  assertEquals("one%",query.singleResult().getName());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createDeploymentQuery().deploymentNameLike("%\\_%");
  assertEquals("two_",query.singleResult().getName());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
