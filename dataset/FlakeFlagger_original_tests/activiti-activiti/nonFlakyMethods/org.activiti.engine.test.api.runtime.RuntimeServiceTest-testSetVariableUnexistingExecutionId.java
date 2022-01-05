public void testSetVariableUnexistingExecutionId(){
  try {
    runtimeService.setVariable("unexistingExecutionId","variableName","value");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("execution unexistingExecutionId doesn't exist",ae.getMessage());
    assertEquals(Execution.class,ae.getObjectClass());
  }
}
