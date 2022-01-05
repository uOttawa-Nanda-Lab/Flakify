@Test public void convertXMLToModel() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  validateModel(bpmnModel);
}
