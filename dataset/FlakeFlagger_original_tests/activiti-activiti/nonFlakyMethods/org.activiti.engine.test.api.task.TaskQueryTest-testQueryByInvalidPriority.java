public void testQueryByInvalidPriority(){
  try {
    taskService.createTaskQuery().taskPriority(null);
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
