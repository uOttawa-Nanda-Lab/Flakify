public void testParseTerminateEndEventDefinitionWithExtensions(){
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.parseExtensionElements.bpmn20.xml").deploy();
  ProcessDefinition processDefinitionQuery=repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
  BpmnModel bpmnModel=this.processEngineConfiguration.getProcessDefinitionCache().get(processDefinitionQuery.getId()).getBpmnModel();
  Map<String,List<ExtensionElement>> extensionElements=bpmnModel.getProcesses().get(0).findFlowElementsOfType(EndEvent.class).get(0).getExtensionElements();
  assertThat(extensionElements.size(),is(1));
  List<ExtensionElement> strangeProperties=extensionElements.get("strangeProperty");
  assertThat(strangeProperties.size(),is(1));
  ExtensionElement strangeProperty=strangeProperties.get(0);
  assertThat(strangeProperty.getNamespace(),is("http://activiti.org/bpmn"));
  assertThat(strangeProperty.getElementText(),is("value"));
  assertThat(strangeProperty.getAttributes().size(),is(1));
  ExtensionAttribute id=strangeProperty.getAttributes().get("id").get(0);
  assertThat(id.getName(),is("id"));
  assertThat(id.getValue(),is("strangeId"));
  repositoryService.deleteDeployment(deployment.getId());
}
