/** 
 * Verifying that the event subscriptions do get removed when removing a deployment.
 */
public void testBoundaryEventSubscriptionDeletedOnDeploymentDelete(){
  String deploymentId=deployBoundarySignalTestProcess();
  runtimeService.startProcessInstanceByKey("signalTest");
  assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());
  String deploymentId2=deployBoundarySignalTestProcess();
  runtimeService.startProcessInstanceByKey("signalTest");
  assertEquals(2,taskService.createTaskQuery().count());
  assertEquals(2,getAllEventSubscriptions().size());
  repositoryService.deleteDeployment(deploymentId,true);
  assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());
  assertEquals(1,getAllEventSubscriptions().size());
  repositoryService.deleteDeployment(deploymentId2,true);
  assertEquals(0,getAllEventSubscriptions().size());
}
