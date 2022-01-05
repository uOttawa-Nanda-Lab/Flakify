@SuppressWarnings("unchecked") public void testSetVariablesUnexistingExecutionId(){
  try {
    runtimeService.setVariables("unexistingexecution",Collections.EMPTY_MAP);
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("execution unexistingexecution doesn't exist",ae.getMessage());
    assertEquals(Execution.class,ae.getObjectClass());
  }
}
