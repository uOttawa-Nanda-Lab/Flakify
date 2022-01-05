public void testQueryByDescriptionLike(){
  TaskQuery query=taskService.createTaskQuery().taskDescriptionLike("%gonzo%");
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
