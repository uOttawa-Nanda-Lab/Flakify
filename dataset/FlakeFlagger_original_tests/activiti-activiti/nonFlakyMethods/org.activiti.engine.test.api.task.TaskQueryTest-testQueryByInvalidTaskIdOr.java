public void testQueryByInvalidTaskIdOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskName("invalid");
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
