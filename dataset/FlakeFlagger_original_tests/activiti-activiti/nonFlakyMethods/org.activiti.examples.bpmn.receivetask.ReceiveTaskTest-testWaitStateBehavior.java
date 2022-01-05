@Deployment public void testWaitStateBehavior(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("receiveTask");
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).activityId("waitState").singleResult();
  assertNotNull(execution);
  runtimeService.trigger(execution.getId());
  assertProcessEnded(pi.getId());
}
