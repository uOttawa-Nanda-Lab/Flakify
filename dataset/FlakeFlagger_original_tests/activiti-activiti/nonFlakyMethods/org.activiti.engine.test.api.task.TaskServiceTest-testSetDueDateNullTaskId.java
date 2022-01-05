public void testSetDueDateNullTaskId(){
  try {
    taskService.setDueDate(null,new Date());
    fail("ActivitiException expected");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
