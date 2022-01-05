public void testSuspendMultipleProcessDefinitionsByKey(){
  int nrOfProcessDefinitions=3;
  for (int i=0; i < nrOfProcessDefinitions; i++) {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml").deploy();
  }
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
  repositoryService.suspendProcessDefinitionByKey("oneTaskProcess");
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().suspended().count());
  repositoryService.activateProcessDefinitionByKey("oneTaskProcess");
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().suspended().count());
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  repositoryService.suspendProcessDefinitionByKey("oneTaskProcess",true,null);
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(0,repositoryService.createProcessDefinitionQuery().active().count());
  assertEquals(nrOfProcessDefinitions,repositoryService.createProcessDefinitionQuery().suspended().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().suspended().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().active().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  for (  org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
