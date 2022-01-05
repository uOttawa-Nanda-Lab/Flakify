@Deployment(resources={"org/activiti/engine/test/api/runtime/RuntimeServiceTest.catchAlertSignal.bpmn20.xml","org/activiti/engine/test/api/runtime/RuntimeServiceTest.catchPanicSignal.bpmn20.xml"}) public void testSignalEventReceived(){
  startSignalCatchProcesses();
  assertEquals(15,runtimeService.createExecutionQuery().count());
  runtimeService.signalEventReceived("alert");
  assertEquals(9,runtimeService.createExecutionQuery().count());
  runtimeService.signalEventReceived("panic");
  assertEquals(0,runtimeService.createExecutionQuery().count());
  startSignalCatchProcesses();
  for (int executions=3; executions > 0; executions--) {
    List<Execution> page=runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").listPage(0,1);
    runtimeService.signalEventReceived("alert",page.get(0).getId());
    assertEquals(executions - 1,runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").count());
  }
  for (int executions=3; executions > 0; executions--) {
    List<Execution> page=runtimeService.createExecutionQuery().signalEventSubscriptionName("panic").listPage(0,1);
    runtimeService.signalEventReceived("panic",page.get(0).getId());
    assertEquals(executions - 1,runtimeService.createExecutionQuery().signalEventSubscriptionName("panic").count());
  }
}
