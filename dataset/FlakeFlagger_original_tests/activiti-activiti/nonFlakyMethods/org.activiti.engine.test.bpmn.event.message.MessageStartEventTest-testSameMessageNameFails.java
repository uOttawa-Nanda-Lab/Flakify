public void testSameMessageNameFails(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/MessageStartEventTest.testSingleMessageStartEvent.bpmn20.xml").deploy().getId();
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/otherProcessWithNewInvoiceMessage.bpmn20.xml").deploy();
    fail("exception expected");
  }
 catch (  ActivitiException e) {
    assertTrue(e.getMessage().contains("there already is a message event subscription for the message with name"));
  }
  repositoryService.deleteDeployment(deploymentId);
}
