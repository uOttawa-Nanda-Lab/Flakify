@Deployment public void testFailingAsyncServiceTimer(){
  runtimeService.startProcessInstanceByKey("asyncService");
  assertEquals(1,managementService.createJobQuery().count());
  Job job=managementService.createJobQuery().singleResult();
  try {
    managementService.executeJob(job.getId());
    fail();
  }
 catch (  Exception e) {
  }
  Execution execution=null;
  for (  Execution e : runtimeService.createExecutionQuery().list()) {
    if (e.getParentId() != null) {
      execution=e;
    }
  }
  assertNotNull(execution);
  assertEquals("service",runtimeService.getActiveActivityIds(execution.getId()).get(0));
  assertEquals(1,managementService.createTimerJobQuery().count());
  runtimeService.deleteProcessInstance(execution.getId(),"dead");
}
