@Deployment public void testTaskListenerWithTransientVariables(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transientVarsTest");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Task after",task.getName());
  String mergedResult=(String)taskService.getVariable(task.getId(),"mergedResult");
  assertEquals("transientVar01transientVar02transientVar03",mergedResult);
}
