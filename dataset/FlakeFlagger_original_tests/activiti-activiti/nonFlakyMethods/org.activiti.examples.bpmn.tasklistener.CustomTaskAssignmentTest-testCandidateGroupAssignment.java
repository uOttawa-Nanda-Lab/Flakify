@Deployment public void testCandidateGroupAssignment(){
  runtimeService.startProcessInstanceByKey("customTaskAssignment");
  assertEquals(1,taskService.createTaskQuery().taskCandidateGroup("management").count());
  assertEquals(1,taskService.createTaskQuery().taskCandidateUser("kermit",Arrays.asList("management")).count());
  assertEquals(0,taskService.createTaskQuery().taskCandidateUser("fozzie",null).count());
}
