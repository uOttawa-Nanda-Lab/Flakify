@Test public void convertJsonToModel() throws Exception {
  BpmnModel bpmnModel=readJsonFile();
  validateModel(bpmnModel);
}
