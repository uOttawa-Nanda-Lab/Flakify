@Deployment public void testTerminateInSubProcessWithBoundaryTerminateAll() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventWithBoundary");
  assertEquals(3,taskService.createTaskQuery().processInstanceId(pi.getId()).count());
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("preTermInnerTask").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
