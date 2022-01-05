@Test public void testWarningError() throws UnsupportedEncodingException, XMLStreamException {
  String flowWithoutConditionNoDefaultFlow="<?xml version='1.0' encoding='UTF-8'?>" + "<definitions id='definitions' xmlns='http://www.omg.org/spec/BPMN/20100524/MODEL' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:activiti='http://activiti.org/bpmn' targetNamespace='Examples'>" + "  <process id='exclusiveGwDefaultSequenceFlow'> "+ "    <startEvent id='theStart' /> "+ "    <sequenceFlow id='flow1' sourceRef='theStart' targetRef='exclusiveGw' /> "+ "    <exclusiveGateway id='exclusiveGw' name='Exclusive Gateway' /> "+ "    <sequenceFlow id='flow2' sourceRef='exclusiveGw' targetRef='theTask1'> "+ "      <conditionExpression xsi:type='tFormalExpression'>${input == 1}</conditionExpression> "+ "    </sequenceFlow> "+ "    <sequenceFlow id='flow3' sourceRef='exclusiveGw' targetRef='theTask2'/> "+ "    <sequenceFlow id='flow4' sourceRef='exclusiveGw' targetRef='theTask2'/> "+ "    <userTask id='theTask1' name='Input is one' /> "+ "    <userTask id='theTask2' name='Default input' /> "+ "  </process>"+ "</definitions>";
  XMLInputFactory xif=XMLInputFactory.newInstance();
  InputStreamReader in=new InputStreamReader(new ByteArrayInputStream(flowWithoutConditionNoDefaultFlow.getBytes()),"UTF-8");
  XMLStreamReader xtr=xif.createXMLStreamReader(in);
  BpmnModel bpmnModel=new BpmnXMLConverter().convertToBpmnModel(xtr);
  Assert.assertNotNull(bpmnModel);
  List<ValidationError> allErrors=processValidator.validate(bpmnModel);
  Assert.assertEquals(1,allErrors.size());
  Assert.assertTrue(allErrors.get(0).isWarning());
}
