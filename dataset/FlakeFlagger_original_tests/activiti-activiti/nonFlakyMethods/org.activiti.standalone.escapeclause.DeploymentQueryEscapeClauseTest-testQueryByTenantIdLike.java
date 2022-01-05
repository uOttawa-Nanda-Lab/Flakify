public void testQueryByTenantIdLike(){
  DeploymentQuery query=repositoryService.createDeploymentQuery().deploymentTenantIdLike("%\\%%");
  assertEquals("One%",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=repositoryService.createDeploymentQuery().deploymentTenantIdLike("%\\_%");
  assertEquals("Two_",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
