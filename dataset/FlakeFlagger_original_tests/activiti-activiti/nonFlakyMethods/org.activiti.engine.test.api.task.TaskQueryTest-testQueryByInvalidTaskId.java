public void testQueryByInvalidTaskId(){
  TaskQuery query=taskService.createTaskQuery().taskId("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    taskService.createTaskQuery().taskId(null);
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
