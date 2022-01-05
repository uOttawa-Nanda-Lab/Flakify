public void testQueryByTaskIdOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId(taskIds.get(0)).taskName("INVALID NAME").endOr();
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
