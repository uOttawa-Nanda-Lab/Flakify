@Deployment public void testNestedForksFollowedByEndEvents(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("nestedForksFollowedByEndEvents");
  assertTrue(processInstance.isEnded());
}
