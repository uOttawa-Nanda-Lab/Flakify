public void testDeployNonSchemaConformantXml(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/repository/nonSchemaConformantXml.bpmn20.xml").deploy().getId();
    fail();
  }
 catch (  XMLException e) {
  }
}
