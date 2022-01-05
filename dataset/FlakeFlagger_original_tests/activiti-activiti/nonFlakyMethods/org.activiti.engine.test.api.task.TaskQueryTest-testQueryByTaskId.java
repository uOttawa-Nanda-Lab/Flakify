public void testQueryByTaskId(){
  TaskQuery query=taskService.createTaskQuery().taskId(taskIds.get(0));
  assertNotNull(query.singleResult());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
