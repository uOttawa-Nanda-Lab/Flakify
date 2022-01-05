@Test public void testDisableValidation(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/standalone/validation/invalid_process_xsd_error.bpmn20.xml").deploy();
    Assert.fail();
  }
 catch (  XMLException e) {
  }
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/standalone/validation/invalid_process_xsd_error.bpmn20.xml").disableSchemaValidation().deploy();
    Assert.fail();
  }
 catch (  ActivitiException e) {
  }
}
