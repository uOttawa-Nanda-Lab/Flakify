@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelCallActivity.bpmn20.xml","org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.externalSubProcess.bpmn20.xml"}) public void testDeleteMultiInstanceCallActivityProcessInstance(){
  assertEquals(0,taskService.createTaskQuery().count());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("miParallelCallActivity");
  assertEquals(7,runtimeService.createProcessInstanceQuery().count());
  assertEquals(12,taskService.createTaskQuery().count());
  this.listener.clearEventsReceived();
  runtimeService.deleteProcessInstance(processInstance.getId(),"testing instance deletion");
  assertThat("Task cancelled event has to be fired.",this.listener.getEventsReceived().get(0).getType(),is(ActivitiEventType.ACTIVITY_CANCELLED));
  assertThat("SubProcess cancelled event has to be fired.",this.listener.getEventsReceived().get(2).getType(),is(ActivitiEventType.PROCESS_CANCELLED));
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  assertEquals(0,taskService.createTaskQuery().count());
}
