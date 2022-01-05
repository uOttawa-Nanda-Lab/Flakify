@Deployment public void testSetTransientVariableInServiceTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transientVarsTest");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  String message=(String)taskService.getVariable(task.getId(),"message");
  assertEquals("Hello World!",message);
  assertNull(runtimeService.getVariable(processInstance.getId(),"response"));
}
