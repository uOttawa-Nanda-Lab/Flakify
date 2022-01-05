public void testGetLatestProcessDefinitionTextByKey(){
  disableValidation();
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/regression/ProcessValidationExecutedAfterDeployTest.bpmn20.xml").deploy();
  enableValidation();
  clearDeploymentCache();
  ProcessDefinition definition=getLatestProcessDefinitionVersionByKey("testProcess1");
  if (definition == null) {
    fail("Error occurred in fetching process model.");
  }
  try {
    repositoryService.getProcessModel(definition.getId());
    assertTrue(true);
  }
 catch (  ActivitiException e) {
    fail("Error occurred in fetching process model.");
  }
  for (  org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId());
  }
}
