public void testQueryByInvalidPriorityOr(){
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskPriority(null);
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
