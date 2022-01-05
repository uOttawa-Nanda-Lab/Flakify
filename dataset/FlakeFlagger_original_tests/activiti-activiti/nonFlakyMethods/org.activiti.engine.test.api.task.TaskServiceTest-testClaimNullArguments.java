public void testClaimNullArguments(){
  try {
    taskService.claim(null,"userid");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
