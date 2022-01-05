@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInParentProcess.bpmn","org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testProcessInstanceTerminatedEvents_terminateInParentProcess() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateParentProcess");
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTerminateEnd").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  List<ActivitiEvent> processTerminatedEvents=listener.filterEvents(ActivitiEventType.PROCESS_CANCELLED);
  assertEquals("There should be exactly one ActivitiEventType.PROCESS_TERMINATED event after the task complete.",1,processTerminatedEvents.size());
  ActivitiProcessCancelledEventImpl processCancelledEvent=(ActivitiProcessCancelledEventImpl)processTerminatedEvents.get(0);
  assertThat(processCancelledEvent.getProcessInstanceId(),is(pi.getProcessInstanceId()));
  assertThat(processCancelledEvent.getProcessDefinitionId(),containsString("terminateParentProcess"));
  List<ActivitiEvent> activityTerminatedEvents=listener.filterEvents(ActivitiEventType.ACTIVITY_CANCELLED);
  assertThat("3 activities must be cancelled.",activityTerminatedEvents.size(),is(3));
  for (  ActivitiEvent event : activityTerminatedEvents) {
    ActivitiActivityCancelledEventImpl activityEvent=(ActivitiActivityCancelledEventImpl)event;
    if (activityEvent.getActivityId().equals("theTask")) {
      assertThat("The user task must be terminated in the called sub process.",activityEvent.getActivityId(),is("theTask"));
      assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_3"));
    }
 else     if (activityEvent.getActivityId().equals("CallActivity_1")) {
      assertThat("The call activity must be terminated",activityEvent.getActivityId(),is("CallActivity_1"));
      assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_3"));
    }
 else     if (activityEvent.getActivityId().equals("EndEvent_3")) {
      assertThat("The end event must be terminated",activityEvent.getActivityId(),is("EndEvent_3"));
      assertThat("The cause must be terminate end event",((FlowNode)activityEvent.getCause()).getId(),is("EndEvent_3"));
    }
  }
}
