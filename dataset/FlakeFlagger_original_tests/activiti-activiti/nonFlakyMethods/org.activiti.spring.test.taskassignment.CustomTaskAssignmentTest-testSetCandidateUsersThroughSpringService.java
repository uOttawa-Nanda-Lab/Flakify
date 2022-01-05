@Deployment public void testSetCandidateUsersThroughSpringService(){
  runtimeService.startProcessInstanceByKey("candidateUsersThroughSpringService",CollectionUtil.singletonMap("emp","fozzie"));
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser("kermit").count());
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser("fozzie").count());
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser("gonzo").count());
  assertEquals(0,taskService.createTaskQuery().taskCandidateUser("mispiggy").count());
}
