public void testCompatibleConditionFlow(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("var1","true");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(PROCESS_DEFINITION_KEY,variables);
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("usertask2").singleResult();
  assertNotNull(execution);
  assertEquals("usertask2",execution.getActivityId());
}
