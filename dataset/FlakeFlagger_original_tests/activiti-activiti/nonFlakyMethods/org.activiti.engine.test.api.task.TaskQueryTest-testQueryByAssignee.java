public void testQueryByAssignee(){
  TaskQuery query=taskService.createTaskQuery().taskAssignee(GONZO);
  assertEquals(1,query.count());
  assertEquals(1,query.list().size());
  assertNotNull(query.singleResult());
  query=taskService.createTaskQuery().taskAssignee(KERMIT);
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  assertNull(query.singleResult());
}
