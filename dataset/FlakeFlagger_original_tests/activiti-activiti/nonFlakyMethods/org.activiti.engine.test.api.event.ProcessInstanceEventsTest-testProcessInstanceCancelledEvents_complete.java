@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceCancelledEvents_complete() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  List<ActivitiEvent> processCancelledEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals("There should be no ActivitiEventType.PROCESS_CANCELLED event after process complete.",0,processCancelledEvents.size());
  List<ActivitiEvent> taskCancelledEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);
  assertEquals("There should be no ActivitiEventType.ACTIVITY_CANCELLED event.",0,taskCancelledEvents.size());
}
