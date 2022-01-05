public void testInvalidProcessDefinition(){
  try {
    String resource=TestHelper.getBpmnProcessDefinitionResource(getClass(),"testInvalidProcessDefinition");
    repositoryService.createDeployment().name(resource).addClasspathResource(resource).deploy();
    fail();
  }
 catch (  XMLException e) {
  }
}
