public void testSetVariableNullExecutionId(){
  try {
    runtimeService.setVariable(null,"variableName","variableValue");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
