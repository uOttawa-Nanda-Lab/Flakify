public void testQueryByCandidateGroupInOrUsingUserGroupLookupProxy(){
  Mockito.when(userGroupManager.getUserGroups(KERMIT)).thenReturn(KERMITSGROUPS);
  Mockito.when(userGroupManager.getUserGroups(GONZO)).thenReturn(GONZOSGROUPS);
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
  query=taskService.createTaskQuery().or().taskCandidateUser(KERMIT).taskCandidateGroupIn(groups).endOr();
  assertEquals(11,query.count());
  assertEquals(11,query.list().size());
  query=taskService.createTaskQuery().or().taskCandidateUser(KERMIT).taskCandidateGroup("unexisting").endOr();
  assertEquals(6,query.count());
  assertEquals(6,query.list().size());
  query=taskService.createTaskQuery().or().taskCandidateUser(KERMIT).taskCandidateGroupIn(groups).endOr().or().taskCandidateUser(GONZO).taskCandidateGroupIn(groups);
  assertEquals(5,query.count());
  assertEquals(5,query.list().size());
}
