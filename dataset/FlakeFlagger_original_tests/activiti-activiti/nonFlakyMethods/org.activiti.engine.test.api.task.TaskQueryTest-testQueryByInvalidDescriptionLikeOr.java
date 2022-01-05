public void testQueryByInvalidDescriptionLikeOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskDescriptionLike("invalid");
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
