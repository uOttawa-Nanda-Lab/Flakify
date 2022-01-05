@SuppressWarnings("unchecked") public void testRemoveVariablesLocalNullExecutionId(){
  try {
    runtimeService.removeVariablesLocal(null,Collections.EMPTY_LIST);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
