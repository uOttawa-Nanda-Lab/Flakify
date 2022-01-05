public void testInstantiateSuspendedChildProcess() throws Exception {
  BpmnModel childBpmnModel=loadBPMNModel(CHILD_PROCESS_RESOURCE);
  Deployment childDeployment=processEngine.getRepositoryService().createDeployment().name("childProcessDeployment").addBpmnModel("childProcess.bpmn20.xml",childBpmnModel).deploy();
  suspendProcessDefinitions(childDeployment);
  try {
    runtimeService.startProcessInstanceByKey("childProcess");
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Cannot start process instance. Process definition Child Process",ae.getMessage());
  }
}
