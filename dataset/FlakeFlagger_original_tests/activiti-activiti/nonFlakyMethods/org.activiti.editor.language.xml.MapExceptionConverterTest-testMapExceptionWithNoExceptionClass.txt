@Test public void testMapExceptionWithNoExceptionClass() throws Exception {
  resourceName="mapException/mapExceptionNoExceptionClass.bpmn";
  BpmnModel bpmnModel=readXMLFile();
  FlowElement flowElement=bpmnModel.getMainProcess().getFlowElement("servicetaskWithAndTrueAndChildren");
  assertNotNull(flowElement);
  assertTrue(flowElement instanceof ServiceTask);
  assertEquals("servicetaskWithAndTrueAndChildren",flowElement.getId());
  ServiceTask serviceTask=(ServiceTask)flowElement;
  assertNotNull(serviceTask.getMapExceptions());
  assertEquals(1,serviceTask.getMapExceptions().size());
  assertNotNull(serviceTask.getMapExceptions().get(0).getClassName());
  assertEquals(0,serviceTask.getMapExceptions().get(0).getClassName().length());
}
