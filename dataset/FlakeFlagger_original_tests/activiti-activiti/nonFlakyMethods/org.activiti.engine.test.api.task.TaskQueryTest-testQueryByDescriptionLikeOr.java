public void testQueryByDescriptionLikeOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskDescriptionLike("%gonzo%");
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
