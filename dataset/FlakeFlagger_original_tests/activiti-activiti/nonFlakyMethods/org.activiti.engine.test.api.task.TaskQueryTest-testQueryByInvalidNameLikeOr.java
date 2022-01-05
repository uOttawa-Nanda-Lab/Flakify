public void testQueryByInvalidNameLikeOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskNameLike("1");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  try {
    taskService.createTaskQuery().or().taskId("invalid").taskNameLike(null).singleResult();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
