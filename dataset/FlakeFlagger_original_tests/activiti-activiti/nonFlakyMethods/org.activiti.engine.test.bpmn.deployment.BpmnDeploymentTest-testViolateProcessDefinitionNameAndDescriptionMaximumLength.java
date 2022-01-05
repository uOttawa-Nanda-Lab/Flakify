public void testViolateProcessDefinitionNameAndDescriptionMaximumLength(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/deployment/processWithLongNameAndDescription.bpmn20.xml").deploy();
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent(Problems.PROCESS_DEFINITION_NAME_TOO_LONG,e.getMessage());
    assertTextPresent(Problems.PROCESS_DEFINITION_DOCUMENTATION_TOO_LONG,e.getMessage());
  }
  assertEquals(0,repositoryService.createDeploymentQuery().count());
}
