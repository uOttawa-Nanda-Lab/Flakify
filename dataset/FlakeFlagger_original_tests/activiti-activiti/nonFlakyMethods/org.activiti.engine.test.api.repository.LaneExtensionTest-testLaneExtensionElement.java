@Test @Deployment public void testLaneExtensionElement(){
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionKey("swimlane-extension").singleResult();
  BpmnModel bpmnModel=repositoryService.getBpmnModel(processDefinition.getId());
  byte[] xml=new BpmnXMLConverter().convertToXML(bpmnModel);
  System.out.println(new String(xml));
  Process bpmnProcess=bpmnModel.getMainProcess();
  for (  Lane l : bpmnProcess.getLanes()) {
    Map<String,List<ExtensionElement>> extensions=l.getExtensionElements();
    Assert.assertTrue(extensions.size() > 0);
  }
}
