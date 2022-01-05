@Test public void doubleConversionValidation() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  validateModel(bpmnModel);
  bpmnModel=exportAndReadXMLFile(bpmnModel);
  validateModel(bpmnModel);
}
