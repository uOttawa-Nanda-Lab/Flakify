public void testAddCandidateGroupNullTaskId(){
  try {
    taskService.addCandidateGroup(null,"groupId");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
