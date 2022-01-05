public void testFindActiveActivityIdsNullExecututionId(){
  try {
    runtimeService.getActiveActivityIds(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("executionId is null",ae.getMessage());
  }
}
