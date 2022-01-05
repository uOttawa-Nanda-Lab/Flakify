@Deployment(resources={"org/activiti/engine/test/bpmn/event/signal/SignalEventTests.catchAlertSignal.bpmn20.xml","org/activiti/engine/test/bpmn/event/signal/SignalEventTests.throwAlertSignalAsynch.bpmn20.xml"}) public void testSignalCatchIntermediateAsynch(){
  runtimeService.startProcessInstanceByKey("catchSignal");
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByKey("throwSignal");
  assertEquals(1,createEventSubscriptionQuery().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEquals(1,managementService.createJobQuery().count());
  try {
    processEngineConfiguration.getClock().setCurrentTime(new Date(System.currentTimeMillis() + 1000));
    waitForJobExecutorToProcessAllJobs(10000,100l);
    assertEquals(0,createEventSubscriptionQuery().count());
    assertEquals(0,runtimeService.createProcessInstanceQuery().count());
    assertEquals(0,managementService.createJobQuery().count());
  }
  finally {
    processEngineConfiguration.getClock().setCurrentTime(new Date());
  }
}
