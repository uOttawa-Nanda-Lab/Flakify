public void testSignalEventReceivedNonExistingExecution(){
  try {
    runtimeService.signalEventReceived("alert","nonexistingExecution");
    fail("exception expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertEquals(Execution.class,ae.getObjectClass());
  }
}
