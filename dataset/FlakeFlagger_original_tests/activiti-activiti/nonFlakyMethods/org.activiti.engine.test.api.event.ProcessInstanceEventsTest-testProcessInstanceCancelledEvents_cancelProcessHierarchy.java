@Deployment(resources={"org/activiti/engine/test/api/runtime/nestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testProcessInstanceCancelledEvents_cancelProcessHierarchy() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("nestedSimpleSubProcess");
  ProcessInstance subProcess=runtimeService.createProcessInstanceQuery().superProcessInstanceId(processInstance.getId()).singleResult();
  assertNotNull(processInstance);
  listener.clearEventsReceived();
  runtimeService.deleteProcessInstance(processInstance.getId(),"delete_test");
  List<ActivitiEvent> processCancelledEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals("ActivitiEventType.PROCESS_CANCELLED was expected 2 times.",2,processCancelledEvents.size());
  ActivitiCancelledEvent processCancelledEvent=(ActivitiCancelledEvent)processCancelledEvents.get(0);
  assertTrue("The cause has to be the same as deleteProcessInstance method call",ActivitiCancelledEvent.class.isAssignableFrom(processCancelledEvent.getClass()));
  assertEquals("The process instance has to be the same as in deleteProcessInstance method call",subProcess.getId(),processCancelledEvent.getProcessInstanceId());
  assertEquals("The execution instance has to be the same as in deleteProcessInstance method call",subProcess.getId(),processCancelledEvent.getExecutionId());
  assertEquals("The cause has to be the same as in deleteProcessInstance method call","delete_test",processCancelledEvent.getCause());
  processCancelledEvent=(ActivitiCancelledEvent)processCancelledEvents.get(1);
  assertTrue("The cause has to be the same as deleteProcessInstance method call",ActivitiCancelledEvent.class.isAssignableFrom(processCancelledEvent.getClass()));
  assertEquals("The process instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),processCancelledEvent.getProcessInstanceId());
  assertEquals("The execution instance has to be the same as in deleteProcessInstance method call",processInstance.getId(),processCancelledEvent.getExecutionId());
  assertEquals("The cause has to be the same as in deleteProcessInstance method call","delete_test",processCancelledEvent.getCause());
  assertEquals("No task can be active for deleted process.",0,this.taskService.createTaskQuery().processInstanceId(processInstance.getId()).count());
  List<ActivitiEvent> taskCancelledEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);
  assertEquals("ActivitiEventType.ACTIVITY_CANCELLED was expected 1 time.",1,taskCancelledEvents.size());
  ActivitiActivityCancelledEvent activityCancelledEvent=(ActivitiActivityCancelledEvent)taskCancelledEvents.get(0);
  assertTrue("The cause has to be the same as deleteProcessInstance method call",ActivitiActivityCancelledEvent.class.isAssignableFrom(activityCancelledEvent.getClass()));
  assertEquals("The process instance has to point to the subprocess",subProcess.getId(),activityCancelledEvent.getProcessInstanceId());
  assertEquals("The cause has to be the same as in deleteProcessInstance method call","delete_test",activityCancelledEvent.getCause());
  listener.clearEventsReceived();
}
