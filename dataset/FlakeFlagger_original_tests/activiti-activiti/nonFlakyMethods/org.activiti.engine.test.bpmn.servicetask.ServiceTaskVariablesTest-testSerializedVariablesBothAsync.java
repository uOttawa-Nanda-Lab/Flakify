@Deployment public void testSerializedVariablesBothAsync(){
  runtimeService.startProcessInstanceByKey("process");
  Job job=managementService.createJobQuery().singleResult();
  assertNotNull(job);
  managementService.executeJob(job.getId());
  job=managementService.createJobQuery().singleResult();
  assertNotNull(job);
  managementService.executeJob(job.getId());
  assertTrue(isOkInDelegate2);
  assertTrue(isOkInDelegate3);
}
