@Test @Deployment public void testSubProcessEvents(){
  SubProcessEventListener listener=new SubProcessEventListener();
  processEngineConfiguration.getEventDispatcher().addEventListener(listener);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("subProcessEvents");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  Execution subProcessExecution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("subProcess").singleResult();
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  List<ActivitiEvent> events=listener.getEventsReceived();
  assertEquals(2,events.size());
  ActivitiActivityEvent event=(ActivitiActivityEvent)events.get(0);
  assertEquals("subProcess",event.getActivityType());
  assertEquals(subProcessExecution.getId(),event.getExecutionId());
  event=(ActivitiActivityEvent)events.get(1);
  assertEquals("subProcess",event.getActivityType());
  assertEquals(subProcessExecution.getId(),event.getExecutionId());
  processEngineConfiguration.getEventDispatcher().removeEventListener(listener);
}
