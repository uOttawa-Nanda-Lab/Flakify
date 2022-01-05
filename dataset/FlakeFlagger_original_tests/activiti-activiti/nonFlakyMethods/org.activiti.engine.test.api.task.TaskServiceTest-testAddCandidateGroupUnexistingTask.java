public void testAddCandidateGroupUnexistingTask(){
  try {
    taskService.addCandidateGroup("unexistingTaskId","group");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Cannot find task with id unexistingTaskId",ae.getMessage());
    assertEquals(Task.class,ae.getObjectClass());
  }
}
