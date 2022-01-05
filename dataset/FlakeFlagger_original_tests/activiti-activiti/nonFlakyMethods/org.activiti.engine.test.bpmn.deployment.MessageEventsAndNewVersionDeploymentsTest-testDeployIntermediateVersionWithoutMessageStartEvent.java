/** 
 * v1 -> has start message event v2 -> has no start message event v3 -> has start message event
 */
public void testDeployIntermediateVersionWithoutMessageStartEvent(){
  String deploymentId1=deployStartMessageTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(1);
  String deploymentId2=deployProcessWithoutEvents();
  assertEquals(0,getAllEventSubscriptions().size());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  try {
    runtimeService.startProcessInstanceByMessage("myStartMessage");
    fail();
  }
 catch (  Exception e) {
  }
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(0);
  String deploymentId3=deployStartMessageTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.startProcessInstanceByMessage("myStartMessage");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(1);
  List<EventSubscriptionEntity> eventSubscriptions=getAllEventSubscriptions();
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId3).singleResult().getId(),eventSubscriptions.get(0).getProcessDefinitionId());
  cleanup(deploymentId1,deploymentId2,deploymentId3);
}
