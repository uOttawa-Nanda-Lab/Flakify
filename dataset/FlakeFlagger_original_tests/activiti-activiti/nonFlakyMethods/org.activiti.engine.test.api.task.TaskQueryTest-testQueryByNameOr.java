public void testQueryByNameOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskName("testTask").taskId("invalid");
  assertEquals(6,query.list().size());
  assertEquals(6,query.count());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
}
