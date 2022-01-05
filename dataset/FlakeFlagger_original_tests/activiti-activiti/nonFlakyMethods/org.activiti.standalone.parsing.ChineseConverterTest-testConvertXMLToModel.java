public void testConvertXMLToModel() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  bpmnModel=exportAndReadXMLFile(bpmnModel);
  deployProcess(bpmnModel);
}
