public void testAddGroupIdentityLinkUnexistingTask(){
  try {
    taskService.addGroupIdentityLink("unexistingTaskId","user",IdentityLinkType.CANDIDATE);
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Cannot find task with id unexistingTaskId",ae.getMessage());
    assertEquals(Task.class,ae.getObjectClass());
  }
}
