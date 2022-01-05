public void testCompleteTaskWithParametersUnexistingTaskId(){
  try {
    taskService.complete("unexistingtask");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Cannot find task with id unexistingtask",ae.getMessage());
    assertEquals(Task.class,ae.getObjectClass());
  }
}
