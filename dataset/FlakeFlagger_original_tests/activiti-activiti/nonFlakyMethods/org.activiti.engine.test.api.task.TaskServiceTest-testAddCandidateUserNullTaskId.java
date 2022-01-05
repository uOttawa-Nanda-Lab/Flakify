public void testAddCandidateUserNullTaskId(){
  try {
    taskService.addCandidateUser(null,"userId");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
