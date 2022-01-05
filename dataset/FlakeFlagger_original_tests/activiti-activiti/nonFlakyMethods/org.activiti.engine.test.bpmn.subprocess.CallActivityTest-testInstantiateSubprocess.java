public void testInstantiateSubprocess() throws Exception {
  BpmnModel mainBpmnModel=loadBPMNModel(MAIN_PROCESS_RESOURCE);
  BpmnModel childBpmnModel=loadBPMNModel(CHILD_PROCESS_RESOURCE);
  Deployment childDeployment=processEngine.getRepositoryService().createDeployment().name("childProcessDeployment").addBpmnModel("childProcess.bpmn20.xml",childBpmnModel).deploy();
  processEngine.getRepositoryService().createDeployment().name("masterProcessDeployment").addBpmnModel("masterProcess.bpmn20.xml",mainBpmnModel).deploy();
  suspendProcessDefinitions(childDeployment);
  try {
    runtimeService.startProcessInstanceByKey("masterProcess");
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Cannot start process instance. Process definition Child Process",ae.getMessage());
  }
}
