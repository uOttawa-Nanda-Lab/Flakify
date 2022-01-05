@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceCancelledEvents_cancel() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  listener.clearEventsReceived();
  runtimeService.deleteProcessInstance(processInstance.getId(),"delete_test");
  List<ActivitiEvent> processCancelledEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals("ActivitiEventType.PROCESS_CANCELLED was expected 1 time.",1,processCancelledEvents.size());
  ActivitiCancelledEvent processCancelledEvent=(ActivitiCancelledEvent)processCancelledEvents.get(0);
  assertTrue("The cause has to be the same as deleteProcessInstance method call",ActivitiCancelledEvent.class.isAssignableFrom(processCancelledEvent.getClass()));
  assertEquals("The process instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),processCancelledEvent.getProcessInstanceId());
  assertEquals("The execution instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),processCancelledEvent.getExecutionId());
  assertEquals("The cause has to be the same as in deleteProcessInstance method call","delete_test",processCancelledEvent.getCause());
  List<ActivitiEvent> taskCancelledEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);
  assertEquals("ActivitiEventType.ACTIVITY_CANCELLED was expected 1 time.",1,taskCancelledEvents.size());
  ActivitiActivityCancelledEvent activityCancelledEvent=(ActivitiActivityCancelledEvent)taskCancelledEvents.get(0);
  assertTrue("The cause has to be the same as deleteProcessInstance method call",ActivitiActivityCancelledEvent.class.isAssignableFrom(activityCancelledEvent.getClass()));
  assertEquals("The process instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),activityCancelledEvent.getProcessInstanceId());
  assertEquals("The cause has to be the same as in deleteProcessInstance method call","delete_test",activityCancelledEvent.getCause());
  listener.clearEventsReceived();
}
