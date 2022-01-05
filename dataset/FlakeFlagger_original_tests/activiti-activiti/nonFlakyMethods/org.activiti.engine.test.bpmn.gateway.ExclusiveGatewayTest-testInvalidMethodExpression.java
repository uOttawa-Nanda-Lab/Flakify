@Deployment public void testInvalidMethodExpression(){
  try {
    runtimeService.startProcessInstanceByKey("invalidMethodExpression",CollectionUtil.singletonMap("order",new ExclusiveGatewayTestOrder(50)));
    fail();
  }
 catch (  ActivitiException e) {
    assertTextPresent("Unknown method used in expression",e.getMessage());
  }
}
