@Test public void convertModelToXML() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  BpmnModel parsedModel=exportAndReadXMLFile(bpmnModel);
  validateModel(parsedModel);
  deployProcess(parsedModel);
}
