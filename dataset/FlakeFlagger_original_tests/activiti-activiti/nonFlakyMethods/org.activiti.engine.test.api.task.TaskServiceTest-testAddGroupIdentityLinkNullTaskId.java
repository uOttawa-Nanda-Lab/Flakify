public void testAddGroupIdentityLinkNullTaskId(){
  try {
    taskService.addGroupIdentityLink(null,"groupId",IdentityLinkType.CANDIDATE);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
