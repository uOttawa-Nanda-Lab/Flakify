public void testQueryByCandidateUserOr(){
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskCandidateUser(KERMIT,KERMITSGROUPS);
  assertEquals(11,query.count());
  assertEquals(11,query.list().size());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
  query=taskService.createTaskQuery().or().taskId("invalid").taskCandidateUser(FOZZIE,FOZZIESGROUPS);
  assertEquals(3,query.count());
  assertEquals(3,query.list().size());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
}
