public void testQueryByInvalidDescriptionLike(){
  TaskQuery query=taskService.createTaskQuery().taskDescriptionLike("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskDescriptionLike(null).list();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
