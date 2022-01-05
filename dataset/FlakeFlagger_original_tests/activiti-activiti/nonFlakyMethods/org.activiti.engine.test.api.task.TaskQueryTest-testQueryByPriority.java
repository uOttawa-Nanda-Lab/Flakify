public void testQueryByPriority(){
  TaskQuery query=taskService.createTaskQuery().taskPriority(10);
  assertEquals(2,query.list().size());
  assertEquals(2,query.count());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
  query=taskService.createTaskQuery().taskPriority(100);
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
  query=taskService.createTaskQuery().taskMinPriority(50);
  assertEquals(3,query.list().size());
  query=taskService.createTaskQuery().taskMinPriority(10);
  assertEquals(5,query.list().size());
  query=taskService.createTaskQuery().taskMaxPriority(10);
  assertEquals(9,query.list().size());
  query=taskService.createTaskQuery().taskMaxPriority(3);
  assertEquals(6,query.list().size());
}
