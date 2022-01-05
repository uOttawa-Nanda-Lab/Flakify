public void testClaimUnexistingTaskId(){
  try {
    taskService.claim("unexistingtaskid","user");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Cannot find task with id unexistingtaskid",ae.getMessage());
    assertEquals(Task.class,ae.getObjectClass());
  }
}
