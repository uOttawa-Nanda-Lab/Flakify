@Deployment(resources={"org/activiti/examples/bpmn/tasklistener/ScriptTaskListenerTest.bpmn20.xml"}) public void testScriptTaskListener(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("scriptTaskListenerProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Name does not match","All your base are belong to us",task.getName());
  taskService.complete(task.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    HistoricTaskInstance historicTask=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult();
    assertEquals("kermit",historicTask.getOwner());
    task=taskService.createTaskQuery().singleResult();
    assertEquals("Task name not set with 'bar' variable","BAR",task.getName());
  }
  Object bar=runtimeService.getVariable(processInstance.getId(),"bar");
  assertNull("Expected 'bar' variable to be local to script",bar);
  Object foo=runtimeService.getVariable(processInstance.getId(),"foo");
  assertEquals("Could not find the 'foo' variable in variable scope","FOO",foo);
}
