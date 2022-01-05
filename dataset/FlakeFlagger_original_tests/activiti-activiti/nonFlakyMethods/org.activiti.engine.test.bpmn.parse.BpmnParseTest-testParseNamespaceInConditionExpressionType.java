@Deployment public void testParseNamespaceInConditionExpressionType(){
  BpmnModel bpmnModel=repositoryService.getBpmnModel(repositoryService.createProcessDefinitionQuery().singleResult().getId());
  Process process=bpmnModel.getProcesses().get(0);
  assertNotNull(process);
  SequenceFlow sequenceFlow=(SequenceFlow)process.getFlowElement("SequenceFlow_3");
  assertEquals("#{approved}",sequenceFlow.getConditionExpression());
  sequenceFlow=(SequenceFlow)process.getFlowElement("SequenceFlow_4");
  assertEquals("#{!approved}",sequenceFlow.getConditionExpression());
}
