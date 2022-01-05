@Deployment public void testRepeatingTimerWithCancelActivity(){
  runtimeService.startProcessInstanceByKey("repeatingTimerAndCallActivity");
  assertEquals(1,managementService.createTimerJobQuery().count());
  assertEquals(1,taskService.createTaskQuery().count());
  Job job=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  assertEquals(1,managementService.createTimerJobQuery().count());
  assertEquals(1,taskService.createTaskQuery().count());
}
