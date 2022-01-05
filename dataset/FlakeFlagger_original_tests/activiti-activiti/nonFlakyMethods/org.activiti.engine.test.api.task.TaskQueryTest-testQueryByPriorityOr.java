public void testQueryByPriorityOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskPriority(10);
  assertEquals(2,query.list().size());
  assertEquals(2,query.count());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
  query=taskService.createTaskQuery().or().taskId("invalid").taskPriority(100);
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  query=taskService.createTaskQuery().or().taskId("invalid").taskMinPriority(50);
  assertEquals(3,query.list().size());
  query=taskService.createTaskQuery().or().taskId("invalid").taskMinPriority(10);
  assertEquals(5,query.list().size());
  query=taskService.createTaskQuery().or().taskId("invalid").taskMaxPriority(10);
  assertEquals(9,query.list().size());
  query=taskService.createTaskQuery().or().taskId("invalid").taskMaxPriority(3);
  assertEquals(6,query.list().size());
}
