@Deployment public void testTerminateInSubProcessSequentialConcurrentMultiInstance() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  long remainingExecutions=runtimeService.createExecutionQuery().count();
  assertTrue(remainingExecutions > 0);
  assertEquals(3,serviceTaskInvokedCount2);
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preNormalEnd").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
