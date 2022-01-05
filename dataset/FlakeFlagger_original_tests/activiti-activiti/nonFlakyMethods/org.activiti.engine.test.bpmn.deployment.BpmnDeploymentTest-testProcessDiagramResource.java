@Deployment(resources={"org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testProcessDiagramResource.bpmn20.xml","org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testProcessDiagramResource.jpg"}) public void testProcessDiagramResource(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertEquals("org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testProcessDiagramResource.bpmn20.xml",processDefinition.getResourceName());
  BpmnModel processModel=repositoryService.getBpmnModel(processDefinition.getId());
  List<StartEvent> startEvents=processModel.getMainProcess().findFlowElementsOfType(StartEvent.class);
  assertEquals(1,startEvents.size());
  assertEquals("someFormKey",startEvents.get(0).getFormKey());
  String diagramResourceName=processDefinition.getDiagramResourceName();
  assertEquals("org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testProcessDiagramResource.jpg",diagramResourceName);
  InputStream diagramStream=repositoryService.getResourceAsStream(deploymentIdFromDeploymentAnnotation,"org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testProcessDiagramResource.jpg");
  byte[] diagramBytes=IoUtil.readInputStream(diagramStream,"diagram stream");
  assertEquals(33343,diagramBytes.length);
}
