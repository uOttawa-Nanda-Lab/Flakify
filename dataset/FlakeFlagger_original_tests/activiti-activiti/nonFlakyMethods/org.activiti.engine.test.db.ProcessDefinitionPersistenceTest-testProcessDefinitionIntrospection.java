public void testProcessDefinitionIntrospection(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/db/processOne.bpmn20.xml").deploy().getId();
  String procDefId=repositoryService.createProcessDefinitionQuery().singleResult().getId();
  ProcessDefinition processDefinition=((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(procDefId);
  assertEquals(procDefId,processDefinition.getId());
  assertEquals("Process One",processDefinition.getName());
  Process process=repositoryService.getBpmnModel(processDefinition.getId()).getMainProcess();
  StartEvent startElement=(StartEvent)process.getFlowElement("start");
  assertNotNull(startElement);
  assertEquals("start",startElement.getId());
  assertEquals("S t a r t",startElement.getName());
  assertEquals("the start event",startElement.getDocumentation());
  List<SequenceFlow> outgoingFlows=startElement.getOutgoingFlows();
  assertEquals(1,outgoingFlows.size());
  assertEquals("${a == b}",outgoingFlows.get(0).getConditionExpression());
  EndEvent endElement=(EndEvent)process.getFlowElement("end");
  assertNotNull(endElement);
  assertEquals("end",endElement.getId());
  assertEquals("flow1",outgoingFlows.get(0).getId());
  assertEquals("Flow One",outgoingFlows.get(0).getName());
  assertEquals("The only transitions in the process",outgoingFlows.get(0).getDocumentation());
  assertSame(startElement,outgoingFlows.get(0).getSourceFlowElement());
  assertSame(endElement,outgoingFlows.get(0).getTargetFlowElement());
  repositoryService.deleteDeployment(deploymentId);
}
