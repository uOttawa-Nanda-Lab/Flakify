@Deployment public void testMixedCandidateUserDefinition(){
  runtimeService.startProcessInstanceByKey("mixedCandidateUser");
  List<Task> tasks=taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
  assertEquals(1,tasks.size());
  tasks=taskService.createTaskQuery().taskCandidateUser(FOZZIE,FOZZIESGROUPS).list();
  assertEquals(1,tasks.size());
  tasks=taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list();
  assertEquals(1,tasks.size());
  tasks=taskService.createTaskQuery().taskCandidateUser("mispiggy",null).list();
  assertEquals(0,tasks.size());
}
