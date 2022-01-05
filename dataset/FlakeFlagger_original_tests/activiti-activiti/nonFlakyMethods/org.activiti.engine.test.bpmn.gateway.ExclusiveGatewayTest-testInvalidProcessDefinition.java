public void testInvalidProcessDefinition(){
  String defaultFlowWithCondition="<?xml version='1.0' encoding='UTF-8'?>" + "<definitions id='definitions' xmlns='http://www.omg.org/spec/BPMN/20100524/MODEL' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:activiti='http://activiti.org/bpmn' targetNamespace='Examples'>" + "  <process id='exclusiveGwDefaultSequenceFlow'> "+ "    <startEvent id='theStart' /> "+ "    <sequenceFlow id='flow1' sourceRef='theStart' targetRef='exclusiveGw' /> "+ "    <exclusiveGateway id='exclusiveGw' name='Exclusive Gateway' default='flow3' /> "+ "    <sequenceFlow id='flow2' sourceRef='exclusiveGw' targetRef='theTask1'> "+ "      <conditionExpression xsi:type='tFormalExpression'>${input == 1}</conditionExpression> "+ "    </sequenceFlow> "+ "    <sequenceFlow id='flow3' sourceRef='exclusiveGw' targetRef='theTask2'> "+ "      <conditionExpression xsi:type='tFormalExpression'>${input == 3}</conditionExpression> "+ "    </sequenceFlow> "+ "    <userTask id='theTask1' name='Input is one' /> "+ "    <userTask id='theTask2' name='Default input' /> "+ "  </process>"+ "</definitions>";
  try {
    repositoryService.createDeployment().addString("myprocess.bpmn20.xml",defaultFlowWithCondition).deploy();
    fail();
  }
 catch (  Exception e) {
  }
  String noOutgoingFlow="<?xml version='1.0' encoding='UTF-8'?>" + "<definitions id='definitions' xmlns='http://www.omg.org/spec/BPMN/20100524/MODEL' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns:activiti='http://activiti.org/bpmn' targetNamespace='Examples'>" + "  <process id='exclusiveGwDefaultSequenceFlow'> "+ "    <startEvent id='theStart' /> "+ "    <sequenceFlow id='flow1' sourceRef='theStart' targetRef='exclusiveGw' /> "+ "    <exclusiveGateway id='exclusiveGw' name='Exclusive Gateway' /> "+ "  </process>"+ "</definitions>";
  try {
    repositoryService.createDeployment().addString("myprocess.bpmn20.xml",noOutgoingFlow).deploy();
    fail("Could deploy a process definition with a XOR Gateway without outgoing sequence flows.");
  }
 catch (  ActivitiException ex) {
  }
}
