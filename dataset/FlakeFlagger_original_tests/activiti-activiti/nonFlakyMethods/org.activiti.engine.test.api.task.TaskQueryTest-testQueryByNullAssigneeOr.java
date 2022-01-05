public void testQueryByNullAssigneeOr(){
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskAssignee(null).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
