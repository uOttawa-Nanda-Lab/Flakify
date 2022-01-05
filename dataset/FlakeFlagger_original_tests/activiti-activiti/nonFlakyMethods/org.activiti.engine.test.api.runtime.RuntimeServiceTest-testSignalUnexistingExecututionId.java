public void testSignalUnexistingExecututionId(){
  try {
    runtimeService.trigger("unexistingExecutionId");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("execution unexistingExecutionId doesn't exist",ae.getMessage());
    assertEquals(Execution.class,ae.getObjectClass());
  }
}
