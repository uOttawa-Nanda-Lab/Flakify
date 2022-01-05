@SuppressWarnings("unchecked") public void testRemoveVariablesNullExecutionId(){
  try {
    runtimeService.removeVariables(null,Collections.EMPTY_LIST);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
