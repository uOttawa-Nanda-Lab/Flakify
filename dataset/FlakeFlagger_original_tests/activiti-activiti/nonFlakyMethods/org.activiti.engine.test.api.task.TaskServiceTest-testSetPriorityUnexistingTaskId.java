public void testSetPriorityUnexistingTaskId(){
  try {
    taskService.setPriority("unexistingtask",12345);
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Cannot find task with id unexistingtask",ae.getMessage());
    assertEquals(Task.class,ae.getObjectClass());
  }
}
