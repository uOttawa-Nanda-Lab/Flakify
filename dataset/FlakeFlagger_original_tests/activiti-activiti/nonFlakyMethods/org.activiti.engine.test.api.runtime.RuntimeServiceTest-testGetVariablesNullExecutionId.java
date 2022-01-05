public void testGetVariablesNullExecutionId(){
  try {
    runtimeService.getVariables(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
