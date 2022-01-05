public void testRemoveLocalVariableNullExecutionId(){
  try {
    runtimeService.removeVariableLocal(null,"variable");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
