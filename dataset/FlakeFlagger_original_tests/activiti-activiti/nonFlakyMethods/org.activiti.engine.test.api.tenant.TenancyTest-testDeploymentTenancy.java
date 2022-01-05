public void testDeploymentTenancy(){
  deployTestProcessWithTestTenant();
  assertEquals(TEST_TENANT_ID,repositoryService.createDeploymentQuery().singleResult().getTenantId());
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantId(TEST_TENANT_ID).list().size());
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentId(autoCleanedUpDeploymentIds.get(0)).deploymentTenantId(TEST_TENANT_ID).list().size());
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantIdLike("my%").list().size());
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantIdLike("%TenantId").list().size());
  assertEquals(1,repositoryService.createDeploymentQuery().deploymentTenantIdLike("m%Ten%").list().size());
  assertEquals(0,repositoryService.createDeploymentQuery().deploymentTenantIdLike("noexisting%").list().size());
  assertEquals(0,repositoryService.createDeploymentQuery().deploymentWithoutTenantId().list().size());
}
