public void testQueryByNameLikeOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskNameLike("gonzo%");
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
