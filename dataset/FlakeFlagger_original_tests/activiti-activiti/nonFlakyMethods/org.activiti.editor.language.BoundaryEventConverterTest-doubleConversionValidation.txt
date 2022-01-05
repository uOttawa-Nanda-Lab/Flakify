@Test public void doubleConversionValidation() throws Exception {
  BpmnModel bpmnModel=readJsonFile();
  bpmnModel=convertToJsonAndBack(bpmnModel);
  validateModel(bpmnModel);
}
