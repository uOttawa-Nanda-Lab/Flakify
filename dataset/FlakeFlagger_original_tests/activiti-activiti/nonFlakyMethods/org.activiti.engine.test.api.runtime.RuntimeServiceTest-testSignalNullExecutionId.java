public void testSignalNullExecutionId(){
  try {
    runtimeService.trigger(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
