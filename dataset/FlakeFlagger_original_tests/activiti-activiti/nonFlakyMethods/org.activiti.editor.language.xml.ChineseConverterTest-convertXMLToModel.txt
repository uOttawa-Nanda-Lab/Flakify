@Test public void convertXMLToModel() throws Exception {
  BpmnModel bpmnModel=readXMLFile();
  deployProcess(bpmnModel);
}
