@Deployment(resources={"org/activiti/engine/test/bpmn/gateway/ExclusiveGatewayTest.testDivergingExclusiveGateway.bpmn20.xml"}) public void testUnknownVariableInExpression(){
  try {
    runtimeService.startProcessInstanceByKey("exclusiveGwDiverging",CollectionUtil.singletonMap("iinput",1));
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent("Unknown property used in expression",e.getMessage());
  }
}
