public void testJobTenancyAfterTenantChange(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testJobTenancy.bpmn20.xml").tenantId(TEST_TENANT_ID).deploy().getId();
  String newTenant="newTenant";
  repositoryService.changeDeploymentTenantId(deploymentId,newTenant);
  Job job=managementService.createTimerJobQuery().singleResult();
  assertEquals(newTenant,job.getTenantId());
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  job=managementService.createTimerJobQuery().singleResult();
  assertEquals(newTenant,job.getTenantId());
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  job=managementService.createJobQuery().singleResult();
  assertEquals(newTenant,job.getTenantId());
  managementService.executeJob(job.getId());
  repositoryService.deleteDeployment(deploymentId,true);
}
