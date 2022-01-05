public void testQueryByInvalidDescription(){
  TaskQuery query=taskService.createTaskQuery().taskDescription("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskDescription(null).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
