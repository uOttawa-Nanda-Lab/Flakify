public void testDuplicateAssigneeDeclaration(){
  try {
    String resource=TestHelper.getBpmnProcessDefinitionResource(getClass(),"testDuplicateAssigneeDeclaration");
    repositoryService.createDeployment().addClasspathResource(resource).deploy();
    fail("Invalid BPMN 2.0 process should not parse, but it gets parsed successfully");
  }
 catch (  XMLException e) {
  }
}
