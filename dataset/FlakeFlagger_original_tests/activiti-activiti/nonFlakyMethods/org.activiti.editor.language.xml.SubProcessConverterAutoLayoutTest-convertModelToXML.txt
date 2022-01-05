@Test public void convertModelToXML() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  BpmnAutoLayout bpmnAutoLayout=new BpmnAutoLayout(bpmnModel);
  bpmnAutoLayout.execute();
  BpmnModel parsedModel=exportAndReadXMLFile(bpmnModel);
  validateModel(parsedModel);
  deployProcess(parsedModel);
}
