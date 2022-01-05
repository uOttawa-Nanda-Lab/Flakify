public void testRemoveVariableNullExecutionId(){
  try {
    runtimeService.removeVariable(null,"variable");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
