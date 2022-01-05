@Deployment(resources={"org/activiti/engine/test/bpmn/event/signal/SignalEventTests.catchMultipleSignals.bpmn20.xml","org/activiti/engine/test/bpmn/event/signal/SignalEventTests.throwAlertSignal.bpmn20.xml","org/activiti/engine/test/bpmn/event/signal/SignalEventTests.throwAbortSignal.bpmn20.xml"}) public void testSignalCatchDifferentSignals(){
  runtimeService.startProcessInstanceByKey("catchSignal");
  assertEquals(2,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByKey("throwAbort");
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  Task taskAfterAbort=taskService.createTaskQuery().taskAssignee("gonzo").singleResult();
  assertNotNull(taskAfterAbort);
  taskService.complete(taskAfterAbort.getId());
  runtimeService.startProcessInstanceByKey("throwSignal");
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
