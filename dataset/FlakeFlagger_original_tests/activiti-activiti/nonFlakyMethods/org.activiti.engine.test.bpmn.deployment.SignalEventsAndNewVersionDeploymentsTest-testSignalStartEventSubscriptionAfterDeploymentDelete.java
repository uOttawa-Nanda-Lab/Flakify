public void testSignalStartEventSubscriptionAfterDeploymentDelete(){
  String deploymentId1=deployStartSignalTestProcess();
  List<EventSubscriptionEntity> eventSubscriptions=getAllEventSubscriptions();
  assertEquals(1,eventSubscriptions.size());
  String deploymentId2=deployStartSignalTestProcess();
  eventSubscriptions=getAllEventSubscriptions();
  assertEquals(1,eventSubscriptions.size());
  repositoryService.deleteDeployment(deploymentId2,true);
  eventSubscriptions=getAllEventSubscriptions();
  assertEquals(1,eventSubscriptions.size());
  cleanup(deploymentId1);
  assertEquals(0,getAllEventSubscriptions().size());
  deploymentId1=deployStartSignalTestProcess();
  deploymentId2=deployStartSignalTestProcess();
  assertEquals(1,getAllEventSubscriptions().size());
  repositoryService.deleteDeployment(deploymentId1,true);
  eventSubscriptions=getAllEventSubscriptions();
  assertEquals(1,eventSubscriptions.size());
  assertEquals(repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId2).singleResult().getId(),eventSubscriptions.get(0).getProcessDefinitionId());
  cleanup(deploymentId2);
  assertEquals(0,getAllEventSubscriptions().size());
}
