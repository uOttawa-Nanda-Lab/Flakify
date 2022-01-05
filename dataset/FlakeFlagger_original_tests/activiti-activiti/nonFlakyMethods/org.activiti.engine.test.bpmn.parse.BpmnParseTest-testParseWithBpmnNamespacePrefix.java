public void testParseWithBpmnNamespacePrefix(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/parse/BpmnParseTest.testParseWithBpmnNamespacePrefix.bpmn20.xml").deploy();
  assertEquals(1,repositoryService.createProcessDefinitionQuery().count());
  repositoryService.deleteDeployment(repositoryService.createDeploymentQuery().singleResult().getId(),true);
}
