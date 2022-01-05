public void testSetPriorityNullTaskId(){
  try {
    taskService.setPriority(null,12345);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
