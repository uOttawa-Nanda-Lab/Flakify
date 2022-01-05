public void testCompleteTaskNullTaskId(){
  try {
    taskService.complete(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
