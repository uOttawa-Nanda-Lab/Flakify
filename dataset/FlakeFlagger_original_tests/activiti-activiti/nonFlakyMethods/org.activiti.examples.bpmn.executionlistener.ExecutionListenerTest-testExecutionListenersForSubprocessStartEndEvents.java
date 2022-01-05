@Deployment(resources={"org/activiti/examples/bpmn/executionlistener/ExecutionListenersForSubprocessStartEndEvent.bpmn20.xml"}) public void testExecutionListenersForSubprocessStartEndEvents(){
  RecorderExecutionListener.clear();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("executionListenersProcess");
  List<RecordedEvent> recordedEvents=RecorderExecutionListener.getRecordedEvents();
  assertEquals(1,recordedEvents.size());
  assertEquals("Process Start",recordedEvents.get(0).getParameter());
  RecorderExecutionListener.clear();
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  recordedEvents=RecorderExecutionListener.getRecordedEvents();
  assertEquals(3,recordedEvents.size());
  assertEquals("Subprocess Start",recordedEvents.get(0).getParameter());
  assertEquals("Subprocess End",recordedEvents.get(1).getParameter());
  assertEquals("Process End",recordedEvents.get(2).getParameter());
}
