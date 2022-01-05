@Deployment public void testSetCandidateGroupsThroughSpringService(){
  runtimeService.startProcessInstanceByKey("candidateUsersThroughSpringService",CollectionUtil.singletonMap("emp","fozzie"));
  assertEquals(1,taskService.createTaskQuery().taskCandidateGroup("management").count());
  assertEquals(1,taskService.createTaskQuery().taskCandidateGroup("directors").count());
  assertEquals(1,taskService.createTaskQuery().taskCandidateGroup("accountancy").count());
  assertEquals(0,taskService.createTaskQuery().taskCandidateGroup("sales").count());
}
