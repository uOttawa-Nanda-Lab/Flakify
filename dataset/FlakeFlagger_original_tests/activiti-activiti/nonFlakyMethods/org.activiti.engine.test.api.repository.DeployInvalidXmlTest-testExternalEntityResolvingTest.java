public void testExternalEntityResolvingTest(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/repository/DeployInvalidXmlTest.testExternalEntityResolvingTest.bpmn20.xml").deploy().getId();
  try {
    ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
    assertEquals("Test 1 2 3 null",processDefinition.getDescription());
  }
  finally {
    repositoryService.deleteDeployment(deploymentId,true);
  }
}
