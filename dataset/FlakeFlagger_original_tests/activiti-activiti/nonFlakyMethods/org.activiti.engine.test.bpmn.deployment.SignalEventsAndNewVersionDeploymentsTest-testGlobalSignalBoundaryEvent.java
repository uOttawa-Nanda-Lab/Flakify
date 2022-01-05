@Deployment public void testGlobalSignalBoundaryEvent(){
  runtimeService.startProcessInstanceByKey("signalTest");
  String deploymentId=deployBoundarySignalTestProcess();
  runtimeService.startProcessInstanceByKey("signalTest");
  assertEquals(2,getAllEventSubscriptions().size());
  runtimeService.signalEventReceived("mySignal");
  assertEquals(0,getAllEventSubscriptions().size());
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  for (  Task task : tasks) {
    assertEquals("Task after signal",task.getName());
  }
  cleanup(deploymentId);
}
