public void testQueryByCandidateGroupIn(){
  List<String> groups=Arrays.asList("management","accountancy");
  TaskQuery query=taskService.createTaskQuery().taskCandidateGroupIn(groups);
  assertEquals(5,query.count());
  assertEquals(5,query.list().size());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
  query=taskService.createTaskQuery().taskCandidateUser(KERMIT,groups);
  assertEquals(11,query.count());
  assertEquals(11,query.list().size());
  query=taskService.createTaskQuery().taskCandidateUser(KERMIT,Arrays.asList("unexisting"));
  assertEquals(6,query.count());
  assertEquals(6,query.list().size());
  query=taskService.createTaskQuery().taskCandidateUser("unexisting",Arrays.asList("unexisting"));
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  groups=Arrays.asList("management","accountancy","sales","unexising");
  query=taskService.createTaskQuery().taskCandidateGroupIn(groups);
  assertEquals(5,query.count());
  assertEquals(5,query.list().size());
}
