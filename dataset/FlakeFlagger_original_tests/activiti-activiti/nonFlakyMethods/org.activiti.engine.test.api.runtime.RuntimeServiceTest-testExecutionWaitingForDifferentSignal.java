@Deployment(resources={"org/activiti/engine/test/api/runtime/RuntimeServiceTest.catchAlertSignal.bpmn20.xml"}) public void testExecutionWaitingForDifferentSignal(){
  runtimeService.startProcessInstanceByKey("catchAlertSignal");
  Execution execution=runtimeService.createExecutionQuery().signalEventSubscriptionName("alert").singleResult();
  try {
    runtimeService.signalEventReceived("bogusSignal",execution.getId());
    fail("exception expected");
  }
 catch (  ActivitiException e) {
  }
}
