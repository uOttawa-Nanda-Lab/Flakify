@Deployment public void testSignalWithProcessVariables(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testSignalWithProcessVariables");
  Map<String,Object> processVariables=new HashMap<String,Object>();
  processVariables.put("variable","value");
  Execution execution=runtimeService.createExecutionQuery().activityId("receiveMessage").singleResult();
  runtimeService.trigger(execution.getId(),processVariables);
  Map<String,Object> variables=runtimeService.getVariables(processInstance.getId());
  assertEquals(variables,processVariables);
}
