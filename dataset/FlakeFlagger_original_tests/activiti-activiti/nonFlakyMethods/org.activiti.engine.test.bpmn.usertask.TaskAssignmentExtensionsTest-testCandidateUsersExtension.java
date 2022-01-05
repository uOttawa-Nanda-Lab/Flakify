@Deployment public void testCandidateUsersExtension(){
  runtimeService.startProcessInstanceByKey("candidateUsersExtension");
  List<Task> tasks=taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list();
  assertEquals(1,tasks.size());
  tasks=taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list();
  assertEquals(1,tasks.size());
}
