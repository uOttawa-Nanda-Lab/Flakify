public void testAddCandidateGroupNullGroupId(){
  try {
    taskService.addCandidateGroup("taskId",null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("identityId is null",ae.getMessage());
  }
}
