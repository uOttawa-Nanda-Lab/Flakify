public void testIllegalUseOfResultVariableName(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/examples/bpmn/servicetask/JavaServiceTaskTest.testIllegalUseOfResultVariableName.bpmn20.xml").deploy();
    fail();
  }
 catch (  ActivitiException e) {
    assertTrue(e.getMessage().contains("resultVariable"));
  }
}
