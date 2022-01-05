@Deployment(resources={"org/activiti/engine/test/bpmn/event/signal/SignalEventTests.catchAlertSignal.bpmn20.xml","org/activiti/engine/test/bpmn/event/signal/SignalEventTests.throwAlertSignal.bpmn20.xml"}) public void testSignalCatchIntermediate(){
  runtimeService.startProcessInstanceByKey("catchSignal");
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByKey("throwSignal");
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
