public void testInstantiateChildProcess() throws Exception {
  BpmnModel childBpmnModel=loadBPMNModel(CHILD_PROCESS_RESOURCE);
  processEngine.getRepositoryService().createDeployment().name("childProcessDeployment").addBpmnModel("childProcess.bpmn20.xml",childBpmnModel).deploy();
  ProcessInstance childProcessInstance=runtimeService.startProcessInstanceByKey("childProcess");
  assertNotNull(childProcessInstance);
}
