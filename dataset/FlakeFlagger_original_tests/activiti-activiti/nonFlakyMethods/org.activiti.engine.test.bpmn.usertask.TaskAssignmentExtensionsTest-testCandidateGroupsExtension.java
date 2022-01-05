@Deployment public void testCandidateGroupsExtension(){
  runtimeService.startProcessInstanceByKey("candidateGroupsExtension");
  List<Task> tasks=taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
  assertEquals(1,tasks.size());
  assertEquals("make profit",tasks.get(0).getName());
  tasks=taskService.createTaskQuery().taskCandidateUser(FOZZIE,FOZZIESGROUPS).list();
  assertEquals(1,tasks.size());
  assertEquals("make profit",tasks.get(0).getName());
  TaskQuery query=taskService.createTaskQuery();
  assertEquals(1,query.taskCandidateGroup("management").count());
  assertEquals(1,query.taskCandidateGroup("accountancy").count());
}
