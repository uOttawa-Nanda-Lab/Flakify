@Deployment(resources={"org/activiti/engine/test/api/runtime/RuntimeServiceTest.catchAlertMessage.bpmn20.xml","org/activiti/engine/test/api/runtime/RuntimeServiceTest.catchPanicMessage.bpmn20.xml"}) public void testMessageEventReceived(){
  startMessageCatchProcesses();
  assertEquals(12,runtimeService.createExecutionQuery().count());
  for (int executions=3; executions > 0; executions--) {
    List<Execution> page=runtimeService.createExecutionQuery().messageEventSubscriptionName("alert").listPage(0,1);
    runtimeService.messageEventReceived("alert",page.get(0).getId());
    assertEquals(executions - 1,runtimeService.createExecutionQuery().messageEventSubscriptionName("alert").count());
  }
  for (int executions=3; executions > 0; executions--) {
    List<Execution> page=runtimeService.createExecutionQuery().messageEventSubscriptionName("panic").listPage(0,1);
    runtimeService.messageEventReceived("panic",page.get(0).getId());
    assertEquals(executions - 1,runtimeService.createExecutionQuery().messageEventSubscriptionName("panic").count());
  }
}
