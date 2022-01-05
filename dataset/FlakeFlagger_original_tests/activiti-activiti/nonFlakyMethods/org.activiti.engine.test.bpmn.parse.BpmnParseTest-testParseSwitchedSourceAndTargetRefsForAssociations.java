public void testParseSwitchedSourceAndTargetRefsForAssociations(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/parse/BpmnParseTest.testParseSwitchedSourceAndTargetRefsForAssociations.bpmn20.xml").deploy();
  assertEquals(1,repositoryService.createProcessDefinitionQuery().count());
  repositoryService.deleteDeployment(repositoryService.createDeploymentQuery().singleResult().getId(),true);
}
