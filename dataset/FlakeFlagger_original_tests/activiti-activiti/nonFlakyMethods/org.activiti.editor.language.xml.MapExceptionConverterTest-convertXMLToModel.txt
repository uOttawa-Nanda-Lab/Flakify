@Test public void convertXMLToModel() throws Exception {
  resourceName="mapException/mapExceptionModel.bpmn";
  BpmnModel bpmnModel=readXMLFile();
  validateModel(bpmnModel);
}
