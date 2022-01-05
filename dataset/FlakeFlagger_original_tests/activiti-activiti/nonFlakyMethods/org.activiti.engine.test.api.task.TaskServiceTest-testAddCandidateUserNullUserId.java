public void testAddCandidateUserNullUserId(){
  try {
    taskService.addCandidateUser("taskId",null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("identityId is null",ae.getMessage());
  }
}
