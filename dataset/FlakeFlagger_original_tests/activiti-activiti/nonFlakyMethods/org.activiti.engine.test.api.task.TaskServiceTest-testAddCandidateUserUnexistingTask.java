public void testAddCandidateUserUnexistingTask(){
  try {
    taskService.addCandidateUser("unexistingTaskId","user");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Cannot find task with id unexistingTaskId",ae.getMessage());
    assertEquals(Task.class,ae.getObjectClass());
  }
}
