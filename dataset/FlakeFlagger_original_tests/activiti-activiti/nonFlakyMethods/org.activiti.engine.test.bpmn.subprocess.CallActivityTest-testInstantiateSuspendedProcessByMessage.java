public void testInstantiateSuspendedProcessByMessage() throws Exception {
  BpmnModel messageTriggeredBpmnModel=loadBPMNModel(MESSAGE_TRIGGERED_PROCESS_RESOURCE);
  Deployment messageTriggeredBpmnDeployment=processEngine.getRepositoryService().createDeployment().name("messageTriggeredProcessDeployment").addBpmnModel("messageTriggered.bpmn20.xml",messageTriggeredBpmnModel).deploy();
  suspendProcessDefinitions(messageTriggeredBpmnDeployment);
  try {
    ProcessInstance childProcessInstance=runtimeService.startProcessInstanceByMessage("TRIGGER_PROCESS_MESSAGE");
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Cannot start process instance. Process definition Message Triggered Process",ae.getMessage());
  }
}
