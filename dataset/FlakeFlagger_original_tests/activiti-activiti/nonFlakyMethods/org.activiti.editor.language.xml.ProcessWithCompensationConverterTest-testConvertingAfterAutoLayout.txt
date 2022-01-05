@Test public void testConvertingAfterAutoLayout(){
  final InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("ProcessWithCompensationAssociation.bpmn20.xml");
  BpmnXMLConverter bpmnXMLConverter=new BpmnXMLConverter();
  BpmnModel bpmnModel1=bpmnXMLConverter.convertToBpmnModel(new InputStreamProvider(){
    @Override public InputStream getInputStream(){
      return inputStream;
    }
  }
,false,false);
  if (bpmnModel1.getLocationMap().size() == 0) {
    BpmnAutoLayout bpmnLayout=new BpmnAutoLayout(bpmnModel1);
    bpmnLayout.execute();
  }
  byte[] xmlByte=bpmnXMLConverter.convertToXML(bpmnModel1);
  final InputStream byteArrayInputStream=new ByteArrayInputStream(xmlByte);
  BpmnModel bpmnModel2=bpmnXMLConverter.convertToBpmnModel(new InputStreamProvider(){
    @Override public InputStream getInputStream(){
      return byteArrayInputStream;
    }
  }
,false,false);
  assertEquals(10,bpmnModel1.getLocationMap().size());
  assertEquals(10,bpmnModel2.getLocationMap().size());
  assertEquals(7,bpmnModel1.getFlowLocationMap().size());
  assertEquals(7,bpmnModel2.getFlowLocationMap().size());
}
