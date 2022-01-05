@Deployment public void testMixedCandidateUserAndGroup(){
  runtimeService.startProcessInstanceByKey("mixedCandidateUserAndGroupExample");
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(GONZO,GONZOSGROUPS).list().size());
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser(KERMIT,KERMITSGROUPS).list().size());
}
