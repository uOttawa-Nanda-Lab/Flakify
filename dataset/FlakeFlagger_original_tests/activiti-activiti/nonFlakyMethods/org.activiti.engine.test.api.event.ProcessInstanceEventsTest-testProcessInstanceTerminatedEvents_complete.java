@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceTerminatedEvents_complete() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  List<ActivitiEvent> processTerminatedEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals("There should be no ActivitiEventType.PROCESS_TERMINATED event after process complete.",0,processTerminatedEvents.size());
}
