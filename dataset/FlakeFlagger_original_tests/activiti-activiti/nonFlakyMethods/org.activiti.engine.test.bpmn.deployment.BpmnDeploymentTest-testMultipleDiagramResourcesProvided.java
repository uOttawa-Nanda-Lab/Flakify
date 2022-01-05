@Deployment(resources={"org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testMultipleDiagramResourcesProvided.bpmn20.xml","org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testMultipleDiagramResourcesProvided.a.jpg","org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testMultipleDiagramResourcesProvided.b.jpg","org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testMultipleDiagramResourcesProvided.c.jpg"}) public void testMultipleDiagramResourcesProvided(){
  ProcessDefinition processA=repositoryService.createProcessDefinitionQuery().processDefinitionKey("a").singleResult();
  ProcessDefinition processB=repositoryService.createProcessDefinitionQuery().processDefinitionKey("b").singleResult();
  ProcessDefinition processC=repositoryService.createProcessDefinitionQuery().processDefinitionKey("c").singleResult();
  assertEquals("org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testMultipleDiagramResourcesProvided.a.jpg",processA.getDiagramResourceName());
  assertEquals("org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testMultipleDiagramResourcesProvided.b.jpg",processB.getDiagramResourceName());
  assertEquals("org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testMultipleDiagramResourcesProvided.c.jpg",processC.getDiagramResourceName());
}
