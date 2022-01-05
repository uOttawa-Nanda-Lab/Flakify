public void testInstantiateProcessByMessage() throws Exception {
  BpmnModel messageTriggeredBpmnModel=loadBPMNModel(MESSAGE_TRIGGERED_PROCESS_RESOURCE);
  processEngine.getRepositoryService().createDeployment().name("messageTriggeredProcessDeployment").addBpmnModel("messageTriggered.bpmn20.xml",messageTriggeredBpmnModel).deploy();
  ProcessInstance childProcessInstance=runtimeService.startProcessInstanceByMessage("TRIGGER_PROCESS_MESSAGE");
  assertNotNull(childProcessInstance);
}
