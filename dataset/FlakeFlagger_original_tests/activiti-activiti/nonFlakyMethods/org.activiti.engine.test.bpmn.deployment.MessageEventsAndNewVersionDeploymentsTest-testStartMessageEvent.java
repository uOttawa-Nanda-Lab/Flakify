public void testStartMessageEvent(){
  String deploymentId1=deployStartMessageTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  assertEventSubscriptionsCount(1);
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  String deploymentId2=deployStartMessageTestProcess();
  assertEventSubscriptionsCount(1);
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(1);
  cleanup(deploymentId1,deploymentId2);
}
