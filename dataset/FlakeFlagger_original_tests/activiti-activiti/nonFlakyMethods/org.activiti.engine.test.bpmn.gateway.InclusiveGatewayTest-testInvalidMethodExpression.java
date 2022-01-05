@Deployment public void testInvalidMethodExpression(){
  try {
    runtimeService.startProcessInstanceByKey("inclusiveInvalidMethodExpression",CollectionUtil.singletonMap("order",new InclusiveGatewayTestOrder(50)));
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent("Unknown method used in expression",e.getMessage());
  }
}
