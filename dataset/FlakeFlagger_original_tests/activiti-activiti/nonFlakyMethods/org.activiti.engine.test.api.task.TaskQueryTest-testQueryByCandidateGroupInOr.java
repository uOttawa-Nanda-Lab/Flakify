public void testQueryByCandidateGroupInOr(){
  List<String> groups=Arrays.asList("management","accountancy");
  TaskQuery query=taskService.createTaskQuery().or().taskId("invalid").taskCandidateGroupIn(groups);
  assertEquals(5,query.count());
  assertEquals(5,query.list().size());
  try {
    query.singleResult();
    fail("expected exception");
  }
 catch (  ActivitiException e) {
  }
  query=taskService.createTaskQuery().or().taskCandidateUser(KERMIT,KERMITSGROUPS).taskCandidateGroupIn(groups).endOr();
  assertEquals(11,query.count());
  assertEquals(11,query.list().size());
  query=taskService.createTaskQuery().or().taskCandidateUser(KERMIT,KERMITSGROUPS).taskCandidateGroup("unexisting").endOr();
  assertEquals(6,query.count());
  assertEquals(6,query.list().size());
  query=taskService.createTaskQuery().or().taskCandidateUser("unexisting",null).taskCandidateGroup("unexisting").endOr();
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  query=taskService.createTaskQuery().or().taskCandidateUser(KERMIT,KERMITSGROUPS).taskCandidateGroupIn(groups).endOr().or().taskCandidateUser(GONZO,GONZOSGROUPS).taskCandidateGroupIn(groups);
  assertEquals(5,query.count());
  assertEquals(5,query.list().size());
  groups=Arrays.asList("management","accountancy","sales","unexising");
  query=taskService.createTaskQuery().or().taskId("invalid").taskCandidateGroupIn(groups);
  assertEquals(5,query.count());
  assertEquals(5,query.list().size());
}
