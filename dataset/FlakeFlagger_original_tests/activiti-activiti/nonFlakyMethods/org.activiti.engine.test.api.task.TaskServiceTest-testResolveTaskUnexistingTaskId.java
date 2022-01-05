public void testResolveTaskUnexistingTaskId(){
  try {
    taskService.resolveTask("blergh");
    fail();
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Cannot find task with id",ae.getMessage());
  }
}
