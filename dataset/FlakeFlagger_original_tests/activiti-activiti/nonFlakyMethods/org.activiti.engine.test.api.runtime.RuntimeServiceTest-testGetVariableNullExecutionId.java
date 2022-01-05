public void testGetVariableNullExecutionId(){
  try {
    runtimeService.getVariables(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
