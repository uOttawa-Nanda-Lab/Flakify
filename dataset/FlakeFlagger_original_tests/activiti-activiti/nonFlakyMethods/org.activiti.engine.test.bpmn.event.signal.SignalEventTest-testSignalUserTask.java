@Deployment public void testSignalUserTask(){
  runtimeService.startProcessInstanceByKey("catchSignal");
  Execution execution=runtimeService.createExecutionQuery().onlyChildExecutions().activityId("waitState").singleResult();
  assertNotNull(execution);
  try {
    runtimeService.trigger(execution.getId());
    fail("ActivitiException expected");
  }
 catch (  ActivitiException ae) {
  }
}
