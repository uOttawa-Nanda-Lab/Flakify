@Deployment public void testParseDiagramInterchangeElementsForUnknownModelElements(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("TestAnnotation").singleResult();
  BpmnModel model=repositoryService.getBpmnModel(processDefinition.getId());
  Process mainProcess=model.getMainProcess();
  assertEquals(0,mainProcess.getExtensionElements().size());
}
