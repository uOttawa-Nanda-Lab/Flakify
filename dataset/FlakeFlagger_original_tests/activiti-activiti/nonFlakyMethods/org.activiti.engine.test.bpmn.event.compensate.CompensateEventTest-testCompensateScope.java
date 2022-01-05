@Deployment public void testCompensateScope(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  assertEquals(5,runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));
  assertEquals(5,runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));
  Execution execution=runtimeService.createExecutionQuery().activityId("beforeEnd").singleResult();
  runtimeService.trigger(execution.getId());
  assertProcessEnded(processInstance.getId());
}
