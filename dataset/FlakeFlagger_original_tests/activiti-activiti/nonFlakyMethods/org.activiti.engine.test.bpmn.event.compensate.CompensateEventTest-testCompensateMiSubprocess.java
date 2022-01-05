@Deployment public void testCompensateMiSubprocess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("compensateProcess");
  assertEquals(5,runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));
  Execution execution=runtimeService.createExecutionQuery().activityId("beforeEnd").singleResult();
  runtimeService.trigger(execution.getId());
  assertProcessEnded(processInstance.getId());
}
