public void testSetDueDateUnexistingTaskId(){
  try {
    taskService.setDueDate("unexistingtask",new Date());
    fail("ActivitiException expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Cannot find task with id unexistingtask",ae.getMessage());
  }
}
