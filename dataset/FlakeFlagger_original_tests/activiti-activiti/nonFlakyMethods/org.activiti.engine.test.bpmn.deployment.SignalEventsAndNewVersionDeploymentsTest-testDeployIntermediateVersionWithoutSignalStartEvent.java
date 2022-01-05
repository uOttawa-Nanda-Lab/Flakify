/** 
 * v1 -> has start signal event v2 -> has no start signal event v3 -> has start signal event
 */
public void testDeployIntermediateVersionWithoutSignalStartEvent(){
  String deploymentId1=deployStartSignalTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(1);
  String deploymentId2=deployProcessWithoutEvents();
  assertEquals(0,getAllEventSubscriptions().size());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(0);
  String deploymentId3=deployStartSignalTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.signalEventReceived("myStartSignal");
  assertEquals(2,runtimeService.createProcessInstanceQuery().count());
  assertEventSubscriptionsCount(1);
  List<EventSubscriptionEntity> eventSubscriptions=getAllEventSubscriptions();
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId3).singleResult().getId(),eventSubscriptions.get(0).getProcessDefinitionId());
  cleanup(deploymentId1,deploymentId2,deploymentId3);
}
