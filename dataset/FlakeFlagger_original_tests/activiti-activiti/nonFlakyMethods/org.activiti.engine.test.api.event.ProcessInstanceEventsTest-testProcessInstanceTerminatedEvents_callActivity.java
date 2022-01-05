@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInCallActivity.bpmn","org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.subProcessTerminate.bpmn"}) public void testProcessInstanceTerminatedEvents_callActivity() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preNormalEnd").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  List<ActivitiEvent> processTerminatedEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals("There should be exactly one ActivitiEventType.PROCESS_CANCELLED event after the task complete.",1,processTerminatedEvents.size());
  ActivitiProcessCancelledEventImpl processCancelledEvent=(ActivitiProcessCancelledEventImpl)processTerminatedEvents.get(0);
  assertNotEquals(pi.getProcessInstanceId(),processCancelledEvent.getProcessInstanceId());
  assertThat(processCancelledEvent.getProcessDefinitionId(),containsString("terminateEndEventSubprocessExample"));
}
