public void testFindActiveActivityIdsUnexistingExecututionId(){
  try {
    runtimeService.getActiveActivityIds("unexistingExecutionId");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("execution unexistingExecutionId doesn't exist",ae.getMessage());
    assertEquals(Execution.class,ae.getObjectClass());
  }
}
