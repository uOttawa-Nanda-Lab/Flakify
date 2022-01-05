public void testConvertXMLToModel() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  bpmnModel=exportAndReadXMLFile(bpmnModel);
  byte[] xml=new BpmnXMLConverter().convertToXML(bpmnModel);
  processEngine.getRepositoryService().createDeployment().name("test1").addString("test1.bpmn20.xml",new String(xml)).deploy();
  String processInstanceKey=runtimeService.startProcessInstanceByKey("process").getId();
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstanceKey).messageEventSubscriptionName("InterruptMessage").singleResult();
  assertNotNull(execution);
}
