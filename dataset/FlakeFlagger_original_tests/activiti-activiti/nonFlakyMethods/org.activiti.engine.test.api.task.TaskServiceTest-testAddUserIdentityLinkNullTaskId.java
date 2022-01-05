public void testAddUserIdentityLinkNullTaskId(){
  try {
    taskService.addUserIdentityLink(null,"userId",IdentityLinkType.CANDIDATE);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
