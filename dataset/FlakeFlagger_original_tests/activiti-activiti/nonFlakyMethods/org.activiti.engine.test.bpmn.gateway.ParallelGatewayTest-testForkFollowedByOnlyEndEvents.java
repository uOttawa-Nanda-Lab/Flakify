@Deployment public void testForkFollowedByOnlyEndEvents(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("forkFollowedByEndEvents");
  assertTrue(processInstance.isEnded());
}
