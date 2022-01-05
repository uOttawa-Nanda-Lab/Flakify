public void testQueryNoCriteria(){
  TaskQuery query=taskService.createTaskQuery();
  assertEquals(12,query.count());
  assertEquals(12,query.list().size());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
}
