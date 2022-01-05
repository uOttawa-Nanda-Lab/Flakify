public void testAddUserIdentityLinkNullUserId(){
  try {
    taskService.addUserIdentityLink("taskId",null,IdentityLinkType.CANDIDATE);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("identityId is null",ae.getMessage());
  }
}
