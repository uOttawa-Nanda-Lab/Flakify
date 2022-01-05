public void testConnectedToActivity(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/gateway/EventBasedGatewayTest.testConnectedToActivity.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  Exception e) {
    if (!e.getMessage().contains("Event based gateway can only be connected to elements of type intermediateCatchEvent")) {
      fail("different exception expected");
    }
  }
}
