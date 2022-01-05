public void testJobTenancy(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testJobTenancy.bpmn20.xml").tenantId(TEST_TENANT_ID).deploy().getId();
  Job job=managementService.createTimerJobQuery().singleResult();
  assertEquals(TEST_TENANT_ID,job.getTenantId());
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  job=managementService.createTimerJobQuery().singleResult();
  assertEquals(TEST_TENANT_ID,job.getTenantId());
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  job=managementService.createJobQuery().singleResult();
  assertEquals(TEST_TENANT_ID,job.getTenantId());
  managementService.executeJob(job.getId());
  String deploymentId2=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/tenant/TenancyTest.testJobTenancy.bpmn20.xml").deploy().getId();
  job=managementService.createTimerJobQuery().singleResult();
  assertEquals("",job.getTenantId());
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  job=managementService.createTimerJobQuery().singleResult();
  assertEquals("",job.getTenantId());
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  job=managementService.createJobQuery().singleResult();
  assertEquals("",job.getTenantId());
  repositoryService.deleteDeployment(deploymentId,true);
  repositoryService.deleteDeployment(deploymentId2,true);
}
