public void testViolateProcessDefinitionIdMaximumLength(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/deployment/processWithLongId.bpmn20.xml").deploy();
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent(Problems.PROCESS_DEFINITION_ID_TOO_LONG,e.getMessage());
  }
  assertEquals(0,repositoryService.createDeploymentQuery().count());
}
