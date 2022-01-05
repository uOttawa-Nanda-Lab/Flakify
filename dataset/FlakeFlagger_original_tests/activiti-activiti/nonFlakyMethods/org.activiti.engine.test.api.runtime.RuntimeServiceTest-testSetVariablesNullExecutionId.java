@SuppressWarnings("unchecked") public void testSetVariablesNullExecutionId(){
  try {
    runtimeService.setVariables(null,Collections.EMPTY_MAP);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
