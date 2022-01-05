@Test public void doubleConversionValidation() throws Exception {
  BpmnModel bpmnModel=readJsonFile();
  validateModel(bpmnModel);
  bpmnModel=convertToJsonAndBack(bpmnModel);
  validateModel(bpmnModel);
}
