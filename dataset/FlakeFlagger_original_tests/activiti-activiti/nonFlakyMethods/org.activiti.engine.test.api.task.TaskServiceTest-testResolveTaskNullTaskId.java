public void testResolveTaskNullTaskId(){
  try {
    taskService.resolveTask(null);
    fail();
  }
 catch (  ActivitiException ae) {
    assertTextPresent("taskId is null",ae.getMessage());
  }
}
