public void testInvalidOnlyTimersUsage(){
  try {
    managementService.createJobQuery().timers().messages().list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
    assertTextPresent("Cannot combine onlyTimers() with onlyMessages() in the same query",e.getMessage());
  }
}
