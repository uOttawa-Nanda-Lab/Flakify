@Test public void convertXMLToElementShouldSetTheImplementationFromXMLImplementationAttribute() throws Exception {
  given(reader.getAttributeValue(null,BpmnXMLConstants.ATTRIBUTE_TASK_IMPLEMENTATION)).willReturn("myConnector");
  BaseElement element=converter.convertXMLToElement(reader,new BpmnModel());
  assertThat(((ServiceTask)element).getImplementation()).isEqualTo("myConnector");
}
