public void testQueryByName(){
  TaskQuery query=taskService.createTaskQuery().taskName("testTask");
  assertEquals(6,query.list().size());
  assertEquals(6,query.count());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
}
