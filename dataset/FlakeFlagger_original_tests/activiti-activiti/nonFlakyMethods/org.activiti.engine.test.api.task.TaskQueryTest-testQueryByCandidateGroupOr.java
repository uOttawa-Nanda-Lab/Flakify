public void testQueryByCandidateGroupOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskCandidateGroup("management");
  assertEquals(3,query.count());
  assertEquals(3,query.list().size());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
  query=taskService.createTaskQuery().or().taskId("invalid").taskCandidateGroup("sales");
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
}
