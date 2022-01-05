@Deployment public void testSignalWaitOnUserTaskBoundaryEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signal-wait");
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).signalEventSubscriptionName("waitsig").singleResult();
  assertNotNull(execution);
  runtimeService.signalEventReceived("waitsig",execution.getId());
  execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).signalEventSubscriptionName("waitsig").singleResult();
  assertNull(execution);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  assertEquals("Wait2",task.getName());
}
