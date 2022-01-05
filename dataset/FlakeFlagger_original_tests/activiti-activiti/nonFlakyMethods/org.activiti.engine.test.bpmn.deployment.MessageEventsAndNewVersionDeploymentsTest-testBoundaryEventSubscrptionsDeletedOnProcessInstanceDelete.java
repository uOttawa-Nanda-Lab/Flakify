/** 
 * Verifying that the event subscriptions do get removed when removing a process instance.
 */
public void testBoundaryEventSubscrptionsDeletedOnProcessInstanceDelete(){
  String deploymentId1=deployBoundaryMessageTestProcess();
  runtimeService.startProcessInstanceByKey("messageTest");
  assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());
  String deploymentId2=deployBoundaryMessageTestProcess();
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("messageTest");
  assertEquals(2,taskService.createTaskQuery().count());
  assertEquals(2,getAllEventSubscriptions().size());
  runtimeService.deleteProcessInstance(processInstance2.getId(),"testing");
  assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());
  assertEquals(1,getAllEventSubscriptions().size());
  runtimeService.messageEventReceived("myMessage",getExecutionIdsForMessageEventSubscription("myMessage").get(0));
  assertEquals(0,getAllEventSubscriptions().size());
  assertEquals("Task after message",taskService.createTaskQuery().singleResult().getName());
  cleanup(deploymentId1,deploymentId2);
}
