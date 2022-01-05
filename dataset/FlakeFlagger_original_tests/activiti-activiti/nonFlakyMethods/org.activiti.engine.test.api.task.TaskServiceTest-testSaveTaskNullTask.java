public void testSaveTaskNullTask(){
  try {
    taskService.saveTask(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("task is null",ae.getMessage());
  }
}
