@Deployment(resources="org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testProcessTerminate.bpmn") public void testProcessInstanceTerminatedEvents() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  long executionEntities=runtimeService.createExecutionQuery().processInstanceId(pi.getId()).count();
  assertEquals(3,executionEntities);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateTask").singleResult();
  taskService.complete(task.getId());
  List<ActivitiEvent> processTerminatedEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals("There should be exactly one ActivitiEventType.PROCESS_CANCELLED event after the task complete.",1,processTerminatedEvents.size());
  ActivitiProcessCancelledEventImpl activitiEvent=(ActivitiProcessCancelledEventImpl)processTerminatedEvents.get(0);
  assertThat(activitiEvent.getProcessInstanceId(),is(pi.getProcessInstanceId()));
  List<ActivitiEvent> activityTerminatedEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);
  assertThat("There should be exactly two ActivitiEventType.ACTIVITY_CANCELLED event after the task complete.",activityTerminatedEvents.size(),is(2));
  for (  ActivitiEvent event : activityTerminatedEvents) {
    ActivitiActivityCancelledEventImpl activityEvent=(ActivitiActivityCancelledEventImpl)event;
    if (activityEvent.getActivityId().equals("preNormalTerminateTask")) {
      assertThat("The user task must be terminated",activityEvent.getActivityId(),is("preNormalTerminateTask"));
      assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_2"));
    }
 else     if (activityEvent.getActivityId().equals("EndEvent_2")) {
      assertThat("The end event must be terminated",activityEvent.getActivityId(),is("EndEvent_2"));
      assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_2"));
    }
  }
}
