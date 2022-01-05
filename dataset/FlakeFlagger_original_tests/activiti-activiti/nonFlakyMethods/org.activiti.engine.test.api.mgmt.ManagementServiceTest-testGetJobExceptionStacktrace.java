@Deployment public void testGetJobExceptionStacktrace(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("exceptionInJobExecution");
  Job timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull("No job found for process instance",timerJob);
  try {
    managementService.moveTimerToExecutableJob(timerJob.getId());
    managementService.executeJob(timerJob.getId());
    fail("RuntimeException from within the script task expected");
  }
 catch (  RuntimeException re) {
    assertTextPresent("This is an exception thrown from scriptTask",re.getCause().getMessage());
  }
  timerJob=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(timerJob);
  assertNotNull(timerJob.getExceptionMessage());
  assertTextPresent("This is an exception thrown from scriptTask",timerJob.getExceptionMessage());
  String exceptionStack=managementService.getTimerJobExceptionStacktrace(timerJob.getId());
  assertNotNull(exceptionStack);
  assertTextPresent("This is an exception thrown from scriptTask",exceptionStack);
}
