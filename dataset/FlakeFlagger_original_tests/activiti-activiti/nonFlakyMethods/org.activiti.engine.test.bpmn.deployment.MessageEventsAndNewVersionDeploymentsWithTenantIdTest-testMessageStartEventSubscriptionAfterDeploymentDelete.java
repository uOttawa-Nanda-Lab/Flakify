public void testMessageStartEventSubscriptionAfterDeploymentDelete(){
  String deploymentId1=deployStartMessageTestProcess();
  List<EventSubscriptionEntity> eventSubscriptions=getAllEventSubscriptions();
  assertEquals(1,eventSubscriptions.size());
  String deploymentId2=deployStartMessageTestProcess();
  eventSubscriptions=getAllEventSubscriptions();
  assertEventSubscriptionsCount(1);
  repositoryService.deleteDeployment(deploymentId2,true);
  eventSubscriptions=getAllEventSubscriptions();
  assertEquals(1,eventSubscriptions.size());
  cleanup(deploymentId1);
  assertEquals(0,getAllEventSubscriptions().size());
  deploymentId1=deployStartMessageTestProcess();
  deploymentId2=deployStartMessageTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  repositoryService.deleteDeployment(deploymentId1,true);
  eventSubscriptions=getAllEventSubscriptions();
  assertEquals(1,eventSubscriptions.size());
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId2).singleResult().getId(),eventSubscriptions.get(0).getProcessDefinitionId());
  cleanup(deploymentId2);
  assertEquals(0,getAllEventSubscriptions().size());
}
