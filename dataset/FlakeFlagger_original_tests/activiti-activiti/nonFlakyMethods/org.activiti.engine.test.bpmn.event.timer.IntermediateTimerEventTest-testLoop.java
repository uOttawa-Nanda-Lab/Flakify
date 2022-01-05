@Deployment public void testLoop(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testLoop");
  for (int i=0; i < 3; i++) {
    Job timer=managementService.createTimerJobQuery().singleResult();
    managementService.moveTimerToExecutableJob(timer.getId());
    managementService.executeJob(timer.getId());
  }
  assertProcessEnded(processInstance.getId());
}
