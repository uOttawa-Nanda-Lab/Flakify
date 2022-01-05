public void testQueryByInvalidName(){
  TaskQuery query=taskService.createTaskQuery().taskName("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskName(null).singleResult();
    fail("expected exception");
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
