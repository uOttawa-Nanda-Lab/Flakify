@Deployment(resources={"org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.testCatchErrorOnCallActivity-parent.bpmn20.xml","org/activiti/engine/test/bpmn/event/error/BoundaryErrorEventTest.subprocess.bpmn20.xml"}) public void testProcessCompletedEvents_callActivityErrorEndEvent() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("catchErrorOnCallActivity");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("Task in subprocess",task.getName());
  List<ProcessInstance> subProcesses=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).list();
  assertEquals(1,subProcesses.size());
  taskService.complete(task.getId());
  List<ActivitiEvent> processCompletedEvents=listener.filterEvents(ActivitiEventType.PROCESS_COMPLETED_WITH_ERROR_END_EVENT);
  assertEquals("There should be exactly an ActivitiEventType.PROCESS_COMPLETED_WITH_ERROR_END_EVENT event after the task complete.",1,processCompletedEvents.size());
  ActivitiEntityEvent processCompletedEvent=(ActivitiEntityEvent)processCompletedEvents.get(0);
  assertEquals(subProcesses.get(0).getId(),processCompletedEvent.getExecutionId());
  task=taskService.createTaskQuery().singleResult();
  assertEquals("Escalated Task",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
}
