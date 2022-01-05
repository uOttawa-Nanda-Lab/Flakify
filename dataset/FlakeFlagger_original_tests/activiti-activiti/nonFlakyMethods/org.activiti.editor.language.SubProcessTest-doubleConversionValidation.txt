@Test public void doubleConversionValidation() throws Exception {
  BpmnModel bpmnModel=readXmlFile();
  validateModel(bpmnModel);
  bpmnModel=convertToJsonAndBack(bpmnModel);
  validateModel(bpmnModel);
}
