public void testQueryByNullAssignee(){
  try {
    taskService.createTaskQuery().taskAssignee(null).list();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
