public void testQueryByDescription(){
  TaskQuery query=taskService.createTaskQuery().taskDescription("testTask description");
  assertEquals(6,query.list().size());
  assertEquals(6,query.count());
  try {
    query.singleResult();
    fail();
  }
 catch (  ActivitiException e) {
  }
}
