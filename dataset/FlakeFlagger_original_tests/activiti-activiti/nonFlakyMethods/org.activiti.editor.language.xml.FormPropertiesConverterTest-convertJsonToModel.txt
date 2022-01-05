@Test public void convertJsonToModel() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  validateModel(bpmnModel);
}
