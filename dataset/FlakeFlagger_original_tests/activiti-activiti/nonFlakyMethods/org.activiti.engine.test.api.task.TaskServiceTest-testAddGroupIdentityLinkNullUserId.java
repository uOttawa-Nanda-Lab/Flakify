public void testAddGroupIdentityLinkNullUserId(){
  try {
    taskService.addGroupIdentityLink("taskId",null,IdentityLinkType.CANDIDATE);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("identityId is null",ae.getMessage());
  }
}
