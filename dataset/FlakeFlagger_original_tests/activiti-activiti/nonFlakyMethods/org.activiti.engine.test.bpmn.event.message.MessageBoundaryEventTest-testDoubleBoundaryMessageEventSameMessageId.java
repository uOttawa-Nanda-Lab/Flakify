public void testDoubleBoundaryMessageEventSameMessageId(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/MessageBoundaryEventTest.testDoubleBoundaryMessageEventSameMessageId.bpmn20.xml").deploy();
    fail("Deployment should fail because Activiti cannot handle two boundary message events with same messageId.");
  }
 catch (  Exception e) {
    assertEquals(0,repositoryService.createDeploymentQuery().count());
  }
}
