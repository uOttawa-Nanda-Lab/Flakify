public void testDefaultFlowOnly(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY);
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("usertask1").singleResult();
  assertNotNull(execution);
  assertEquals("usertask1",execution.getActivityId());
}
