public void testMessageEventReceivedNonExistingExecution(){
  try {
    runtimeService.messageEventReceived("alert","nonexistingExecution");
    fail("exception expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertEquals(Execution.class,ae.getObjectClass());
  }
}
