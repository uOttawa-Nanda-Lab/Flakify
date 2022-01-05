/** 
 * Verifying that the event subscriptions do get removed when removing a process instance.
 */
public void testBoundaryEventSubscrptionsDeletedOnProcessInstanceDelete(){
  String deploymentId1=deployBoundarySignalTestProcess();
  runtimeService.startProcessInstanceByKey("signalTest");
  assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());
  String deploymentId2=deployBoundarySignalTestProcess();
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("signalTest");
  assertEquals(2,taskService.createTaskQuery().count());
  assertEquals(2,getAllEventSubscriptions().size());
  runtimeService.deleteProcessInstance(processInstance2.getId(),"testing");
  assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());
  assertEquals(1,getAllEventSubscriptions().size());
  runtimeService.signalEventReceived("mySignal");
  assertEquals(0,getAllEventSubscriptions().size());
  assertEquals("Task after signal",taskService.createTaskQuery().singleResult().getName());
  cleanup(deploymentId1,deploymentId2);
}
