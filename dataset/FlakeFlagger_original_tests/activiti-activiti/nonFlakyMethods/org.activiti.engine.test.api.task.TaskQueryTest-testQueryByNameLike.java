public void testQueryByNameLike(){
  TaskQuery query=taskService.createTaskQuery().taskNameLike("gonzo%");
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
